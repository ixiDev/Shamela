plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        version = 1

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/licenses/**")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    implementation("com.healthmarketscience.jackcess:jackcess:4.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")

    implementation("io.ktor:ktor-client-android:1.2.5")
    implementation("io.ktor:ktor-client-logging-jvm:1.2.5")
    implementation("io.ktor:ktor-client-logging:1.2.5")
    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("com.github.omicronapps:7-Zip-JBinding-4Android:Release-16.02-2.02")

    val work_version = "2.5.0"
    implementation("androidx.work:work-runtime-ktx:$work_version")
    androidTestImplementation("androidx.work:work-testing:$work_version")
    implementation("androidx.work:work-multiprocess:$work_version")

    val room_version = "2.3.0"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")
}