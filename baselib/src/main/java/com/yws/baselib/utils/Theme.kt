package com.yws.baselib.utils

import android.content.res.TypedArray

object Theme {

    object Color {
        const val T1 = 0xFF131737.toInt()
        const val T2 = 0xFF848896.toInt()

        const val M1 = 0xFFFF4D4D.toInt()
        const val M3 = 0xFFFF3355.toInt()

        const val L1 = 0xFFEAEAEA.toInt()
    }

    object Attr {
        /**
         * 获取资源Id
         */
        fun getResourceId(attr: Int, defValue: Int): Int {
            val attrs = intArrayOf(attr)
            val typedArray: TypedArray = appContext.theme.obtainStyledAttributes(attrs)
            val resourceId = typedArray.getResourceId(0, defValue)
            typedArray.recycle()
            return resourceId
        }
    }
}