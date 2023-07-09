package com.yws.baselib.services.impl

import android.widget.Toast
import com.yws.baselib.services.api.IToastService
import com.yws.baselib.utils.appContext
import com.yws.baselib.widget.ToastView

object ToastService : IToastService {
    private val sToast = Toast(appContext).apply { view = ToastView(appContext) }
    private val sErrorToast = Toast(appContext).apply { view = ToastView(appContext) }

    override fun showToast(msg: String) {
        (sToast.view as? ToastView)?.setText(msg, 0, 0)
        sToast.duration = Toast.LENGTH_SHORT
        sToast.show()
    }

    override fun showErrorToast(msg: String) {
        (sErrorToast.view as? ToastView)?.setText(msg, 0xFFDC4437.toInt(), 0)
        sErrorToast.duration = Toast.LENGTH_SHORT
        sErrorToast.show()
    }
}