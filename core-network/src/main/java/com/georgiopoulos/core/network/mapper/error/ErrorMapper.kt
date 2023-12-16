package com.georgiopoulos.core.network.mapper.error

import com.georgiopoulos.core.model.error.ErrorModel

/**
 * The ErrorMapper interface is responsible for mapping various exceptions to corresponding Error objects.
 * It provides a centralized way of handling and categorizing different types of exceptions.
 *
 */
interface ErrorMapper {

    /**
     * Maps the given exception to a ErrorModel object.
     *
     * @param exception The exception to be mapped.
     * @return The [ErrorModel] corresponding to the mapped exception.
     */

    fun mapError(exception: Throwable): ErrorModel
}