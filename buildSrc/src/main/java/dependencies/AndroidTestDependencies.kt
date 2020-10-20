package dependencies

object AndroidTestDependencies {
    const val junit_test_ext = "androidx.test.ext:junit:${Versions.androidx_test_ext}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val test_runner = "androidx.test:runner:${Versions.test_runner}"
    const val test_rules = "androidx.test:rules:${Versions.test_rules}"
    const val test_core_ktx = "androidx.test:core-ktx:${Versions.test_core_ktx}"
    const val test_ext_truth = "androidx.test.ext:truth:${Versions.test_ext_truth}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val instrumentation_runner = "androidx.test.runner.AndroidJUnitRunner"
}