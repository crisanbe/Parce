object DaggerHilt {
    const val version = "2.44"
    private const val versionHiltAndroidx = "1.0.0"
    private const val versionHiltNavigation = "1.0.0"
    private const val versionHiltLifecycle = "1.0.0-alpha03"
    const val hiltAndroid = "com.google.dagger:hilt-android:$version"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
    const val hiltCompilerAndroidx = "androidx.hilt:hilt-compiler:$versionHiltAndroidx"
    const val hiltCompilerNavigation = "androidx.hilt:hilt-navigation-compose:$versionHiltNavigation"
    const val hiltLifecycle = "androidx.hilt:hilt-lifecycle-viewmodel:$versionHiltLifecycle"
}
