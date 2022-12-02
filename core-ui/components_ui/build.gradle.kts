plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures { // Enables Jetpack Compose for this module
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeCompilerVersion
    }
}

dependencies {
    implementation(Compose.ui)
    implementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.runtime)
    implementation(Compose.navigation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.uiTooling)
    implementation(Compose.runtime_livedata)
    debugApi(AndroidX.customView)
    debugApi(AndroidX.customView_PoolingContainer)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.runtimeKtx)

    implementation(Coil.coilCompose)
    implementation(Coil.coilAccompanist)
    implementation(Material.material)
    implementation(Material.materialGoogle)

    implementation(Accompanist.accompanistIndicators)
    implementation(Accompanist.accompanistPager)
    implementation(Accompanist.accompanistGlide)
    implementation(Accompanist.accompanistPermission)
    implementation(Accompanist.accompanistSystemUiController)
    implementation(Accompanist.accompanistNavigationAnimation)
    implementation(Accompanist.navigationCompose)
}