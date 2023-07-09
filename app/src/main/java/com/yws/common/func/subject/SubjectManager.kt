package com.yws.common.func.subject

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
object SubjectManager : ISubjectManager {
    private val mData = mutableListOf(
        Subject(
            0,
            8F,
            SubjectDifficulty.Medium,
            SubjectType.Choice,
            "查找语病",
            SubjectContent.Text("选出下列各句中没有语病的一项（）\nA. 坚持“动态清零”的政策，是对人民至上、生命至上理念最" +
                    "好的践行。\nB. 个人是否拥有健康的体魄，关键在于持之以恒、科学健康地参加体育锻炼.\nC. 我们的先辈们开启了丝绸之路，开辟了人类文明史上的大交流、大融合时代。" +
                    "\nD. 通过开展读经典美文、学传统礼仪等活动，让我们从中深切领悟到了传统文化的魅力。"),
            SubjectAnswer.Text("A")
        ),
        Subject(
            1,
            8F,
            SubjectDifficulty.Medium,
            SubjectType.Choice,
            "查找语病",
            SubjectContent.Text("下列各句中有语病的一项是（）\nA. 武汉新增四条快速公交线路，改善了区域间的交通状况，提高了通勤效率。\nB. 湖北高新技术产品出口总值" +
                    "不断上升，突破了千亿元大关，创下了历史新高。\nC. 在神舟十四号载人飞船成功发射之后，将于任务期间完成组装建造中国空间站。\nD. 可再生能源发电的竞争力将随着全球能源结构转型的" +
                    "加速推进而得到进一步增强。"),
            SubjectAnswer.Text("C")
        ),
        Subject(
            2,
            8F,
            SubjectDifficulty.Simple,
            SubjectType.Choice,
            "词语使用",
            SubjectContent.Text("下列句子中加点的词语使用不恰当的一项是（）\nA. 岁月可能会模糊记忆，但英雄的名字永远镌刻在历史的丰碑上，铭记于人们心中。\nB. 对于网络词语" +
                    "的使用，有人极力排斥，有人欣然接受，更多的人保持着谨慎的态度。\nC. 无数科技工作者默默奉献，殚精竭虑，无怨无悔，创造了我国航天事业的辉煌。\nD. 电视连续剧《人世间》的演员把角色" +
                    "演绎得真实可信，栩栩如生，深受观众好评。"),
            SubjectAnswer.Text("C")
        ),
        Subject(
            3,
            8F,
            SubjectDifficulty.Medium,
            SubjectType.Choice,
            "查找语病",
            SubjectContent.Text("下列各句中，没有语病的一项是（）\nA. 2022年北京冬奥会上，经过中国小将谷爱凌的奋力拼博，终于获得自由式滑雪女子大跳台金牌。\nB. 近段时间来，全国各地开展广" +
                    "'泛'喜迎二十大、永远跟党走、奋进新征程”等主题教育活动。\nC. 实施新修订的义务教育课程方案和课程标准，对推动义务教育高质量发展，全面建设社会主义现代化强国具有重要意义。\nD. 浩" +
                    "坤湖旅游休闲度假景区，距百色至凌云二级公路约五公里的距离，自然风光十分优美。"),
            SubjectAnswer.Text("D")
        ),
        Subject(
            4,
            8F,
            SubjectDifficulty.Difficult,
            SubjectType.Choice,
            "词语使用",
            SubjectContent.Text("在下面文字画线处填入语句，衔接最恰当的一项是（）\n'每一代人有每一代人的长征路，每一代人都要走好自己的长征路。'_________相信我们一定能不负先辈" +
                    "重托，不辱历史使命，走好我们这一代人的长征路。\n1. 我们要树立远大目标，刻苦学习，为报效祖国打好扎实的基础。\n2. 想今朝，我们应如何珍惜幸福生活，继承和发扬英雄们的奋斗精神？\n" +
                    "3. 然后在实践中历练，面对各种艰难险阻，挺身而出，攻坚克难。\n4. 忆往昔，先辈们披荆斩棘，浴血奋斗，换来今天的和平岁月。\nA. 1432\tB. 4312\tC. 4213\tD. 1243"),
            SubjectAnswer.Text("B")
        ),

        Subject(
            5,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("______________，在河之洲。(《关雎》)"),
            SubjectAnswer.Text("关关雎鸠")
        ),

        Subject(
            6,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("______________，甲光向日金鳞开。"),
            SubjectAnswer.Text("黑云压城城欲摧")
        ),

        Subject(
            7,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("伤心秦汉经行处，______________。"),
            SubjectAnswer.Text("宫阙万间都做了土")
        ),

        Subject(
            8,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("______________，一览众山小"),
            SubjectAnswer.Text("会当凌绝顶")
        ),

        Subject(
            9,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("几处早莺争暖树，______________。"),
            SubjectAnswer.Text("谁家新燕啄春泥")
        ),

        Subject(
            10,
            5F,
            SubjectDifficulty.Simple,
            SubjectType.FillIn,
            "默写",
            SubjectContent.Text("_____________，风正一帆悬"),
            SubjectAnswer.Text("潮平两岸阔")
        ),

        Subject(
            11,
            15F,
            SubjectDifficulty.Difficult,
            SubjectType.SUBJECTIVITY,
            "古文理解",
            SubjectContent.Text(" 结合《儒林外史》整本书阅读，第一回的主要人物是王冕，但是王冕与小说后面章节的人物关联不大，作者为什么要在小说的开端用一章来写他呢？\n"),
            SubjectAnswer.Text("因为所以")
        ),

        Subject(
            12,
            15F,
            SubjectDifficulty.Difficult,
            SubjectType.SUBJECTIVITY,
            "古文理解",
            SubjectContent.Text("结合《儒林外史》整本书阅读，《儒林外史》以功名富贵为核心：有醉心功名迂腐可笑者；有心艳功名泯灭人性者；有依仗功名而假意清高者；" +
                    "有假托无意功名自命清高被人看破耻笑者；也有辞却功名释放个性者。请问周进属于哪种人？请结合小说情节加以说明。"),
            SubjectAnswer.Text("周进是醉心功名迂腐可笑者。")
        ),
    )

    override suspend fun queryAll(): List<Subject> {
        return mData
    }

    override suspend fun queryById(id: Int): Subject? {
        for (i in 0 until mData.size) {
            if (mData[i].id == id) {
                return mData[i]
            }
        }
        return null
    }

    override suspend fun queryByType(type: SubjectType): List<Subject> {
        return emptyList()
    }

    override suspend fun addSubject(subject: Subject): Subject? {
        val newSubject = Subject(
            mData.size,
            subject.score,
            subject.difficulty,
            subject.type,
            subject.points,
            subject.content,
            subject.answer
        )
        mData.add(newSubject)
        return newSubject
    }

    override suspend fun updateSubject(subject: Subject): Boolean {
        for (i in 0 until mData.size) {
            if (mData[i].id == subject.id) {
                mData[i] = subject
                return true
            }
        }
        return false
    }

    override suspend fun deleteSubject(id: Int): Boolean {
        for (i in 0 until mData.size) {
            if (mData[i].id == id) {
                mData.removeAt(i)
                return true
            }
        }
        return false
    }
}