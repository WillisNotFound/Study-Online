package com.yws.common.func.testpaper

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/26
 * <p>version: 1.0
 * <p>update: none
 */
object TestPaperManager : ITestPaperManager {
    private val mData = mutableListOf(
        TestPaper(
            0,
            "试卷0",
            25F,
            listOf(0, 1)
        ),
        TestPaper(
            1,
            "试卷1",
            50F,
            listOf(1, 2, 3)
        ),
        TestPaper(
            2,
            "试卷2",
            30F,
            listOf(0, 3)
        ),
        TestPaper(
            3,
            "试卷3",
            30F,
            listOf(0, 3)
        )
    )

    override suspend fun queryAllTestPaper(): List<TestPaper> {
        return mData
    }

    override fun queryTestPaperById(id: Int): TestPaper? {
        for (i in 0 until mData.size) {
            if (mData[i].id == id) {
                return mData[i]
            }
        }
        return null
    }

    override suspend fun addTestPaper(testPaper: TestPaper): TestPaper? {
        val newTestPaper = TestPaper(
            mData.size,
            testPaper.name,
            testPaper.score,
            testPaper.subjectIdList,
        )
        mData.add(newTestPaper)
        return newTestPaper
    }

    override fun Display_Subject(): Boolean {
        return false
    }

    override fun Add_Subject(id: Int): Boolean {
        return false
    }

    override fun Delete_Subject(id: Int): Boolean {
        return false
    }

    override fun Count_Score(): Float {
        return 0F
    }

    override fun Set_Score(id: Int, sc: Float): Boolean {
        return false
    }
}