package com.yws.common.func.testpaper

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
interface ITestPaperManager {
    /**
     * 查询所有试卷
     */
    suspend fun queryAllTestPaper(): List<TestPaper>

    /**
     * 通过id查询试卷
     */
    fun queryTestPaperById(id: Int): TestPaper?

    /**
     * 添加试卷
     */
    suspend fun addTestPaper(testPaper: TestPaper): TestPaper?

    /**
     * 展示题目
     */
    fun Display_Subject(): Boolean

    /**
     * 增加题目
     */
    fun Add_Subject(id: Int): Boolean

    /**
     * 删除题目
     */
    fun Delete_Subject(id: Int): Boolean

    /**
     * 计算总分
     */
    fun Count_Score(): Float

    /**
     * 设置题目分数
     */
    fun Set_Score(id: Int, sc: Float): Boolean
}