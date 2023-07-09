package com.yws.common.func

import com.yws.common.func.answerpaper.AnswerPaperManager
import com.yws.common.func.answerpaper.IAnswerPaperManager
import com.yws.common.func.student.IStudentManager
import com.yws.common.func.student.StudentManager
import com.yws.common.func.subject.ISubjectManager
import com.yws.common.func.subject.SubjectManager
import com.yws.common.func.teacher.ITeacherManager
import com.yws.common.func.teacher.TeacherManager
import com.yws.common.func.testpaper.ITestPaperManager
import com.yws.common.func.testpaper.TestPaperManager

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
val teacherManager: ITeacherManager get() = TeacherManager
val studentManager: IStudentManager get() = StudentManager
val subjectManager: ISubjectManager get() = SubjectManager
val testPaperManager: ITestPaperManager get() = TestPaperManager
val answerPaperManager: IAnswerPaperManager get() = AnswerPaperManager