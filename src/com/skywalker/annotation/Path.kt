package com.skywalker.annotation


@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class Path(val value: String, val encoded: Boolean = false)
