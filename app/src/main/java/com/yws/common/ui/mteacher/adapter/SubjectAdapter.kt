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
import com.yws.common.func.subject.Subject
import com.yws.common.func.subject.SubjectDifficulty

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
class SubjectAdapter : Adapter<SubjectAdapter.SubjectViewHolder>() {
    var onLongClick: ((Int, Subject) -> Boolean)? = null
    var onClick: ((Int, Subject) -> Unit)? = null
    val data = mutableListOf<Subject>()
    private val mData: List<Subject> get() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        return SubjectViewHolder(parent.context).apply {
            itemView.setOnLongClickListener {
                onLongClick?.invoke(layoutPosition, mData[layoutPosition]) ?: true
            }
            itemView.setOnClickListener {
                onClick?.invoke(layoutPosition, mData[layoutPosition]) ?: true
            }
        }
    }

    override fun getItemCount() = mData.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.onBind(mData[position])
    }

    inner class SubjectViewHolder(context: Context) : ViewHolder(ConstraintLayout(context)) {
        private val typeTv = TextView(context).apply { id = View.generateViewId() }
        private val pointsTv = TextView(context).apply { id = View.generateViewId() }
        private val descTv = TextView(context).apply { id = View.generateViewId() }
        private val difficultyTv = TextView(context).apply { id = View.generateViewId() }
        private val scoreTv = TextView(context).apply { id = View.generateViewId() }

        init {
            (itemView as ConstraintLayout).apply {
                setWidthHeightParams(MATCH_PARENT, 64.dp) {
                    setMarginEx(12.dp, 6.dp, 12.dp, 6.dp)
                }
                background = buildCustomShape(12.dpf, fillColor = 0xFFEEEEEE)

                typeTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToTop = descTv.id
                        startToStart = PARENT_ID
                        verticalChainStyle = CHAIN_PACKED
                        setMarginEx(12.dp, 0, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0xFF52B45E)
                }

                pointsTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = typeTv.id
                        startToEnd = typeTv.id
                        setMarginEx(12.dp, 0, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                    setTextColor(0xFF000000)
                }

                descTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToBottom = typeTv.id
                        bottomToBottom = PARENT_ID
                        startToStart = typeTv.id
                        setMarginEx(0.dp, 6.dp, 0, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13F)
                    setTextColor(0x99000000)
                    ellipsize = TextUtils.TruncateAt.END
                    setMaxLength(10)
                }

                difficultyTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToTop = PARENT_ID
                        bottomToTop = scoreTv.id
                        endToEnd = PARENT_ID
                        verticalChainStyle = CHAIN_PACKED
                        setMarginEx(0, 0, 12.dp, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F)
                }

                scoreTv.addTo(this) {
                    setConstraintLayoutParams(WRAP_CONTENT, WRAP_CONTENT) {
                        topToBottom = difficultyTv.id
                        bottomToBottom = PARENT_ID
                        endToEnd = PARENT_ID
                        setMarginEx(0, 6.dp, 12.dp, 0)
                    }
                    setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16F)
                    setTextColor(0xFFDC1B00)
                }
            }
        }

        fun onBind(subject: Subject) {
            typeTv.text = "[${subject.type}]"
            pointsTv.text = subject.points
            descTv.text = subject.content.simpleDesc
            scoreTv.text = subject.score.toString()
            difficultyTv.text = subject.difficulty.toString()
            when (subject.difficulty) {
                SubjectDifficulty.Simple -> {
                    difficultyTv.setTextColor(0xFF52B45E)
                }

                SubjectDifficulty.Medium -> {
                    difficultyTv.setTextColor(0xFFFCAF00)
                }

                SubjectDifficulty.Difficult -> {
                    difficultyTv.setTextColor(0xFFDC1B00)
                }
            }
        }
    }
}