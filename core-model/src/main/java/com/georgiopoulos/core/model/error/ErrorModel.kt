package com.georgiopoulos.core.model.error

import com.georgiopoulos.core_resources.R as Resources

sealed class ErrorModel(open val errorMessageResId: Int) {
    data object UnknownErrorModel : ErrorModel(Resources.string.error_unknown)
}

sealed class NetworkErrorModel(override val errorMessageResId: Int) : ErrorModel(errorMessageResId) {
    data object Network : NetworkErrorModel(Resources.string.error_network)
    data object NotFound : NetworkErrorModel(Resources.string.error_not_found)
    data object NotAuthorized : NetworkErrorModel(Resources.string.error_not_authorized)
    data object ServiceUnavailable : NetworkErrorModel(Resources.string.error_service_unavailable)
    data object ServiceNotWorking : NetworkErrorModel(Resources.string.error_service_not_working)
    data object Unknown : NetworkErrorModel(Resources.string.error_unknown)
}
