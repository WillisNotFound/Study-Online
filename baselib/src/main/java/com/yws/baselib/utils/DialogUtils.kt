package com.yws.baselib.utils

import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.yws.baselib.R

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 对话框工具类
 * SinceVer:
 */

fun DialogFragment.setAsLiveBottomDialogStyle(customAttributes: (WindowManager.LayoutParams.() -> Unit)? = null) {
    dialog?.window?.run {
        decorView.setPadding(0, 0, 0, 0)
        attributes = attributes.apply {
            width = MATCH_PARENT
            height = WRAP_CONTENT
            gravity = Gravity.BOTTOM
            windowAnimations = R.style.Base_BottomDialogAnimation
            dimAmount = 0f
            customAttributes?.invoke(this)
        }
        setBackgroundDrawable(ColorDrawable())
    }
}

fun DialogFragment.setAsLiveRightDialogStyle(customAttributes: (WindowManager.LayoutParams.() -> Unit)? = null) {
    dialog?.window?.run {
        decorView.setPadding(0, 0, 0, 0)
        attributes = attributes.apply {
            width = 294f.dp
            height = MATCH_PARENT
            gravity = Gravity.RIGHT
            customAttributes?.invoke(this)
        }
        setBackgroundDrawable(ColorDrawable())
    }
}

fun DialogFragment.setAsCenterDialogStyle(customAttributes: (WindowManager.LayoutParams.() -> Unit)? = null) {
    dialog?.window?.run {
        decorView.setPadding(0, 0, 0, 0)
        attributes = attributes.apply {
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            gravity = Gravity.CENTER
            customAttributes?.invoke(this)
        }
        setBackgroundDrawable(ColorDrawable())
    }
}