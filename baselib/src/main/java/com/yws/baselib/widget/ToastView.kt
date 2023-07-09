package com.yws.baselib.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 自定义Toast
 * SinceVer:
 */
class ToastView(context: Context?) : LinearLayout(context) {
    private val iconView: ImageView
    private val textView: TextView
    private val backgroundDrawable: GradientDrawable

    init {
        //布局
        val paddingH: Int = 23.dp
        val paddingV: Int = 15.dp
        setPadding(paddingH, paddingV, paddingH, paddingV)
        iconView = ImageView(context)
        textView = TextView(context)
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        val iconSize: Int = 24.dp
        val iconParams = LayoutParams(iconSize, iconSize)
        iconParams.marginEnd = 8.dp
        addView(iconView, iconParams)
        val textParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        textView.gravity = Gravity.CENTER
        addView(textView, textParams)

        //设置背景，设置背景tint
        backgroundDrawable = GradientDrawable()
        backgroundDrawable.cornerRadius = 40.dpf
        backgroundDrawable.setColor(DEFAULT_BG_COLOR)
        backgroundDrawable.shape = GradientDrawable.RECTANGLE
        backgroundDrawable.alpha = 230
        background = backgroundDrawable

        //设置icon
        iconView.visibility = GONE

        //设置文字
        //sans-serif-condensed是系统默认
        textView.typeface = Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL)
        textView.textSize = 15f
        textView.setTextColor(Color.WHITE)
    }

    fun setText(msg: CharSequence, @ColorInt bgTint: Int, @DrawableRes iconRes: Int) {
        if (bgTint == 0) {
            backgroundDrawable.setColor(DEFAULT_BG_COLOR)
        } else {
            backgroundDrawable.setColor(bgTint)
        }
        if (iconRes == 0) {
            iconView.visibility = GONE
        } else {
            iconView.visibility = VISIBLE
            iconView.setImageResource(iconRes)
        }
        textView.text = msg
    }

    companion object {
        @ColorInt
        private val DEFAULT_BG_COLOR = Color.parseColor("#E6555555")
        private const val TOAST_TYPEFACE = "sans-serif"
    }
}