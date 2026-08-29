.DEFAULT_GOAL := help

ANDROID     := android
IOS         := ios

JAVA_17     ?= $(HOME)/.jdks/jdk-17.0.20.1+1/Contents/Home

GAME_PORT   ?= 43594
SERVER_IP   ?=

ANDROID_PKG ?= world.gregs.voidosrs.android
ANDROID_ACT ?= $(ANDROID_PKG)/.MainActivity
IOS_PKG     ?= world.gregs.voidosrs.ios
IOS_DEVICE  ?= iPhone 17 Pro
IOS_ARCH    ?= arm64

.PHONY: help \
	desktop desktop-jar desktop-run \
	android android-install android-build android-reverse android-run \
	android-stop android-log android-clean android-server \
	android-apk \
	ios ios-sim ios-build ios-relaunch ios-device ios-clean

help:
	@echo "void-client"
	@echo "  Server must already be running elsewhere on TCP $(GAME_PORT)."
	@echo "  Point clients with SERVER_IP=… (see android-server / README)."
	@echo ""
	@echo "desktop"
	@echo "  make desktop          :client:run"
	@echo "  make desktop-jar      :client:shadowJar"
	@echo "  make desktop-run      jar with --address (SERVER_IP or 127.0.0.1)"
	@echo "android"
	@echo "  make android          installDebug + reverse + launch"
	@echo "  make android-install  :app:installDebug"
	@echo "  make android-build    :app:assembleDebug"
	@echo "  make android-apk      assembleDebug + copy app-debug.apk to resources/"
	@echo "  make android-reverse  adb reverse :$(GAME_PORT)"
	@echo "  make android-run      force-stop + reverse + start"
	@echo "  make android-stop     force-stop"
	@echo "  make android-log      logcat -s void-osrs:I"
	@echo "  make android-server   setprop debug.void.server=\$$SERVER_IP + relaunch"
	@echo "  make android-clean    clean"
	@echo "ios"
	@echo "  make ios              launchIPhoneSimulator ($(IOS_DEVICE))"
	@echo "  make ios-sim          alias of ios"
	@echo "  make ios-build        assemble (no launch)"
	@echo "  make ios-relaunch     simctl install + launch"
	@echo "  make ios-device       physical iPad (sign + devicectl)"
	@echo "  make ios-clean        clean"

check-java17:
	@test -x "$(JAVA_17)/bin/java" || (echo "missing arm64 JDK 17 at JAVA_17=$(JAVA_17)"; exit 1)
	@$(JAVA_17)/bin/java -XshowSettings:properties -version 2>&1 | grep -q 'os.arch = aarch64' \
		|| (echo "JAVA_17 must be arm64 (os.arch=aarch64), got:"; \
		    $(JAVA_17)/bin/java -XshowSettings:properties -version 2>&1 | grep os.arch; exit 1)

# ── desktop ──────────────────────────────────────────────────────────────────

DESKTOP_JAR := client/build/libs/void-client-1.2.0.jar
DESKTOP_ADDR := $(if $(SERVER_IP),$(SERVER_IP),127.0.0.1)

desktop: check-java17
	JAVA_HOME="$(JAVA_17)" PATH="$(JAVA_17)/bin:$$PATH" ./gradlew :client:run

desktop-jar: check-java17
	JAVA_HOME="$(JAVA_17)" PATH="$(JAVA_17)/bin:$$PATH" ./gradlew :client:shadowJar

# Rebuild jar, kill any prior desktop client, then launch with --address.
desktop-run: desktop-jar
	@pkill -f 'void-client-1\.2\.0\.jar' 2>/dev/null || true
	@sleep 1
	"$(JAVA_17)/bin/java" -jar "$(DESKTOP_JAR)" --address $(DESKTOP_ADDR)

# ── android ──────────────────────────────────────────────────────────────────

android-build:
	cd $(ANDROID) && ./gradlew :app:assembleDebug

# Build the debug APK and copy it to resources/ at the repo root.
# Output: resources/app-debug.apk
ANDROID_APK_SRC := $(ANDROID)/app/build/outputs/apk/debug/app-debug.apk
ANDROID_APK_DST := resources/app-debug.apk

android-apk: android-build
	@mkdir -p resources
	@cp $(ANDROID_APK_SRC) $(ANDROID_APK_DST)
	@echo "APK -> $(ANDROID_APK_DST)"

android-install:
	cd $(ANDROID) && ./gradlew :app:installDebug

android-reverse:
	@$(ANDROID)/scripts/adb-reverse.sh $(GAME_PORT)

android-stop:
	adb shell am force-stop $(ANDROID_PKG)

android-run: android-stop android-reverse
	adb shell am start -n $(ANDROID_ACT)

android: android-install android-run

android-log:
	adb logcat -s void-osrs:I

android-server:
	@test -n "$(SERVER_IP)" || (echo "usage: make android-server SERVER_IP=192.168.1.10"; exit 1)
	adb shell setprop debug.void.server $(SERVER_IP)
	$(MAKE) android-run

android-clean:
	cd $(ANDROID) && ./gradlew clean

# ── ios ──────────────────────────────────────────────────────────────────────

ios ios-sim: check-java17
	cd $(IOS) && JAVA_HOME="$(JAVA_17)" PATH="$(JAVA_17)/bin:$$PATH" \
		./gradlew --no-daemon launchIPhoneSimulator \
		-Probovm.arch=$(IOS_ARCH) \
		-Probovm.device.name='$(IOS_DEVICE)'

ios-build: check-java17
	cd $(IOS) && JAVA_HOME="$(JAVA_17)" PATH="$(JAVA_17)/bin:$$PATH" \
		./gradlew --no-daemon assemble \
		-Probovm.arch=$(IOS_ARCH)

ios-relaunch:
	xcrun simctl install booted $(IOS)/build/robovm.tmp/Void.app
	xcrun simctl launch booted $(IOS_PKG)

ios-device:
	bash .cursor/skills/run-mobile-device/scripts/ios-device.sh

ios-clean: check-java17
	cd $(IOS) && JAVA_HOME="$(JAVA_17)" PATH="$(JAVA_17)/bin:$$PATH" ./gradlew --no-daemon clean
