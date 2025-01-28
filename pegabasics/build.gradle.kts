plugins {
    alias(libs.plugins.android.library)
    id("maven-publish") // ✅ Required for JitPack publishing
}

android {
    namespace = "com.pegalite.pegabasics"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

// ✅ FIX: Manually define the library component for publishing
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.pegalitestudio" // Change to your GitHub username
                artifactId = "pegabasics"            // Your library name
                version = "1.0.0"                    // Your library version

                // ✅ Explicitly specify the AAR file for Android Libraries
                afterEvaluate {
                    artifact(tasks.getByName("bundleReleaseAar"))
                }
            }
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.daimajia)

    // For Lottie Animation
    implementation(libs.lottie)

    /* For Responsive Layout */
    implementation(libs.sdp.android)
    implementation(libs.ssp.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
