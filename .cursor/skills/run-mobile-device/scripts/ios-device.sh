#!/usr/bin/env bash
# Build, sign, install, and launch Void on the physical iPad.
# RoboVM launchIOSDevice always crashes in AppLauncher; install via devicectl after.
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/../../../.." && pwd)"
IOS="$ROOT/ios"
APP="$IOS/build/robovm.tmp/Void.app"
JAVA_17="${JAVA_17:-$HOME/.jdks/jdk-17.0.20.1+1/Contents/Home}"
UDID="${IOS_UDID:-00008120-000E7D8830214932}"
SIGN_ID="${IOS_SIGN_IDENTITY:-Apple Development: Matheus Gois (885DD2XQL9)}"
PROFILE="${IOS_PROVISIONING_PROFILE:-bdd4f76c-78f2-493c-b2e0-66f72e688f77}"
BUNDLE="${IOS_BUNDLE:-world.gregs.voidosrs.ios}"

if [[ ! -x "$JAVA_17/bin/java" ]]; then
  echo "missing arm64 JDK 17 at JAVA_17=$JAVA_17" >&2
  exit 1
fi
if ! "$JAVA_17/bin/java" -XshowSettings:properties -version 2>&1 | grep -q 'os.arch = aarch64'; then
  echo "JAVA_17 must be arm64 (os.arch=aarch64)" >&2
  "$JAVA_17/bin/java" -XshowSettings:properties -version 2>&1 | grep os.arch >&2 || true
  exit 1
fi

cd "$IOS"
set +e
JAVA_HOME="$JAVA_17" PATH="$JAVA_17/bin:$PATH" ./gradlew --no-daemon launchIOSDevice \
  -Probovm.arch=arm64 \
  -Probovm.iosSkipSigning=false \
  -Probovm.iosSignIdentity="$SIGN_ID" \
  -Probovm.iosProvisioningProfile="$PROFILE" \
  -Probovm.device.udid="$UDID"
gradle_status=$?
set -e

# RoboVM AppLauncher always throws NumberFormatException after a successful .app build.
if [[ ! -d "$APP" ]]; then
  echo "gradle failed (exit $gradle_status) and $APP is missing" >&2
  exit "${gradle_status:-1}"
fi

xcrun devicectl device install app --device "$UDID" "$APP"
xcrun devicectl device process launch --device "$UDID" "$BUNDLE"
echo "OK — launched $BUNDLE on $UDID"
