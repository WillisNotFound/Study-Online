package com.yws.baselib.utils

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.text.InputFilter
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

//======View展示隐藏相关-start======
fun <T : View> T.gone() {
    visibility = View.GONE
}

fun <T : View> T.visible() {
    visibility = View.VISIBLE
}

fun <T : View> T.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

fun View.addTo(viewGroup: ViewGroup) {
    viewGroup.addView(this)
}

fun View.addTo(viewGroup: ViewGroup, width: Int, height: Int) {
    viewGroup.addView(this, width, height)
}

inline fun <T : View> T.addTo(viewGroup: ViewGroup, action: T.() -> Unit) {
    action()
    viewGroup.addView(this)
}

fun View.removeFromParent() {
    (parent as? ViewGroup)?.removeView(this)
}
//======View展示隐藏相关-end======

//======输入法相关-start======
private val inputMethodManager get() = appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun View.hideSoftKeyboard() {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSoftKeyboard() {
    isFocusable = true
    requestFocus()
    inputMethodManager.showSoftInput(this, 2)
}

fun Fragment.hideKeyboard() {
    context?.let {
        ContextCompat.getSystemService(it, InputMethodManager::class.java)?.toggleSoftInput(
            InputMethodManager.HIDE_IMPLICIT_ONLY, 0
        )
    }
}
//======输入法相关-end======

//======尺寸相关-start======
val displayMetrics: DisplayMetrics by lazy { appContext.resources.displayMetrics }
val Float.dp: Int get() = (this * displayMetrics.density).toInt()
val Int.dp: Int get() = (this * displayMetrics.density).toInt()
val Int.dpf: Float get() = (this * displayMetrics.density)
val Float.dpf: Float get() = (this * displayMetrics.density)
val Float.sp2px: Float get() = this * displayMetrics.scaledDensity + 0.5f
//======尺寸相关-end======

//======控件LayoutParams相关-start======
fun View.setWidthHeight(width: Int, height: Int) {
    layoutParams = ViewGroup.LayoutParams(width, height)
}

inline fun View.setWidthHeightParams(
    width: Int,
    height: Int,
    block: ViewGroup.MarginLayoutParams.() -> Unit
) {
    layoutParams = ViewGroup.MarginLayoutParams(width, height).apply(block)
}

fun View.setLinearWidthHeight(width: Int, height: Int) {
    layoutParams = LinearLayout.LayoutParams(width, height)
}

fun View.setLinearWeight(width: Int, height: Int, weight: Float) {
    layoutParams = LinearLayout.LayoutParams(width, height, weight)
}

inline fun View.setLinearLayoutParams(
    width: Int,
    height: Int,
    block: LinearLayout.LayoutParams.() -> Unit
) {
    layoutParams = LinearLayout.LayoutParams(width, height).apply { block() }
}

inline fun View.setLinearLayoutWeightParams(
    width: Int,
    height: Int,
    weight: Float,
    block: LinearLayout.LayoutParams.() -> Unit
) {
    layoutParams = LinearLayout.LayoutParams(width, height, weight).apply { block() }
}

inline fun View.setConstraintLayoutParams(
    width: Int,
    height: Int,
    block: ConstraintLayout.LayoutParams.() -> Unit
) {
    layoutParams = ConstraintLayout.LayoutParams(width, height).apply(block)
}

inline fun View.setFrameLayoutWidthHeight(
    width: Int,
    height: Int,
    block: FrameLayout.LayoutParams.() -> Unit
) {
    layoutParams = FrameLayout.LayoutParams(width, height).apply(block)
}
//======控件LayoutParams相关-start======

//======其他-start======
fun TextView.setTextColor(color: Long) {
    setTextColor(color.toInt())
}

fun TextView.setMaxLength(maxLength: Int) {
    filters += InputFilter.LengthFilter(maxLength)
}

fun ViewGroup.MarginLayoutParams.setMarginEx(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    setMargins(left, top, right, bottom)
}

fun View.setPaddingEx(
    left: Int = 0,
    top: Int = 0,
    right: Int = 0,
    bottom: Int = 0
) {
    setPadding(left, top, right, bottom)
}

fun View.getDrawable(resId: Int): Drawable? {
    return context.getDrawable(resId)
}
//======其他-end======

/**
 * 假装自己是Compose
 */
@DslMarker
annotation class ComposableSimulate

/**
 * 假装自己是Compose，并且自带LayoutParams
 */
@DslMarker
annotation class ComposableSimulateWithLayoutParams
