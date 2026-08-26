plugins {
    application
    id("com.gradleup.shadow") version "8.3.10"
}

group = "world.gregs.void"
version = "1.2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("../libs/clientlibs.jar"))
}

java {
    sourceSets {
        main {
            // All roots stay in the Java *unnamed* (default) package — a named package
            // cannot reference the 634 Class* types. Extra dirs are for clarity only.
            //   src           — RS634 deob (+ toolkit short names)
            //   src-input     — MouseHandler / AWT mouse
            //   src-font      — BitmapFont backends
            //   src-menu      — MenuEntry / DefaultClickSwapper
            //   src-void      — Void helpers (LoginPrefs, MobileKeyboard)
            //   src-microbot  — bot runtime / HUD / mouse backends
            //   src-rs2       — Rs2* scripting API
            java.srcDirs(
                "src",
                "src-input",
                "src-font",
                "src-menu",
                "src-void",
                "src-microbot",
                "src-rs2",
            )
            resources.srcDirs("resources")
        }
    }
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }

    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

application {
    mainClass = "Loader"
}

tasks.shadowJar {
    archiveBaseName.set("void-client")
    archiveClassifier.set("")
    minimize()
}

// Must be a 32-bit jre - ideally with jlink
val jrePath = file("${System.getProperty("user.home")}/.jdks/jdk1.8.0_171/")

// Build a bundle with an in-built 32-bit jre.
tasks.register<Zip>("bundleApp") {
    dependsOn(tasks.named("shadowJar"))

    archiveFileName.set("void-bundle.zip")
    destinationDirectory.set(layout.buildDirectory.dir("dist"))

    val shadowJar = tasks.shadowJar.get()
    from(shadowJar.archiveFile) {
        rename { "client.jar" }
        into("void-bundle")
    }
    from(jrePath) {
        into("void-bundle/jre")
    }
}