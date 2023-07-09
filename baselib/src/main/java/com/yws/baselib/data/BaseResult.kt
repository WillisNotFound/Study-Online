package com.yws.baselib.data

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
sealed class BaseResult<out T> {
    abstract fun get(): T
    class Success<out T>(private val value: T) : BaseResult<T>() {
        override fun get() = value

    }

    class Failure<out T>(private val value: T) : BaseResult<T>() {
        override fun get() = value

    }
}