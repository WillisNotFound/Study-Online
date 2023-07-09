package com.yws.baselib.utils

import android.graphics.drawable.Drawable

import android.graphics.drawable.GradientDrawable

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: shape工具类
 * SinceVer:
 */

fun buildCustomShape(
    radius: Float = 0f,
    gradient: Gradient? = null,
    stroke: Stroke? = null,
    fillColor: Long? = null,
    shape: Int = GradientDrawable.RECTANGLE
): Drawable {
    return buildCustomShape(
        cornetRadius = CornetRadius(radius, radius, radius, radius),
        gradient = gradient,
        stroke = stroke,
        fillColor = fillColor,
        shape = shape
    )
}

fun buildCustomShape(
    cornetRadius: CornetRadius? = null,
    gradient: Gradient? = null,
    stroke: Stroke? = null,
    fillColor: Long? = null,
    shape: Int = GradientDrawable.RECTANGLE
): Drawable {
    val gradientDrawable = GradientDrawable()
    gradientDrawable.shape = shape
    cornetRadius?.let {
        gradientDrawable.cornerRadii = floatArrayOf(
            it.topLeftRadius,
            it.topLeftRadius,
            it.topRightRadius,
            it.topRightRadius,
            it.bottomRightRadius,
            it.bottomRightRadius,
            it.bottomLeftRadius,
            it.bottomLeftRadius
        )
    }
    gradient?.let {
        gradientDrawable.gradientType = it.gradientType
        gradientDrawable.colors = it.gradientColors
        it.angle?.run {
            handleAngle(this, gradientDrawable)
        }
    }
    stroke?.let {
        gradientDrawable.setStroke(it.strokeWidth, it.strokeColor.toInt(), it.dashWidth, it.dashGap)
    }
    fillColor?.let {
        gradientDrawable.setColor(it.toInt())
    }
    return gradientDrawable
}

/**
 * 圆角
 */
class CornetRadius(
    val topLeftRadius: Float = 0f,
    val topRightRadius: Float = 0f,
    val bottomLeftRadius: Float = 0f,
    val bottomRightRadius: Float = 0f
)

/**
 * 渐变
 */
class Gradient(
    val gradientType: Int = GradientDrawable.LINEAR_GRADIENT,
    val gradientColors: IntArray,
    val angle: Int? = null
)

/**
 * 描边
 */
class Stroke(
    val strokeWidth: Int = 10,
    val strokeColor: Long = 0,
    val dashWidth: Float = 0f,
    val dashGap: Float = 0f,
)


/**
 * 渐变angle处理
 */
private fun handleAngle(target: Int, gradientDrawable: GradientDrawable) {
    if (gradientDrawable.gradientType == GradientDrawable.LINEAR_GRADIENT) {
        val orientation: GradientDrawable.Orientation =
            when ((target % 360 + 360) % 360) {
                0 -> GradientDrawable.Orientation.LEFT_RIGHT
                45 -> GradientDrawable.Orientation.BL_TR
                90 -> GradientDrawable.Orientation.BOTTOM_TOP
                135 -> GradientDrawable.Orientation.BR_TL
                180 -> GradientDrawable.Orientation.RIGHT_LEFT
                225 -> GradientDrawable.Orientation.TR_BL
                270 -> GradientDrawable.Orientation.TOP_BOTTOM
                315 -> GradientDrawable.Orientation.TL_BR
                else -> GradientDrawable.Orientation.TOP_BOTTOM // 默认应该是这个
            }
        gradientDrawable.orientation = orientation
    }
}