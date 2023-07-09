package com.yws.baselib.services

import com.yws.baselib.services.api.ICommonDialogService
import com.yws.baselib.services.api.IToastService
import com.yws.baselib.services.impl.CommonDialogService
import com.yws.baselib.services.impl.ToastService

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 各种基础服务注入
 * SinceVer:
 */

/**
 * 弹窗服务
 */
val dialogService: ICommonDialogService get() = CommonDialogService

/**
 * Toast服务
 */
val toastService: IToastService get() = ToastService