object Version {
    const val KOTLIN = "1.3.72"
}

object ProjectConfig {
    const val GRADLE = "com.android.tools.build:gradle:4.0.0"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.KOTLIN}"
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
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Version.KOTLIN}"

    const val MATERIAL = "com.google.android.material:material:1.1.0"
    const val CORE_KTX = "androidx.core:core-ktx:1.3.0"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"

}

object TestDependencies {
    const val JUNIT = "junit:junit:4.13"
    const val EXT_JUNIT = "androidx.test.ext:junit:1.1.1"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.2.0"
}
