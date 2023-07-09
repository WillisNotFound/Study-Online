package com.yws.common.ui.mteacher.subject

import android.R.attr
import android.os.Bundle
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.yws.baselib.utils.CornetRadius
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setAsLiveBottomDialogStyle
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setLinearLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setMaxLength
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.utils.toFloatSafely
import com.yws.baselib.widget.TopBar
import com.yws.common.R
import com.yws.common.func.subject.Subject
import com.yws.common.func.subject.SubjectAnswer
import com.yws.common.func.subject.SubjectContent
import com.yws.common.func.subject.SubjectDifficulty
import com.yws.common.func.subject.SubjectType
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
class SubjectAddDialog : DialogFragment() {
    var mContinuation: Continuation<Subject?>? = null
    private var mResult: Subject? = null

    companion object {
        suspend fun showDialog(fragmentManager: FragmentManager): Subject? {
            return suspendCancellableCoroutine { continuation ->
                SubjectAddDialog().apply {
                    this.mContinuation = continuation
                }.show(fragmentManager, toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)
            background = buildCustomShape(12.dpf, fillColor = 0xFFFFFFFF)

            TopBar("添加题目", buildCustomShape(CornetRadius(12.dpf, 12.dpf, 0F, 0F), fillColor = 0xFFEEEEEE))

            NestedScrollView(context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, MATCH_PARENT) {
                    topMargin = 48.dp
                }

                LinearLayout(context).addTo(this) {
                    setWidthHeight(MATCH_PARENT, MATCH_PARENT)
                    orientation = LinearLayout.VERTICAL
                    gravity = Gravity.CENTER_HORIZONTAL

                    var subjectType: SubjectType = SubjectType.FillIn
                    var difficulty: SubjectDifficulty = SubjectDifficulty.Simple
                    var content: SubjectContent = SubjectContent.Text("")
                    var answer: SubjectAnswer = SubjectAnswer.Text("")

                    val pointsEdt = EditText(context)
                    val scoreEdt = EditText(context)
                    val contentEdt = EditText(context)
                    val answerEdt = EditText(context)

                    //题型
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        val array = arrayOf(SubjectType.FillIn, SubjectType.Choice, SubjectType.SUBJECTIVITY)
                        TextView(context).addTo(this) {
                            setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                marginEnd = 12.dp
                            }
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "题型："
                        }

                        Spinner(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            adapter = ArrayAdapter(context, R.layout.item_text, array)
                            dropDownWidth = 64.dp
                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    subjectType = array[position]
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        }
                    }

                    //难度
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        val array = arrayOf(
                            SubjectDifficulty.Simple,
                            SubjectDifficulty.Medium,
                            SubjectDifficulty.Difficult
                        )
                        TextView(context).addTo(this) {
                            setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                marginEnd = 12.dp
                            }
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "难度："
                        }

                        Spinner(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            adapter = ArrayAdapter(context, R.layout.item_text, array)
                            dropDownWidth = 64.dp

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    difficulty = array[position]
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        }
                    }

                    //知识点
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        TextView(context).addTo(this) {
                            setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                marginEnd = 12.dp
                            }
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "知识点："
                        }

                        pointsEdt.addTo(this) {
                            setWidthHeight(MATCH_PARENT, WRAP_CONTENT)
                            background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                            setPaddingEx(12.dp, 0, 12.dp, 0)
                            gravity = Gravity.START or Gravity.CENTER_VERTICAL
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            inputType = InputType.TYPE_CLASS_TEXT
                            setMaxLength(20)
                            isSingleLine = true
                        }
                    }

                    //分数
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        TextView(context).addTo(this) {
                            setLinearLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                                marginEnd = 12.dp
                            }
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "分数："
                        }

                        scoreEdt.addTo(this) {
                            setWidthHeight(MATCH_PARENT, WRAP_CONTENT)
                            background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                            setPaddingEx(12.dp, 0, 12.dp, 0)
                            gravity = Gravity.START or Gravity.CENTER_VERTICAL
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            inputType = InputType.TYPE_CLASS_NUMBER
                            setMaxLength(2)
                            isSingleLine = true
                        }
                    }

                    //内容类型
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        val array = arrayOf(SubjectContent.Text(""))
                        TextView(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "内容类型："
                        }

                        Spinner(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            adapter = ArrayAdapter(context, R.layout.item_text, array)
                            dropDownWidth = 64.dp

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    content = array[position]
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        }
                    }

                    //内容
                    contentEdt.addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 128.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                        gravity = Gravity.START
                        minHeight = 64.dp
                        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                        inputType = InputType.TYPE_CLASS_TEXT
                        setMaxLength(100)
                        isSingleLine = false
                        hint = "题目内容（100字以内）"
                    }

                    //答案类型
                    LinearLayout(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        setPaddingEx(12.dp, 0, 12.dp, 0)
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        orientation = LinearLayout.HORIZONTAL
                        gravity = Gravity.CENTER_VERTICAL

                        val array = arrayOf(SubjectAnswer.Text(""))
                        TextView(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                            setTextColor(0x99000000)
                            text = "答案类型："
                        }

                        Spinner(context).addTo(this) {
                            setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                            adapter = ArrayAdapter(context, R.layout.item_text, array)
                            dropDownWidth = 64.dp

                            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    parent: AdapterView<*>?,
                                    view: View?,
                                    position: Int,
                                    id: Long
                                ) {
                                    answer = array[position]
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {

                                }
                            }
                        }
                    }

                    //答案
                    answerEdt.addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 128.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                        setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                        gravity = Gravity.START
                        minHeight = 64.dp
                        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                        inputType = InputType.TYPE_CLASS_TEXT
                        setMaxLength(100)
                        isSingleLine = false
                        hint = "题目答案（100字以内）"
                    }

                    //保存按钮
                    TextView(context).addTo(this) {
                        setLinearLayoutParams(MATCH_PARENT, 48.dp) {
                            setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                        }
                        background = buildCustomShape(12.dpf, fillColor = 0xFF52B45E)
                        foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1))
                        gravity = Gravity.CENTER
                        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                        setTextColor(0xFFFFFFFF)
                        text = "保存"
                        setOnClickListener {
                            mResult = Subject(
                                -1,
                                scoreEdt.text.toString().toFloatSafely(),
                                difficulty,
                                subjectType,
                                pointsEdt.text.toString(),
                                SubjectContent.Text(contentEdt.text.toString()),
                                SubjectAnswer.Text(answerEdt.text.toString())
                            )
                            dismissAllowingStateLoss()
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setAsLiveBottomDialogStyle {
            height = 640.dp
            flags = flags or FLAG_DIM_BEHIND
            dimAmount = 0.5F
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mContinuation?.run {
            resume(mResult)
        }
        mContinuation = null
    }
}