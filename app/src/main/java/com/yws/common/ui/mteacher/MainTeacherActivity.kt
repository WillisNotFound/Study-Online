package com.yws.common.ui.mteacher

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yws.common.R
import com.yws.common.ui.mteacher.exam.ExaminationFragment
import com.yws.common.ui.mteacher.mine.MineFragment
import com.yws.common.ui.mteacher.subject.SubjectFragment

class MainTeacherActivity : AppCompatActivity() {
    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, MainTeacherActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_teacher)
        findViewById<BottomNavigationView>(R.id.teacher_bottom_nav).apply {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.tab_teacher_subject -> {
                        supportFragmentManager.commit {
                            val fragment =
                                supportFragmentManager.findFragmentByTag("SubjectFragment") ?: SubjectFragment()
                            replace(R.id.teacher_fragment_container, fragment, "SubjectFragment")
                            setReorderingAllowed(true)
                            addToBackStack(null)
                        }
                    }

                    R.id.tab_teacher_examination -> {
                        supportFragmentManager.commit {
                            val fragment =
                                supportFragmentManager.findFragmentByTag("ExaminationFragment") ?: ExaminationFragment()
                            replace(R.id.teacher_fragment_container, fragment, "ExaminationFragment")
                            setReorderingAllowed(true)
                            addToBackStack(null)
                        }
                    }

                    R.id.tab_teacher_mine -> {
                        supportFragmentManager.commit {
                            val fragment = supportFragmentManager.findFragmentByTag("MineFragment") ?: MineFragment()
                            replace(R.id.teacher_fragment_container, fragment, "MineFragment")
                            setReorderingAllowed(true)
                            addToBackStack(null)
                        }
                    }
                }
                true
            }
            selectedItemId = R.id.tab_teacher_subject
        }
    }

    override fun onStart() {
        super.onStart()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = 0xFFEEEEEE.toInt()
    }
}