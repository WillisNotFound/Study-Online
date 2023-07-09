package com.yws.common.ui.mteacher.exam

import androidx.lifecycle.ViewModel
import com.yws.common.func.testPaperManager
import com.yws.common.func.testpaper.TestPaper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class ExamPublishViewModel : ViewModel() {
    private val mTestPaperList = MutableStateFlow(emptyList<TestPaper>())
    val testPaperList: StateFlow<List<TestPaper>> = mTestPaperList

    suspend fun queryAllTextPaper() {
        mTestPaperList.value = testPaperManager.queryAllTestPaper()
    }

    suspend fun addTextPaper(testPaper: TestPaper): TestPaper? {
        return testPaperManager.addTestPaper(testPaper)
    }
}