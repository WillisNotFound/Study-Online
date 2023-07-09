package com.yws.common.func.subject

/**
 * description: 题目实体类
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
data class Subject(
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
    val answer: SubjectAnswer
) {
    fun toSubjectSelect(check: Boolean): SubjectSelect {
        return SubjectSelect(id, score, difficulty, type, points, content, answer, check)
    }
}

sealed interface SubjectType {
    /**
     * 填空题
     */
    object FillIn : SubjectType {
        override fun toString(): String {
            return "填空题"
        }
    }

    /**
     * 选择题
     */
    object Choice : SubjectType {
        override fun toString(): String {
            return "选择题"
        }
    }

    /**
     * 主观题
     */
    object SUBJECTIVITY : SubjectType {
        override fun toString(): String {
            return "主观题"
        }
    }
}

sealed interface SubjectContent {
    val simpleDesc: String

    /**
     * 文字描述
     */
    class Text(val desc: String) : SubjectContent {
        override val simpleDesc: String = desc

        override fun toString(): String {
            return "文本"
        }
    }

    /**
     * 图片描述
     */
    class Image(val url: String) : SubjectContent {
        override val simpleDesc: String = url

        override fun toString(): String {
            return "图片"
        }
    }
}

sealed interface SubjectAnswer {
    val simpleDesc: String

    /**
     * 文字描述
     */
    class Text(val desc: String) : SubjectAnswer {
        override val simpleDesc: String = desc

        override fun toString(): String {
            return "文本"
        }
    }

    /**
     * 图片描述
     */
    class Image(val url: String) : SubjectAnswer {
        override val simpleDesc: String = url

        override fun toString(): String {
            return "图片"
        }
    }
}

sealed interface SubjectDifficulty {
    /**
     * 简单
     */
    object Simple : SubjectDifficulty {
        override fun toString(): String {
            return "简单"
        }
    }

    /**
     * 中等
     */
    object Medium : SubjectDifficulty {
        override fun toString(): String {
            return "中等"
        }
    }

    /**
     * 困难
     */
    object Difficult : SubjectDifficulty {
        override fun toString(): String {
            return "困难"
        }
    }
}