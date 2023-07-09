package com.yws.common.func.teacher


/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
interface ITeacherManager {
    /**
     * 当前教师实例（最终实现应该是全局单例），没登陆则为null
     */
    val current: Teacher?

    /**
     * 教师登录
     */
    suspend fun login(account: Int, password: String): Boolean

    /**
     * 教师取消登录
     */
    suspend fun unLogin()
}