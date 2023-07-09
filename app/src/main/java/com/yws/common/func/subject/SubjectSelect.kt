package com.yws.common.func.subject

import android.hardware.camera2.params.BlackLevelPattern

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
class SubjectSelect(
    /**
     * 编号
     */
    val id: Int,

    /**
     * 分值
     */
    val score: Float,

    /**
     * 难度
     */
    val difficulty: SubjectDifficulty,

    /**
     * 题型
     */
    val type: SubjectType,

    /**
     * 知识点
     */
    val points: String,

    /**
     * 题目内容
     */
    val content: SubjectContent,

    /**
     * 题目答案
     */
    val answer: SubjectAnswer,

    /**
     * 选中状态
     */
    var check: Boolean = false
) {
    fun toSubject(): Subject {
        return Subject(id, score, difficulty, type, points, content, answer)
    }
}