package com.yws.common.ui.mstudent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yws.common.R
import com.yws.common.ui.mstudent.exam.StuExamFragment
import com.yws.common.ui.mstudent.mine.StuMineFragment
import com.yws.common.ui.mstudent.answerpaper.StuAnsPaperFragment

/**
 * description: none
 * @author yan.w.s@qq.com
 * <p>time: 2023/6/23
 * <p>version: 1.0
 * <p>update: none
 */
class MainStudentActivity : AppCompatActivity() {
    private val mStuExamFragment = StuExamFragment()
    private val mStuAnsPaperFragment = StuAnsPaperFragment()
    private val mStuMineFragment = StuMineFragment()
    private var mCurFragment: Fragment = mStuExamFragment

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, MainStudentActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_student)
        supportFragmentManager.commit {
            add(R.id.student_fragment_container, mStuExamFragment)
            add(R.id.student_fragment_container, mStuAnsPaperFragment)
            add(R.id.student_fragment_container, mStuMineFragment)
            hide(mStuAnsPaperFragment)
            hide(mStuMineFragment)
        }
        findViewById<BottomNavigationView>(R.id.student_bottom_nav).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.tab_student_examination -> {
                        supportFragmentManager.commit {
                            hide(mCurFragment)
                            show(mStuExamFragment)
                            mCurFragment = mStuExamFragment
                        }
                    }

                    R.id.tab_student_answer_paper -> {
                        supportFragmentManager.commit {
                            hide(mCurFragment)
                            show(mStuAnsPaperFragment)
                            mCurFragment = mStuAnsPaperFragment
                        }
                    }

                    R.id.tab_student_mine -> {
                        supportFragmentManager.commit {
                            hide(mCurFragment)
                            show(mStuMineFragment)
                            mCurFragment = mStuMineFragment
                        }
                    }
                }
                true
            }
            selectedItemId = R.id.tab_student_examination
        }
    }

    override fun onStart() {
        super.onStart()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = 0xFFEEEEEE.toInt()
    }
}