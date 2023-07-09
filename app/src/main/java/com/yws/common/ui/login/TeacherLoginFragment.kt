package com.yws.common.ui.login

import android.R.attr
import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.yws.baselib.data.BaseResult
import com.yws.baselib.services.toastService
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setMaxLength
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.widget.TopBar
import com.yws.common.ui.mteacher.MainTeacherActivity

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 教师登录界面
 * SinceVer:
 */
class TeacherLoginFragment : Fragment() {
    private val mViewModel by activityViewModels<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            TopBar("教师登录", buildCustomShape(0F, fillColor = 0xFFEEEEEE))

            val accountEdt = EditText(context).apply { id = View.generateViewId() }
            val passwordEdt = EditText(context).apply { id = View.generateViewId() }
            val loginBtn = TextView(context).apply { id = View.generateViewId() }

            accountEdt.addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 56.dp) {
                    topToTop = PARENT_ID
                    setMarginEx(12.dp, 60.dp, 12.dp, 0)
                }
                setPaddingEx(12.dp, 0, 12.dp, 0)
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                inputType = InputType.TYPE_CLASS_NUMBER
                setMaxLength(20)
                maxLines = 1
                hint = "工号"
            }

            passwordEdt.addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 56.dp) {
                    topToBottom = accountEdt.id
                    setMarginEx(12.dp, 12.dp, 12.dp, 0)
                }
                setPaddingEx(12.dp, 0, 12.dp, 0)
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
                inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                setMaxLength(20)
                maxLines = 1
                hint = "密码"
            }

            loginBtn.addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 56.dp) {
                    topToBottom = passwordEdt.id
                    setMarginEx(12.dp, 12.dp, 12.dp, 0)
                }
                background = buildCustomShape(12.dpf, fillColor = 0xFF52B45E)
                foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1))
                gravity = Gravity.CENTER
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                setTextColor(0xFFFFFFFF)
                text = "登录"
                setOnClickListener {
                    lifecycleScope.launchWhenResumed {
                        val result = mViewModel.teacherLogin(accountEdt.text.toString(), passwordEdt.text.toString())
                        when (result) {
                            is BaseResult.Success -> {
                                MainTeacherActivity.startAction(requireActivity())
                                toastService.showToast(result.get())
                            }

                            is BaseResult.Failure -> {
                                toastService.showErrorToast(result.get())
                            }
                        }
                    }
                }
            }

            TextView(context).addTo(this) {
                setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                    topToBottom = loginBtn.id
                    endToEnd = PARENT_ID
                    setMarginEx(0, 12.dp, 12.dp, 0)
                }
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                setTextColor(0xFF3DA6FE)
                paint.flags = Paint.UNDERLINE_TEXT_FLAG
                gravity = Gravity.CENTER
                text = "我是学生→"

                setOnClickListener {
                    mViewModel.switchToStudentLogin()
                }
            }
        }
    }
}