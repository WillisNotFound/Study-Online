package com.yws.common.ui.mteacher.exam

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
class ExamMakeViewModel: ViewModel() {
    private val mAnsPaperList = MutableStateFlow(emptyList<AnswerPaper>())
    val ansPaperList: StateFlow<List<AnswerPaper>> = mAnsPaperList

    var mScoreList: MutableList<Float>? = null

    suspend fun queryAllAnsPaper() {
        mAnsPaperList.value = answerPaperManager.queryAllAnswerPaper()
    }
}