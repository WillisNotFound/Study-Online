package com.yws.common.ui.mteacher.exam

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
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.yws.baselib.utils.CornetRadius
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.collectWhenResumed
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.gone
import com.yws.baselib.utils.setAsLiveBottomDialogStyle
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.utils.visible
import com.yws.baselib.widget.TopBar
import com.yws.common.func.answerPaperManager
import com.yws.common.func.answerpaper.AnswerPaper
import com.yws.common.func.answerpaper.AnswerPaperStatus
import com.yws.common.func.testPaperManager
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
class ExamMakingDialog : DialogFragment() {
    private var mAnswerPaper: AnswerPaper? = null
    var mContinuation: Continuation<AnswerPaper?>? = null
    private var mResult: AnswerPaper? = null

    private val mViewModel by activityViewModels<ExamMakeViewModel>()

    companion object {
        suspend fun showDialog(fragmentManager: FragmentManager, answerPaper: AnswerPaper): AnswerPaper? {
            return suspendCancellableCoroutine { continuation ->
                ExamMakingDialog().apply {
                    this.mContinuation = continuation
                    this.mAnswerPaper = answerPaper
                }.show(fragmentManager, toString())
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)
            background = buildCustomShape(12.dpf, fillColor = 0xFFFFFFFF)

            TopBar("批改", buildCustomShape(CornetRadius(12.dpf, 12.dpf, 0.dpf, 0.dpf), fillColor = 0xFFEEEEEE))

            mAnswerPaper?.let { answerPaper ->
                val testPaper = testPaperManager.queryTestPaperById(answerPaper.testPaperId)!!
                mViewModel.mScoreList = MutableList(answerPaper.subjectAnswers.size) { 0F }
                val currentPage = MutableStateFlow(0)
                val pageTv = TextView(context).apply { id = View.generateViewId() }
                TopBar(testPaper.name, buildCustomShape(CornetRadius(12.dpf, 12.dpf, 0F, 0F), fillColor = 0xFFEEEEEE))

                ViewPager2(context).addTo(this) {
                    setConstraintLayoutParams(MATCH_PARENT, 0.dp) {
                        topToTop = PARENT_ID
                        bottomToTop = pageTv.id
                        setMarginEx(0.dp, 48.dp, 0.dp, 0.dp)
                    }
                    adapter = object : FragmentStateAdapter(childFragmentManager, lifecycle) {
                        override fun getItemCount() = testPaper.subjectIdList.size

                        override fun createFragment(position: Int): Fragment {
                            return MakingFragment.newInstance(
                                childFragmentManager,
                                position,
                                testPaper.subjectIdList[position],
                                answerPaper.subjectAnswers[position]
                            )
                        }
                    }

                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            currentPage.value = position
                        }
                    })
                }

                pageTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        startToStart = PARENT_ID
                        endToEnd = PARENT_ID
                        bottomToBottom = PARENT_ID
                        setMarginEx(12.dp, 12.dp, 12.dp, 12.dp)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10F)
                    setTextColor(0x99000000)
                    currentPage.collectWhenResumed(lifecycleScope) {
                        text = "${it + 1}/${testPaper.subjectIdList.size}"
                    }
                }

                TextView(context).addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        endToEnd = PARENT_ID
                        setMarginEx(0, 10.dp, 12.dp, 0)
                    }
                    setPaddingEx(12.dp, 6.dp, 12.dp, 6.dp)
                    background = buildCustomShape(CornetRadius(12.dpf, 12.dpf, 12.dpf, 12.dpf), fillColor = 0xFF52B45E)
                    foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackgroundBorderless, -1))
                    gravity = Gravity.CENTER
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0xFFFFFFFF)
                    text = "提交"

                    setOnClickListener {
                        lifecycleScope.launchWhenResumed {
                            var score = 0F
                            for (i in 0 until (mViewModel.mScoreList?.size ?: 0)) {
                                score += mViewModel.mScoreList?.get(i) ?: 0F
                            }
                            answerPaperManager.updateAnswerPaper(
                                AnswerPaper(
                                    answerPaper.id,
                                    answerPaper.studentId,
                                    answerPaper.testPaperId,
                                    score,
                                    mViewModel.mScoreList ?: emptyList(),
                                    answerPaper.subjectAnswers,
                                    AnswerPaperStatus.Correct,
                                ).apply {
                                    mResult = this
                                }
                            )
                            dismissAllowingStateLoss()
                        }
                    }

                    currentPage.collectWhenResumed(lifecycleScope) {
                        if (it + 1 == testPaper.subjectIdList.size) {
                            visible()
                        } else {
                            gone()
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