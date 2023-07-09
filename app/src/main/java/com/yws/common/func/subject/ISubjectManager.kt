package com.yws.common.func.subject

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
interface ISubjectManager {
    /**
     * 查询所有题目
     */
    suspend fun queryAll(): List<Subject>

    /**
     * 查询指定id的题目
     */
    suspend fun queryById(id: Int): Subject?

    /**
     * 查询指定类型的题目
     */
    suspend fun queryByType(type: SubjectType): List<Subject>

    /**
     * 添加题目
     *
     * @return 添加成功-Subject，添加失败-null
     */
    suspend fun addSubject(subject: Subject): Subject?

    /**
     * 更新题目，id为唯一标识
     *
     * @return 更新成功-true，更新失败-false
     */
    suspend fun updateSubject(subject: Subject): Boolean

    /**
     * 删除指定id的题目
     *
     * @return 删除成功-true，删除失败-false
     */
    suspend fun deleteSubject(id: Int): Boolean
}