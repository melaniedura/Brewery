package dependencies

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit_4_version}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    const val room_testing = "androidx.room:room-testing:${Versions.room}"
    const val core_testing = "android.arch.core:core-testing:${Versions.core_testing}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
}