package com.yws.common.func.student

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
interface IStudentManager {
    /**
     * 当前学生实例（最终实现应该是全局单例），没登陆则为null
     */
    val current: Student?

    /**
     * 学生登录
     */
    suspend fun login(account: Int, password: String): Boolean

    /**
     * 学生取消登录
     */
    suspend fun unLogin()

    fun findStudentById(id: Int): Student?
}