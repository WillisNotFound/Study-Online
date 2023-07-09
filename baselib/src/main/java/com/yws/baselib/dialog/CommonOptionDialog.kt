package com.yws.baselib.dialog

import android.R.attr
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.yws.baselib.utils.ComposableSimulateWithLayoutParams
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setAsCenterDialogStyle
import com.yws.baselib.utils.setLinearLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class CommonOptionDialog : DialogFragment() {
    var options: List<Option> = emptyList()

    companion object {
        fun showDialog(fragmentManager: FragmentManager, options: List<Option>) {
            CommonOptionDialog().apply {
                this.options = options
            }.show(fragmentManager, toString())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LinearLayout(inflater.context).apply {
            setWidthHeight(64.dp, WRAP_CONTENT)
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            background = buildCustomShape(12.dpf, fillColor = 0xFFFFFFFF)

            options.forEachIndexed { index, option ->
                OptionRow(option.desc, option.onClick)
                if (index != options.size - 1) {
                    OptionDivider()
                }
            }
        }
    }

    @ComposableSimulateWithLayoutParams
    private fun LinearLayout.OptionRow(desc: String, onClick: () -> Unit) = TextView(context).addTo(this) {
        setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
            setMarginEx(0, 8.dp, 0, 8.dp)
        }
        foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1))
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
        setTextColor(0xFF000000)
        text = desc
        setOnClickListener {
            onClick()
            dismissAllowingStateLoss()
        }
    }

    @ComposableSimulateWithLayoutParams
    private fun LinearLayout.OptionDivider() = View(context).addTo(this) {
        setLinearLayoutParams(MATCH_PARENT, 1) {
            background = buildCustomShape(0F, fillColor = 0xFFEEEEEE)
        }
    }

    override fun onStart() {
        super.onStart()
        setAsCenterDialogStyle {
            width = 128.dp
            flags = flags or FLAG_DIM_BEHIND
            dimAmount = 0.5F
        }
    }
}

class Option(val desc: String, val onClick: () -> Unit)