package dependencies

object Build {
    const val build_tools = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val dagger_hilt_gradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val navigation_safe_args_gradle = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}
