package com.yws.common.ui.mteacher.subject

import androidx.lifecycle.ViewModel
import com.yws.common.func.subject.Subject
import com.yws.common.func.subjectManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
class SubjectViewModel : ViewModel() {
    private val mSubjectList = MutableStateFlow(emptyList<Subject>())
    val subjectList: StateFlow<List<Subject>> = mSubjectList

    suspend fun addSubject(subject: Subject): Subject? {
        return subjectManager.addSubject(subject)
    }

    suspend fun querySubjectList() {
        mSubjectList.value = subjectManager.queryAll()
    }

    suspend fun deleteSubject(subject: Subject): Boolean {
        return subjectManager.deleteSubject(subject.id)
    }

    suspend fun updateSubject(subject: Subject): Boolean {
        return subjectManager.updateSubject(subject)
    }
}