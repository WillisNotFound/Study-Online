package com.yws.common.ui.mteacher.exam

import androidx.lifecycle.ViewModel
import com.yws.common.func.subject.SubjectSelect
import com.yws.common.func.subjectManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class TestPaperAddViewModel : ViewModel() {
    private val mSubjectSelectList = MutableStateFlow(emptyList<SubjectSelect>())
    val subjectSelectList: StateFlow<List<SubjectSelect>> = mSubjectSelectList

    suspend fun querySubjectSelectList() {
        mSubjectSelectList.value = subjectManager.queryAll().map {
            it.toSubjectSelect(false)
        }
    }
}