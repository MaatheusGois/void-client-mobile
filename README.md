# Void Client

Deobfuscated 634 2010-12-14 client.

Mobile (Android + iOS) architecture and how to maintain the ports: **[ARCHITECTURE.md](ARCHITECTURE.md)**.

https://github.com/user-attachments/assets/10381f43-aba1-4b22-b725-282112065ff2


# Build

Build the client with Gradle, no install needed:

```gradle
./gradlew shadowJar
```

You will find the built `void-client.jar` in `/client/build/libs`.

If you're having issues with OpenGL/DirectX make sure you're using or running with a 32bit jre.

```bash
java -jar void-client.jar -d32
```
