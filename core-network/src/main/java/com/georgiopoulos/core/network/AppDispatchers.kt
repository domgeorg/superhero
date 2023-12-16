package com.georgiopoulos.core.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

/**
 * An annotation used to qualify or distinguish dependencies in Dagger.
 * This annotation is retained at runtime and can be accessed and processed by the Dagger framework.
 */
@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(

    /**
     * The dispatchers (execution contexts) available in the application.
     */
    val appDispatchers: AppDispatchers,
)

/**
 * An enumeration representing different dispatchers (execution contexts) that can be used in the application.
 * The "IO" dispatcher typically represents input/output operations or other blocking tasks.
 */
enum class AppDispatchers {
    IO
}