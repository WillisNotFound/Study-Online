package com.yws.common.func.answerpaper

import com.yws.common.func.subject.SubjectAnswer

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class AnswerPaper(
    val id: Int,
    val studentId: Int,
    val testPaperId: Int,
    val score: Float = 0F,
    val scores: List<Float> = emptyList(),
    val subjectAnswers: List<SubjectAnswer> = emptyList(),
    val status: AnswerPaperStatus = AnswerPaperStatus.Idle
)

sealed interface AnswerPaperStatus {
    object Idle : AnswerPaperStatus {
        override fun toString(): String {
            return "未批改"
        }
    }

    object Correct : AnswerPaperStatus {
        override fun toString(): String {
            return "已批改"
        }
    }
}