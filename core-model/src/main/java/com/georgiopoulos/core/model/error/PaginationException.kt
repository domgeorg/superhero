package com.georgiopoulos.core.model.error

import com.georgiopoulos.core.model.error.ErrorModel.UnknownErrorModel

data class PaginationException(
    val errorModel: ErrorModel,
) : Exception()

fun Throwable.getErrorMessageResId(): Int =
    (this as? PaginationException)?.errorModel?.errorMessageResId ?: UnknownErrorModel.errorMessageResId
