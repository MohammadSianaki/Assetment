package com.github.mohammadsianaki.currencyexchange.core_common.network

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketException


fun Throwable.asReadableError(): Error {
    return when (this) {
        is SocketException -> Error.NetworkError
        is IOException -> Error.NetworkError
        is HttpException -> extractHttpError()
        else -> Error.UnknownError
    }
}


private fun HttpException.extractHttpError(): Error {
    return try {
        when (code()) {
            ErrorCodes.UNAUTHORIZED.code -> Error.HttpError.UnAuthorized
            ErrorCodes.BAD_REQUEST.code -> Error.HttpError.BadRequest
            ErrorCodes.FORBIDDEN.code -> Error.HttpError.Forbidden
            ErrorCodes.NOT_FOUND.code -> Error.HttpError.NotFound
            ErrorCodes.NOT_ACCEPTABLE.code -> Error.HttpError.NotAcceptable
            ErrorCodes.INTERNAL_SERVER.code -> Error.HttpError.InternalServer
            else -> Error.UnknownError
        }
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
        Error.UnknownError
    }
}
