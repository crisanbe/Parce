import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.jetbrains.kotlin.config.JvmAnalysisFlags.useIR

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id ("com.google.protobuf") version ("0.8.12")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

   /* defaultConfig {
        // Required when setting minSdkVersion to 20 or lower
        multiDexEnabled = true
    }*/

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        //isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
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
    implementation(project(Modules.shared))
    implementation(project(Modules.network))
    implementation(project(Modules.core))
    implementation(project(Modules.components_ui))

    implementation(Compose.ui)
    debugImplementation(Compose.uiToolingPreview)
    implementation(Compose.hiltNavigationCompose)
    implementation(Compose.material)
    implementation(Compose.navigation)
    implementation(Compose.uiFoundation)
    implementation(Compose.composeAnimation)
    implementation(Compose.viewModelCompose)
    implementation(Compose.activityCompose)
    implementation(Compose.uiTooling)
    implementation(Compose.runtime)
    implementation(Compose.runtime_livedata)
    implementation(Compose.runtimeRxjava2)
    implementation(Compose.lifecycleRuntimeCompose)
    implementation(DaggerHilt.hiltAndroid)
    implementation(DaggerHilt.hiltCompilerNavigation)
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.airbnb.android:lottie-compose:5.2.0")
    implementation("com.google.accompanist:accompanist-placeholder:0.26.1-alpha")
    implementation("androidx.preference:preference-ktx:1.2.0")
    kapt(DaggerHilt.hiltCompiler)
    kapt(DaggerHilt.hiltCompilerAndroidx)

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.runtimeKtx)
    implementation(AndroidX.savedState)
    implementation(AndroidX.datastore_core)
    implementation(AndroidX.datastore_preferences)
    debugApi(AndroidX.customView)
    debugApi(AndroidX.customView_PoolingContainer)

    implementation(Room.room_ktx)
    implementation(Room.room_runtime)
    kapt(Room.room_compiler)

    implementation(Google.google_material)
    implementation(Google.protobuf)

    implementation(Coil.coilCompose)
    implementation(Coil.coilAccompanist)

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)
    implementation(Retrofit.retrofitConverterGson)
    implementation(Retrofit.retrofit2AdapterRrxjava)

    implementation(Coroutines.coroutines)
    implementation(Coroutines.coroutinesAndroid)

    implementation(ViewModelLiveData.lifecycleKtx)
    implementation(ViewModelLiveData.livedataKtx)
    implementation(ViewModelLiveData.livedataViewModel)
    implementation(ViewModelLiveData.androidxActivity)

    implementation(Gson.gson)
    implementation(Material.material)
    implementation(Material.materialGoogle)

    implementation(Accompanist.accompanistIndicators)
    implementation(Accompanist.accompanistPager)
    implementation(Accompanist.accompanistGlide)
    implementation(Accompanist.accompanistPermission)
    implementation(Accompanist.accompanistSystemUiController)
    implementation(Accompanist.accompanistNavigationAnimation)
    implementation(Accompanist.navigationCompose)

    testImplementation(Testing.junit4)
    testImplementation(Testing.junitAndroidExt)
    testImplementation(Testing.truth)
    testImplementation(Testing.coroutines)
    testImplementation(Testing.turbine)
    testImplementation(Testing.composeUiTest)
    testImplementation(Testing.coreTest)
    testImplementation(Testing.retrofitHamcrestTest)

    androidTestImplementation(Testing.junit4)
    androidTestImplementation(Testing.junitAndroidExt)
    androidTestImplementation(Testing.truth)
    androidTestImplementation(Testing.coroutines)
    androidTestImplementation(Testing.turbine)
    androidTestImplementation(Testing.composeUiTest)
    androidTestImplementation(Testing.hiltTesting)
    kaptAndroidTest(DaggerHilt.hiltCompiler)
    androidTestImplementation(Testing.testRunner)
}

protobuf {
    protoc {
        artifact = if (osdetector.os == "osx") {
            ("com.google.protobuf:protoc:3.14.0:osx-x86_64")
        } else {
            ("com.google.protobuf:protoc:3.14.0")
        }
    }
    generateProtoTasks {
        all().forEach {
            it.builtins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}


