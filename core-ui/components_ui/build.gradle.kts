import ProjectConfig.appId
import ProjectConfig.versionCode
import ProjectConfig.versionName

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        appId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode
        versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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

    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
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