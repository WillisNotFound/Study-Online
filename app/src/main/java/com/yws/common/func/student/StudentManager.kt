package com.yws.common.func.student

import android.util.Log

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
object StudentManager : IStudentManager {
    private const val TAG = "StudentManager"
    private val sStudentList = listOf(
        Student(1000, "学生张三", "p1000"),
        Student(1001, "学生李四", "p1001"),
        Student(1002, "学生王五", "p1002"),
        Student(1003, "学生刘六", "p1003"),
    )

    private var mCurrent: Student? = null
    override val current: Student?
        get() {
            if (mCurrent == null) {
                Log.e(TAG, "StudentManager$ you must call login() first.")
            }
            return mCurrent
        }

    override suspend fun login(account: Int, password: String): Boolean {
        sStudentList.forEach { student ->
            if (student.id == account && student.password == password) {
                mCurrent = student
                return true
            }
        }
        return false
    }

    override suspend fun unLogin() {
        mCurrent = null
    }

    override fun findStudentById(id: Int): Student? {
        return sStudentList.firstOrNull { it.id == id }
    }
}