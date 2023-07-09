package com.yws.baselib.utils

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
fun String.toIntSafely(): Int {
    return try {
        this.toInt()
    } catch (e: NumberFormatException) {
        0
    }
}

fun String.toFloatSafely(): Float {
    return try {
        this.toFloat()
    } catch (e: NumberFormatException) {
        0F
    }
}
