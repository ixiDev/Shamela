// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.0.0-beta07")
    val hilt_version by extra("2.35")
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-beta02")
        @Suppress("GradleDependency")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.32")
//        classpath("androidx.benchmark:benchmark-gradle-plugin:1.0.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt_version")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
