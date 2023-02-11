package com.github.mohammadsianaki.currencyexchange.core_common.network

enum class ErrorCodes(val code: Int) {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    NOT_ACCEPTABLE(406),
    INTERNAL_SERVER(500)
}
