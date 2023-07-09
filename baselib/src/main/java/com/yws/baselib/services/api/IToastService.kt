package com.yws.baselib.services.api

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc:
 * SinceVer:
 */
interface IToastService {
    fun showToast(msg: String)

    fun showErrorToast(msg: String)
}