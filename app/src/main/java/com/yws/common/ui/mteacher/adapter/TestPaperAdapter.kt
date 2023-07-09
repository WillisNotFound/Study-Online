package com.yws.common.ui.mteacher.adapter

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.CHAIN_PACKED
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yws.baselib.utils.addTo
import com.yws.baselib.utils.buildCustomShape
import com.yws.baselib.utils.dp
import com.yws.baselib.utils.dpf
import com.yws.baselib.utils.setConstraintLayoutParams
import com.yws.baselib.utils.setMarginEx
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeightParams
import com.yws.common.func.testpaper.TestPaper

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class TestPaperAdapter : Adapter<TestPaperAdapter.TestPaperViewHolder>() {
    var onClick: ((Int, TestPaper) -> Unit)? = null
    val data = mutableListOf<TestPaper>()
    private val mData: List<TestPaper> get() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestPaperViewHolder {
        return TestPaperViewHolder(parent.context).apply {
            itemView.setOnClickListener {
                onClick?.invoke(layoutPosition, mData[layoutPosition]) ?: true
            }
        }
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: TestPaperViewHolder, position: Int) {
        holder.onBind(mData[position])
    }

    inner class TestPaperViewHolder(context: Context) : ViewHolder(ConstraintLayout(context)) {
        private val nameTv = TextView(context).apply { id = View.generateViewId() }
        private val scoreTv = TextView(context).apply { id = View.generateViewId() }

        init {
            (itemView as ConstraintLayout).apply {
                setWidthHeightParams(MATCH_PARENT, 64.dp) {
                    setMarginEx(12.dp, 6.dp, 12.dp, 6.dp)
                }
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)

                nameTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToBottom = PARENT_ID
                        startToStart = PARENT_ID
                        verticalChainStyle = CHAIN_PACKED
                        setMarginEx(12.dp, 0, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                    setTextColor(0xFF52B45E)
                }

                scoreTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToBottom = PARENT_ID
                        endToEnd = PARENT_ID
                        setMarginEx(0, 6.dp, 12.dp, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                    setTextColor(0x99000000)
                }
            }
        }

        fun onBind(testPaper: TestPaper) {
            nameTv.text = testPaper.name
            scoreTv.text = "总分：${testPaper.score}"
        }
    }
}