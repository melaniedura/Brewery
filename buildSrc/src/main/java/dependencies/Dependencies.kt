package dependencies

object Dependencies {
    const val kotlin_standard_library = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle_viewmodel}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val material = "com.google.android.material:material:${Versions.material_design}"
    const val navigation_fragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val photo_view = "com.github.chrisbanes:photoview:${Versions.photo_view}"
    const val lifecycle_livedata =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
    const val lifecycle_runtime =
        "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle_version}"
    const val lifecycle_common_java8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"
    const val lifecycle_viewmodel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val compose_runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val compose_ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val compose_foundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val compose_foundation_layout =
        "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val compose_material = "androidx.compose.material:material:${Versions.compose}"
    const val compose_runtime_livedata =
        "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
    const val compose_ui_tooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val compose_material_theme_adapter =
        "com.google.android.material:compose-theme-adapter:${Versions.compose}"
}