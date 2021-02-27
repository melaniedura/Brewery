package dependencies

object TestDependencies {
    const val junit = "junit:junit:${Versions.junit_4_version}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito_inline}"
    const val mockito_kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    const val core_testing = "androidx.arch.core:core-testing:${Versions.core_testing}"
    const val coroutines_test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
}