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
            // cannot reference the 634 Class* types. Extra dirs mirror client domains:
            //   src        — RS634 deob core (+ toolkit short names)
            //   fonts      — BitmapFont + software/GL/jaclib backends
            //   input      — MouseHandler / AWT mouse
            //   menu       — MenuEntry / DefaultClickSwapper
            //   void       — host extras (LoginPrefs, MobileKeyboard)
            //   microbot   — bot runtime / HUD / mouse backends
            //   rs2        — Rs2* scripting API (used by microbot)
            java.srcDirs(
                "src",
                "fonts",
                "input",
                "menu",
                "void",
                "microbot",
                "rs2",
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