package com.yws.common.ui.mteacher.mine

import android.R.attr
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.CHAIN_SPREAD
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.yws.baselib.services.toastService
import com.yws.baselib.utils.ComposableSimulateWithLayoutParams
import com.yws.baselib.utils.CornetRadius
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setLinearLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.widget.TopBar
import com.yws.common.R
import com.yws.common.func.teacherManager
import com.yws.common.ui.login.LoginActivity

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
class MineFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            TopBar("我的", buildCustomShape(0F, fillColor = 0xFFEEEEEE))
            LinearLayout(context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, WRAP_CONTENT) {
                    topToTop = PARENT_ID
                    setMarginEx(12.dp, 60.dp, 12.dp, 0)
                }
                orientation = LinearLayout.VERTICAL

                ProfileCard()
                OptionRow("关于我们") { toastService.showToast("关于我们") }
                OptionRow("切换账号") {
                    lifecycleScope.launchWhenResumed {
                        teacherManager.unLogin()
                        LoginActivity.startAction(requireActivity())
                    }
                }
            }

        }
    }

    @ComposableSimulateWithLayoutParams
    private fun LinearLayout.ProfileCard() = ConstraintLayout(context).addTo(this) {
        setLinearLayoutParams(MATCH_PARENT, 96.dp) {}
        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)

        val avatarIv = ImageView(context).apply { id = View.generateViewId() }
        val nameTv = TextView(context).apply { id = View.generateViewId() }
        val idTv = TextView(context).apply { id = View.generateViewId() }

        avatarIv.addTo(this) {
            setConstraintLayoutParams(56.dp, 56.dp) {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                setMarginEx(12.dp, 0, 0, 0)
            }
            setImageResource(R.drawable.ic_avatar_64dp)
        }

        nameTv.addTo(this) {
            setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                topToTop = avatarIv.id
                bottomToTop = idTv.id
                startToEnd = avatarIv.id
                verticalChainStyle = CHAIN_SPREAD
                setMarginEx(12.dp, 0, 0, 0)
            }
            maxLines = 1
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
            setTextColor(0xFF000000)
            typeface = Typeface.DEFAULT_BOLD
            text = teacherManager.current?.name
        }

        idTv.addTo(this) {
            setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                topToBottom = nameTv.id
                bottomToBottom = avatarIv.id
                startToEnd = avatarIv.id
                setMarginEx(12.dp, 0, 0, 0)
            }
            maxLines = 1
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12F)
            setTextColor(0x99000000)
            text = teacherManager.current?.id.toString()
        }
    }

    @ComposableSimulateWithLayoutParams
    private fun LinearLayout.OptionRow(desc: String, onClick: () -> Unit) = ConstraintLayout(context).apply {
        addTo(this@OptionRow)
        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
            topMargin = 12.dp
        }
        background = buildCustomShape(CornetRadius(12.dpf, 12.dpf, 12.dpf, 12.dpf), fillColor = 0xFFEEEEEE)
        foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackground, -1))

        TextView(context).addTo(this) {
            setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                startToStart = PARENT_ID
                marginStart = 12.dp
            }
            text = desc
        }

        ImageView(context).addTo(this) {
            setConstraintLayoutParams(16.dp, 16.dp) {
                topToTop = PARENT_ID
                bottomToBottom = PARENT_ID
                endToEnd = PARENT_ID
                marginEnd = 12.dp
            }
            setImageResource(R.drawable.ic_left_24dp)
        }

        setOnClickListener { onClick() }
    }
}