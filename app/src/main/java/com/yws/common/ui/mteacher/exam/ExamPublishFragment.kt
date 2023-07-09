package com.yws.common.ui.mteacher.exam

import android.R.attr
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.CHAIN_PACKED
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yws.baselib.utils.ComposableSimulateWithLayoutParams
import com.yws.baselib.utils.Theme
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.collectWhenResumed
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.getDrawable
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setPaddingEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeight
import com.yws.baselib.widget.EmptyRecyclerView
import com.yws.common.R
import com.yws.common.ui.mteacher.adapter.TestPaperAdapter


/**
 * description: 试卷已发布
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class ExamPublishFragment : Fragment() {
    private val mViewModel by viewModels<ExamPublishViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ConstraintLayout(inflater.context).apply {
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            val testPaperAdapter = TestPaperAdapter().apply {
                onClick = { _, testPaper ->
                    TestPaperDialog.showDialog(childFragmentManager, testPaper)
                }

                mViewModel.testPaperList.collectWhenResumed(lifecycleScope) {
                    data.clear()
                    data.addAll(it)
                    notifyDataSetChanged()
                }
            }
            EmptyRecyclerView(EmptyView(), context).addTo(this) {
                setConstraintLayoutParams(MATCH_PARENT, 0.dp) {
                    topToTop = PARENT_ID
                    bottomToBottom = PARENT_ID
                }
                adapter = testPaperAdapter
                layoutManager = LinearLayoutManager(context)
            }

            FloatButton {
                lifecycleScope.launchWhenResumed {
                    val newTestPaper = TestPaperAddFragment.showDialog(childFragmentManager)
                    newTestPaper?.let { new ->
                        val temp = mViewModel.addTextPaper(new)
                        if (temp != null) {
                            testPaperAdapter.data.add(newTestPaper)
                            testPaperAdapter.notifyItemInserted(testPaperAdapter.data.size - 1)
                        }
                    }
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            mViewModel.queryAllTextPaper()
        }
    }

    @ComposableSimulateWithLayoutParams
    private fun ConstraintLayout.EmptyView(): View {
        return ConstraintLayout(context).apply {
            addTo(this@EmptyView)
            setWidthHeight(MATCH_PARENT, MATCH_PARENT)

            val iconIv = ImageView(context).apply { id = View.generateViewId() }
            val textTv = TextView(context).apply { id = View.generateViewId() }

            iconIv.addTo(this) {
                setConstraintLayoutParams(64.dp, 64.dp) {
                    topToTop = PARENT_ID
                    bottomToTop = textTv.id
                    startToStart = PARENT_ID
                    endToEnd = PARENT_ID
                    verticalChainStyle = CHAIN_PACKED
                }
                setImageResource(R.drawable.ic_empty_128dp)
            }

            textTv.addTo(this) {
                setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                    topToBottom = iconIv.id
                    bottomToBottom = PARENT_ID
                    startToStart = PARENT_ID
                    endToEnd = PARENT_ID
                    topMargin = 12.dp
                }
                setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                setTextColor(0xFF707070)
                text = "空空如也"
            }
        }
    }

    @ComposableSimulateWithLayoutParams
    private fun ConstraintLayout.FloatButton(onClick: () -> Unit) = ImageView(context).addTo(this) {
        setConstraintLayoutParams(48.dp, 48.dp) {
            bottomToBottom = PARENT_ID
            endToEnd = PARENT_ID
            setMarginEx(0, 0, 24.dp, 24.dp)
        }
        setPaddingEx(12.dp, 12.dp, 12.dp, 12.dp)
        setImageResource(R.drawable.ic_add_32dp)
        background = buildCustomShape(24.dpf, fillColor = 0xFF52B45E)
        foreground = getDrawable(Theme.Attr.getResourceId(attr.selectableItemBackground, -1))

        setOnClickListener {
            onClick()
        }
    }
}