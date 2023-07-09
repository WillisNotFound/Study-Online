package com.yws.common.ui.mstudent.exam

import androidx.lifecycle.ViewModel
import com.yws.common.func.subject.SubjectAnswer

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class StuExamingViewModel:ViewModel() {
    var mAnswerList: MutableList<SubjectAnswer>? = null
}