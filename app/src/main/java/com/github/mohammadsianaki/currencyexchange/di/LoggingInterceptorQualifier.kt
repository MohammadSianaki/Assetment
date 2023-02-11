package com.github.mohammadsianaki.currencyexchange.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LoggingInterceptorQualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckerInterceptorQualifier