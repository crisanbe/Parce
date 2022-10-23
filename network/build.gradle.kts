apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(Retrofit.okHttp)
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.okHttpLoggingInterceptor)
    "implementation"(Retrofit.moshiConverter)
    "implementation"(Retrofit.retrofitConverterGson)
    "implementation"(Retrofit.retrofit2AdapterRrxjava)
}
