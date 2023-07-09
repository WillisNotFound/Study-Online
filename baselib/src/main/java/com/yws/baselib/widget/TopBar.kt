package com.yws.baselib.widget

import android.R.attr
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import com.yws.baselib.utils.ComposableSimulate
import com.yws.baselib.utils.ComposableSimulateWithLayoutParams
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setTextColor

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 教师登录界面
 * SinceVer:
 */
@ComposableSimulateWithLayoutParams
fun ConstraintLayout.TopBar(
    title: String,
    backgroundDrawable: Drawable? = null,
    leftButton: TopBarButton? = null,
    rightButton: TopBarButton? = null
) = ConstraintLayout(context).addTo(this) {
    setConstraintLayoutParams(MATCH_PARENT, 48.dp) { topToTop = PARENT_ID }
    background = backgroundDrawable

    //标题
    TextView(context).addTo(this) {
        setConstraintLayoutParams(MATCH_PARENT, MATCH_PARENT) {
            marginStart = 36.dp
            marginEnd = 36.dp
        }
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
        setTextColor(0x99000000)
        ellipsize = TextUtils.TruncateAt.END
        gravity = Gravity.CENTER
        text = title
    }

    //左侧按钮
    leftButton?.let {
        InnerButton(leftButton).apply {
            setConstraintLayoutParams(24.dp, 24.dp) {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                marginStart = 12.dp
            }
        }
    }

    //右侧按钮
    rightButton?.let {
        InnerButton(rightButton).apply {
            setConstraintLayoutParams(24.dp, 24.dp) {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                endToEnd = PARENT_ID
                marginEnd = 12.dp
            }
        }
    }

    //最下方分割线
    View(context).addTo(this) {
        setConstraintLayoutParams(MATCH_PARENT, 1) {
            bottomToBottom = PARENT_ID
        }
        background = ColorDrawable().apply {
            color = 0x11111111
        }
    }
}

@ComposableSimulate
private fun ConstraintLayout.InnerButton(topBarButton: TopBarButton): ImageView {
    return ImageView(context).apply {
        addTo(this@InnerButton)
        setImageResource(topBarButton.icon)
        val bgSrc = Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1)
        if (bgSrc != -1) {
            setBackgroundResource(bgSrc)
        }
        if (topBarButton.onClick != null) {
            setOnClickListener { topBarButton.onClick.invoke() }
        }
    }
}

class TopBarButton(val icon: Int, val onClick: (() -> Unit)? = null)