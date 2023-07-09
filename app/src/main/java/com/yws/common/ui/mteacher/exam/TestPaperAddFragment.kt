package com.yws.common.ui.mteacher.exam

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
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yws.baselib.utils.CornetRadius
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.collectWhenResumed
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setAsLiveBottomDialogStyle
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setMaxLength
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.widget.TopBar
import com.yws.common.func.testpaper.TestPaper
import com.yws.common.ui.mteacher.adapter.SubjectSelectAdapter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class TestPaperAddFragment : DialogFragment() {
    private val mViewModel by viewModels<TestPaperAddViewModel>()

    var mContinuation: Continuation<TestPaper?>? = null
    private var mResult: TestPaper? = null

    private val mScoreFlow = MutableStateFlow(0F)
    private val mSubjectList = mutableListOf<Int>()

    companion object {
        suspend fun showDialog(fragmentManager: FragmentManager): TestPaper? {
            return suspendCancellableCoroutine { continuation ->
                TestPaperAddFragment().apply {
                    this.mContinuation = continuation
                }.show(fragmentManager, toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)
            background = buildCustomShape(12.dpf, fillColor = 0xFFFFFFFF)

            TopBar("添加试卷", buildCustomShape(CornetRadius(12.dpf, 12.dpf, 0.dpf, 0.dpf), fillColor = 0xFFEEEEEE))

            val nameEdt = EditText(context)
            LinearLayout(context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 48.dp) {
                    topToTop = PARENT_ID
                    setMarginEx(12.dp, 60.dp, 12.dp, 0.dp)
                }
                setPaddingEx(12.dp, 0, 12.dp, 0)
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL

                TextView(context).addTo(this) {
                    setWidthHeight(WRAP_CONTENT, WRAP_CONTENT)
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0x99000000)
                    text = "试卷名："
                }

                nameEdt.addTo(this) {
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

            val subjectAdapter = SubjectSelectAdapter().apply {
                onClick = { check, subject ->
                    if (check) {
                        mScoreFlow.value = mScoreFlow.value + subject.score
                        mSubjectList.add(subject.id)
                    } else {
                        mScoreFlow.value = mScoreFlow.value - subject.score
                        mSubjectList.remove(mSubjectList.firstOrNull { id == subject.id })
                    }
                }

                mViewModel.subjectSelectList.collectWhenResumed(lifecycleScope) {
                    data.clear()
                    data.addAll(it)
                    notifyDataSetChanged()
                }
            }
            RecyclerView(context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, MATCH_PARENT) {
                    topMargin = 108.dp
                }
                adapter = subjectAdapter
                layoutManager = LinearLayoutManager(context)
            }

            val scoreTv = TextView(context)
            val publishBtn = TextView(context)
            ConstraintLayout(context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 48.dp) {
                    topToTop = PARENT_ID
                }

                scoreTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToBottom = PARENT_ID
                        startToStart = PARENT_ID
                        setMarginEx(12.dp, 0, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0x99000000)
                    mScoreFlow.collectWhenResumed(lifecycleScope) {
                        text = "总分：$it"
                    }
                }

                publishBtn.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToBottom = PARENT_ID
                        endToEnd = PARENT_ID
                        setMarginEx(0, 0, 12.dp, 0)
                    }
                    setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                    background = buildCustomShape(CornetRadius(12.dpf, 12.dpf, 12.dpf, 12.dpf), fillColor = 0xFF52B45E)
                    foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1))
                    gravity = Gravity.CENTER
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0xFFFFFFFF)
                    text = "发布"

                    setOnClickListener {
                        mResult = TestPaper(-1, nameEdt.text.toString(), mScoreFlow.value, mSubjectList)
                        dismissAllowingStateLoss()
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
        lifecycleScope.launchWhenResumed {
            mViewModel.querySubjectSelectList()
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