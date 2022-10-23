object Testing {
    private const val CoreTesting = "2.1.0"
    private const val retrofitHamcrestTesting = "2.2"
    private const val junitVersion = "4.13.2"
    private const val junitAndroidExtVersion = "1.1.3"
    private const val coreEspressoVersion = "3.4.0"
    private const val coroutinesTestVersion = "1.6.4"
    private const val truthVersion = "1.1.3"
    private const val mockkVersion = "1.12.7"
    private const val turbineVersion = "0.9.0"
    private const val mockWebServerVersion = "4.10.0"
    private const val testRunnerVersion = "1.4.0"
    const val coreTest = "androidx.arch.core:core-testing:$CoreTesting"
    const val retrofitHamcrestTest = "org.hamcrest:hamcrest:$retrofitHamcrestTesting"
    const val junit4 = "junit:junit:$junitVersion"
    const val junitAndroidExt = "androidx.test.ext:junit:$junitAndroidExtVersion"
    const val coreEspresso = "androidx.test.espresso:espresso-core:$coreEspressoVersion"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Compose.composeVersion}"
    const val composeTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesTestVersion"
    const val truth = "com.google.truth:truth:$truthVersion"
    const val mockk = "io.mockk:mockk:$mockkVersion"
    const val mockkAndroid = "io.mockk:mockk-android:$mockkVersion"
    const val turbine = "app.cash.turbine:turbine:$turbineVersion"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:$mockWebServerVersion"
    const val composeUiTest = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val hiltTesting = "com.google.dagger:hilt-android-testing:${DaggerHilt.version}"
    const val testRunner = "androidx.test:runner:$testRunnerVersion"
}
