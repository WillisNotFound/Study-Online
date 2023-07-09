package com.yws.common.ui.mstudent.answerpaper

import androidx.lifecycle.ViewModel
import com.yws.common.func.answerPaperManager
import com.yws.common.func.answerpaper.AnswerPaper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class StuAnswerViewModel : ViewModel() {
    private val mCorrectAnsPaperList = MutableStateFlow(emptyList<AnswerPaper>())
    val correctAnsPaperList: StateFlow<List<AnswerPaper>> = mCorrectAnsPaperList
    private val mIdleAnsPaperList = MutableStateFlow(emptyList<AnswerPaper>())
    val idleAnsPaperList: StateFlow<List<AnswerPaper>> = mIdleAnsPaperList

    suspend fun queryMyCorrectAnsPaper() {
        mCorrectAnsPaperList.value = answerPaperManager.queryMyCorrectAnswerPaper()
    }

    suspend fun queryMyIdleAnsPaper() {
        mIdleAnsPaperList.value = answerPaperManager.queryMyIdleAnswerPaper()
    }
}