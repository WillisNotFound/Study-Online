package com.yws.common.func.answerpaper

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
interface IAnswerPaperManager {
    suspend fun queryAllAnswerPaper(): List<AnswerPaper>

    suspend fun queryAllCorrectAnswerPaper(): List<AnswerPaper>

    suspend fun queryAllIdleAnswerPaper(): List<AnswerPaper>

    suspend fun queryMyAnswerPaper(): List<AnswerPaper>

    suspend fun queryMyCorrectAnswerPaper(): List<AnswerPaper>

    suspend fun queryMyIdleAnswerPaper(): List<AnswerPaper>

    suspend fun addAnswerPaper(answerPaper: AnswerPaper): AnswerPaper?

    suspend fun updateAnswerPaper(answerPaper: AnswerPaper): Boolean
}