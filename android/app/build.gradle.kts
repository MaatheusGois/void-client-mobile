plugins {
    id("com.android.application")
}

android {
    namespace = "world.gregs.voidosrs.android"
    compileSdk = 35

    defaultConfig {
        applicationId = "world.gregs.voidosrs.android"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    sourceSets {
        getByName("main") {
            java.srcDir(layout.buildDirectory.dir("generated/client"))
        }
    }
}

val prepareClientSources by tasks.registering(Copy::class) {
    // Flat merge into generated/client (all default package). Nested domain folders
    // under client/ are organizational only — each .java must land at the dest root.
    val clientDir = rootProject.projectDir.resolve("../client")
    from(clientDir) {
        include("**/*.java")
        exclude("build/**")
        eachFile {
            path = name
        }
        includeEmptyDirs = false
    }
    into(layout.buildDirectory.dir("generated/client"))
    filter { line ->
        line
            .replace("javax.swing", "voidswing")
            .replace("javax.sound", "voidsound")
            .replace("java.applet", "voidapplet")
            .replace("java.lang.management", "voidmgmt")
            .replace("com.sun.management", "voidsun.management")
            .replace("sun.net", "voidsun.net")
            .replace("java.awt", "voidawt")
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.matching { it.name == "preBuild" || it.name.startsWith("compile") }.configureEach {
    dependsOn(prepareClientSources)
}
