package com.yws.common.ui.mteacher.adapter

import android.content.Context
import android.text.TextUtils
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
import com.yws.baselib.utils.setMaxLength
import com.yws.baselib.utils.setTextColor
import com.yws.baselib.utils.setWidthHeightParams
import com.yws.common.func.answerpaper.AnswerPaper
import com.yws.common.func.answerpaper.AnswerPaperStatus
import com.yws.common.func.studentManager
import com.yws.common.func.testPaperManager

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/27
 * <p>version: 1.0
 * <p>update: none
 */
class AnswerPaperAdapter2: Adapter<AnswerPaperAdapter2.AnswerPaper2ViewHolder>() {
    var onClick: ((Int, AnswerPaper) -> Unit)? = null
    val data = mutableListOf<AnswerPaper>()
    private val mData: List<AnswerPaper> get() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerPaper2ViewHolder {
        return AnswerPaper2ViewHolder(parent.context).apply {
            itemView.setOnClickListener {
                onClick?.invoke(layoutPosition, mData[layoutPosition]) ?: true
            }
        }
    }

    override fun onBindViewHolder(holder: AnswerPaper2ViewHolder, position: Int) {
        holder.onBind(mData[position])
    }

    override fun getItemCount() = mData.size

    inner class AnswerPaper2ViewHolder(context: Context) : ViewHolder(ConstraintLayout(context)) {
        private val nameTv = TextView(context).apply { id = View.generateViewId() }
        private val studentNameTv = TextView(context).apply { id = View.generateViewId() }
        private val statusTv = TextView(context).apply { id = View.generateViewId() }

        init {
            (itemView as ConstraintLayout).apply {
                setWidthHeightParams(MATCH_PARENT, 64.dp) {
                    setMarginEx(12.dp, 6.dp, 12.dp, 6.dp)
                }
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)

                nameTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToTop = studentNameTv.id
                        startToStart = PARENT_ID
                        verticalChainStyle = CHAIN_PACKED
                        setMarginEx(12.dp, 0, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                    setTextColor(0xFF52B45E)
                }

                studentNameTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToBottom = nameTv.id
                        bottomToBottom = PARENT_ID
                        startToStart = nameTv.id
                        setMarginEx(0.dp, 6.dp, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13F)
                    setTextColor(0x99000000)
                    ellipsize = TextUtils.TruncateAt.END
                    setMaxLength(10)
                }


                statusTv.addTo(this) {
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

        fun onBind(answerPaper: AnswerPaper) {
            nameTv.text = testPaperManager.queryTestPaperById(answerPaper.testPaperId)?.name
            studentNameTv.text = studentManager.findStudentById(answerPaper.studentId)?.name
            statusTv.text = answerPaper.status.toString()
            when (answerPaper.status) {
                AnswerPaperStatus.Correct -> {
                    statusTv.setTextColor(0xFF52B45E)
                }

                AnswerPaperStatus.Idle -> {
                    statusTv.setTextColor(0xFFDC1B00)
                }
            }
        }
    }
}