object Version {
    const val KOTLIN = "1.3.72"
    const val HILT = "2.28.3-alpha"
}

object ProjectConfig {
    const val GRADLE = "com.android.tools.build:gradle:4.0.0"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
    const val HILT_ANDROID_GRADLE_PLUGIN =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.HILT}"
}

object AndroidConfig {
    const val COMPILE_SDK = 29
    const val APP_ID = "com.beok.bookfinder"
    const val MIN_SDK = 21
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object Dependencies {
    private const val GLIDE_VER = "4.11.0"

    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"

    const val MATERIAL = "com.google.android.material:material:1.1.0"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.0"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"

    const val COROUTINE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9"

    const val TIMBER = "com.jakewharton.timber:timber:4.7.1"

    const val GLIDE = "com.github.bumptech.glide:glide:$GLIDE_VER"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:$GLIDE_VER"

    const val LOTTIE = "com.airbnb.android:lottie:3.4.1"
}

object TestDependencies {
    const val JUNIT_JUPITER = "org.junit.jupiter:junit-jupiter:5.6.2"
    const val ASSERTJ_CORE = "org.assertj:assertj-core:3.16.1"
    const val MOCK_WEB_SERVER = "com.squareup.okhttp3:mockwebserver:4.8.1"
}

object JetpackDependencies {
    private const val HILT_JETPACK_VER = "1.0.0-alpha02"
    private const val LIFECYCLE_VER = "2.2.0"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Version.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Version.HILT}"

    const val DAGGER_HILT_LIFECYCLE_VM = "androidx.hilt:hilt-lifecycle-viewmodel:$HILT_JETPACK_VER"
    const val HILT_COMPILER = "androidx.hilt:hilt-compiler:$HILT_JETPACK_VER"

    const val LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VER"
    const val LIFECYCLE_VIEWMODEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VER"

    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:1.2.0-alpha07"

    const val PAGING_RUNTIME_KTX = "androidx.paging:paging-runtime-ktx:2.1.2"
}

object NetworkDependencies {
    private const val RETROFIT_VER = "2.9.0"
    private const val MOSHI_VER = "1.9.3"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:$RETROFIT_VER"

    const val LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:3.14.2"

    const val CONVERTER_MOSHI = "com.squareup.retrofit2:converter-moshi:$RETROFIT_VER"
    const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:$MOSHI_VER"
    const val MOSHI_KOTLIN_CODEGEN = "com.squareup.moshi:moshi-kotlin-codegen:$MOSHI_VER"
}
