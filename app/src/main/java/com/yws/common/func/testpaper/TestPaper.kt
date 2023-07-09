package com.yws.common.func.testpaper

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class TestPaper(
    /**
     * 编号
     */
    val id: Int,

    /**
     * 试卷名
     */
    val name: String,

    /**
     * 分值
     */
    val score: Float,

    /**
     * 题目id列表
     */
    val subjectIdList: List<Int>
)