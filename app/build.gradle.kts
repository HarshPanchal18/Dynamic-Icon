plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    val mainActivity = "com.example.dynamic_app_icon.MainActivity"
    val mainActivityAlias0 = "com.example.dynamic_app_icon.MainActivityAliasZero"
    val mainActivityAlias1 = "com.example.dynamic_app_icon.MainActivityAliasOne"

    namespace = "com.example.dynamic_app_icon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dynamic_app_icon"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        manifestPlaceholders.apply {
            set("main_activity", mainActivity)
            set("main_activity_alias_zero", mainActivityAlias0)
            set("main_activity_alias_one", mainActivityAlias1)
        }

        buildConfigField("String", "main_activity", "\"${mainActivity}\"")
        buildConfigField("String", "main_activity_alias_zero", "\"${mainActivityAlias0}\"")
        buildConfigField("String", "main_activity_alias_one", "\"${mainActivityAlias1}\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "main_activity", "\"${mainActivity}\"")
            buildConfigField("String", "main_activity_alias_zero", "\"${mainActivityAlias0}\"")
            buildConfigField("String", "main_activity_alias_one", "\"${mainActivityAlias1}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions.jvmTarget = "1.8"

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
