
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.vados.liteenglishtranslator"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.vados.liteenglishtranslator"

        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility =  JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")

    //UI
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity-ktx:1.6.1")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Retrofit 2
    implementation(RETROFIT)
    implementation(RETROFIT_CONVERT_JSON)
    implementation(OKHTTP_INTERCEPTOR)
    implementation(RETROFIT_COROUTINES_ADAPTER)

    //Room
    implementation(ROOM_RUNTIME)
    kapt(ROOM_COMPILER)

    //Koin
    //Основная библиотека
    implementation(KOIN_CORE)
    //Koin для поддержки Android (Scope,ViewModel ...)
    implementation(KOIN_ANDROID)
    //Для совместимости с Java
    implementation(KOIN_ANDROID_COMPAT)

    //Glide
    implementation("com.github.bumptech.glide:glide:4.14.2")

    //Coil image download
    implementation("io.coil-kt:coil:2.1.0")

}