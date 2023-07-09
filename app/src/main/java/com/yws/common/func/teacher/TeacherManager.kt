package com.yws.common.func.teacher

import android.util.Log

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
object TeacherManager : ITeacherManager {
    private const val TAG = "TeacherManager"
    private val sTeacherList = listOf(
        Teacher(1000, "张三", "p1000"),
        Teacher(1001, "李四", "p1001"),
        Teacher(1002, "王五", "p1002"),
        Teacher(1003, "刘六", "p1003"),
    )

    private var mCurrent: Teacher? = null
    override val current: Teacher?
        get() {
            if (mCurrent == null) {
                Log.e(TAG, "TeacherManager$ you must call login() first.")
            }
            return mCurrent
        }

    override suspend fun login(account: Int, password: String): Boolean {
        sTeacherList.forEach { teacher ->
            if (teacher.id == account && teacher.password == password) {
                mCurrent = teacher
                return true
            }
        }
        return false
    }

    override suspend fun unLogin() {
        mCurrent = null
    }
}