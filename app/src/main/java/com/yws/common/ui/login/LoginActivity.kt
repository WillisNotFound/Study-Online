package com.yws.common.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import com.yws.baselib.utils.collectWhenResumed
import com.yws.common.R

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc:
 * SinceVer:
 */
class LoginActivity : AppCompatActivity() {
    private val mViewModel by viewModels<LoginViewModel>()

    companion object {
        fun startAction(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mViewModel.loginOptionFlow.collectWhenResumed(lifecycleScope) {
            when (it) {
                LoginOption.Student -> {
                    supportFragmentManager.commitNow {
                        replace(R.id.login_fragment_container, StudentLoginFragment())
                    }
                }

                LoginOption.Teacher -> {
                    supportFragmentManager.commitNow {
                        replace(R.id.login_fragment_container, TeacherLoginFragment())
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = 0xFFEEEEEE.toInt()
    }
}