package com.yws.common.ui.mteacher.subject

import android.graphics.Typeface
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.setLinearLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setMaxLength
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.common.func.subject.SubjectDifficulty
import com.yws.common.func.subjectManager

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class SubjectInfoFragment : Fragment() {
    private var mSubjectId: Int? = null

    companion object {
        fun newInstance(fragmentManager: FragmentManager, subjectId: Int): SubjectInfoFragment {
            return SubjectInfoFragment().apply {
                this.mSubjectId = subjectId
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            lifecycleScope.launchWhenResumed {
                subjectManager.queryById(mSubjectId ?: -1)?.let { mSubject ->
                    NestedScrollView(context).addTo(this@apply) {
                        setWidthHeight(MATCH_PARENT, MATCH_PARENT)

                        LinearLayout(context).addTo(this) {
                            setWidthHeight(MATCH_PARENT, MATCH_PARENT)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            LinearLayout(context).addTo(this) {
                                setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                                    setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                                }
                                setPaddingEx(12.dp, 0, 12.dp, 0)
                                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                                orientation = LinearLayout.HORIZONTAL
                                gravity = Gravity.CENTER_VERTICAL or Gravity.START

                                TextView(context).addTo(this) {
                                    setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                        setMarginEx(12.dp, 0, 12.dp, 0)
                                    }
                                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                    setTextColor(0xFF52B45E)
                                    text = "[${mSubject.type}]"
                                }

                                TextView(context).addTo(this) {
                                    setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                        setMarginEx(12.dp, 0, 12.dp, 0)
                                    }
                                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                    text = "[${mSubject.difficulty}]"
                                    when (mSubject.difficulty) {
                                        SubjectDifficulty.Simple -> {
                                            setTextColor(0xFF52B45E)
                                        }

                                        SubjectDifficulty.Medium -> {
                                            setTextColor(0xFFFCAF00)
                                        }

                                        SubjectDifficulty.Difficult -> {
                                            setTextColor(0xFFDC1B00)
                                        }

                                        else -> {}
                                    }
                                }

                                TextView(context).addTo(this) {
                                    setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                        setMarginEx(12.dp, 0, 12.dp, 0)
                                    }
                                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                                    setTextColor(0xFFDC1B00)
                                    text = mSubject?.score.toString()
                                }

                                TextView(context).addTo(this) {
                                    setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                        setMarginEx(12.dp, 0, 12.dp, 0)
                                    }
                                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                                    setTextColor(0x99000000)
                                    text = mSubject.points
                                    isSingleLine = true
                                    setMaxLength(8)
                                    ellipsize = TextUtils.TruncateAt.END
                                }
                            }

                            //内容
                            TextView(context).addTo(this) {
                                setLinearLayoutParams(MATCH_PARENT, 36.dp) {
                                    marginStart = 12.dp
                                }
                                gravity = Gravity.START
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                setTextColor(0x99000000)
                                typeface = Typeface.DEFAULT_BOLD
                                isSingleLine = false
                                text = "内容："
                            }
                            TextView(context).addTo(this) {
                                setLinearLayoutParams(MATCH_PARENT, WRAP_CONTENT) {
                                    setMarginEx(12.dp, 0.dp, 12.dp, 12.dp)
                                }
                                minHeight = 64.dp
                                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                                setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                setTextColor(0x99000000)
                                gravity = Gravity.START
                                isSingleLine = false
                                text = mSubject.content.simpleDesc
                            }

                            //答案
                            TextView(context).addTo(this) {
                                setLinearLayoutParams(MATCH_PARENT, 36.dp) {
                                    marginStart = 12.dp
                                }
                                gravity = Gravity.START
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                setTextColor(0x99000000)
                                typeface = Typeface.DEFAULT_BOLD
                                isSingleLine = false
                                text = "答案："
                            }
                            TextView(context).addTo(this) {
                                setLinearLayoutParams(MATCH_PARENT, WRAP_CONTENT) {
                                    setMarginEx(12.dp, 0.dp, 12.dp, 12.dp)
                                }
                                minHeight = 64.dp
                                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                                setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                                setTextColor(0x99000000)
                                gravity = Gravity.START
                                isSingleLine = false
                                text = mSubject.answer.simpleDesc
                            }
                        }
                    }
                }
            }
        }
    }
}