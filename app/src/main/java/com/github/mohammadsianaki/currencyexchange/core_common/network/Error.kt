package com.github.mohammadsianaki.currencyexchange.core_common.network

import androidx.annotation.Keep
import androidx.annotation.StringRes
import com.github.mohammadsianaki.currencyexchange.R
import java.io.Serializable

@Keep
sealed class Error(@StringRes val messageRes: Int) : Serializable {
    object NetworkError : Error(R.string.network_error)
    object UnknownError : Error(R.string.unknown_error)

    sealed class HttpError(@StringRes val message: Int) : Error(message) {
        object UnAuthorized : HttpError(R.string.unauthorized_error)
        object BadRequest : HttpError(R.string.bad_request)
        object Forbidden : HttpError(R.string.forbidden_error)
        object NotFound : HttpError(R.string.not_found_error)
        object NotAcceptable : HttpError(R.string.not_acceptable_error)
        object InternalServer : Error(R.string.server_error)
    }
}