package com.yws.baselib.utils

import android.annotation.SuppressLint
import android.content.Context

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: app工具类
 * SinceVer:
 */

@SuppressLint("StaticFieldLeak")
lateinit var appContext: Context

fun initAppUtils(context: Context) {
    appContext = context.applicationContext
}