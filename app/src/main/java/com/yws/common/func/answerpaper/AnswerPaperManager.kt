package com.yws.common.func.answerpaper

import com.yws.common.func.studentManager
import com.yws.common.func.subject.SubjectAnswer

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
object AnswerPaperManager : IAnswerPaperManager {
    private val mData = mutableListOf(
        AnswerPaper(
            0,
            1000,
            0,
            0F,
            emptyList(),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2")),
            AnswerPaperStatus.Idle
        ),
        AnswerPaper(
            1,
            1000,
            1,
            0F,
            emptyList(),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2"), SubjectAnswer.Text("answer3")),
            AnswerPaperStatus.Idle
        ),
        AnswerPaper(
            2,
            1000,
            2,
            20F,
           listOf(10F, 10F),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2")),
            AnswerPaperStatus.Correct
        ),
        AnswerPaper(
            3,
            1000,
            0,
            30F,
            listOf(15F, 15F),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2")),
            AnswerPaperStatus.Correct
        ),
        AnswerPaper(
            4,
            1001,
            1,
            0F,
            emptyList(),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2"), SubjectAnswer.Text("answer3")),
            AnswerPaperStatus.Idle
        ),
        AnswerPaper(
            5,
            1001,
            2,
            0F,
            emptyList(),
            listOf(SubjectAnswer.Text("answer1"), SubjectAnswer.Text("answer2")),
            AnswerPaperStatus.Idle
        ),
    )

    override suspend fun queryAllAnswerPaper(): List<AnswerPaper> {
        return mData
    }

    override suspend fun queryAllCorrectAnswerPaper(): List<AnswerPaper> {
        return mData.filter { it.status == AnswerPaperStatus.Correct }
    }

    override suspend fun queryAllIdleAnswerPaper(): List<AnswerPaper> {
        return mData.filter { it.status == AnswerPaperStatus.Idle }
    }

    override suspend fun queryMyAnswerPaper(): List<AnswerPaper> {
        return mData.filter { it.studentId == studentManager.current?.id }
    }

    override suspend fun queryMyCorrectAnswerPaper(): List<AnswerPaper> {
        return queryMyAnswerPaper().filter { it.status == AnswerPaperStatus.Correct }
    }

    override suspend fun queryMyIdleAnswerPaper(): List<AnswerPaper> {
        return queryMyAnswerPaper().filter { it.status == AnswerPaperStatus.Idle }
    }

    override suspend fun addAnswerPaper(answerPaper: AnswerPaper): AnswerPaper? {
        val newAnsPaper = AnswerPaper(
            mData.size,
            answerPaper.studentId,
            answerPaper.testPaperId,
            answerPaper.score, answerPaper.scores,
            answerPaper.subjectAnswers,
            answerPaper.status
        )
        mData.add(newAnsPaper)
        return newAnsPaper
    }

    override suspend fun updateAnswerPaper(answerPaper: AnswerPaper): Boolean {
        for (i in 0 until mData.size) {
            if (mData[i].id == answerPaper.id) {
                mData[i] = answerPaper
                return true
            }
        }
        return false
    }
}