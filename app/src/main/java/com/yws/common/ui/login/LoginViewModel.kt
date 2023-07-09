package com.yws.common.ui.login

import androidx.lifecycle.ViewModel
import com.yws.baselib.data.BaseResult
import com.yws.baselib.utils.toIntSafely
import com.yws.common.func.studentManager
import com.yws.common.func.teacherManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc:
 * SinceVer:
 */
class LoginViewModel : ViewModel() {
    private val mLoginOptionFlow: MutableStateFlow<LoginOption> = MutableStateFlow(LoginOption.Teacher)
    val loginOptionFlow: StateFlow<LoginOption> = mLoginOptionFlow

    fun switchToStudentLogin() {
        mLoginOptionFlow.value = LoginOption.Student
    }

    fun switchToTeacherLogin() {
        mLoginOptionFlow.value = LoginOption.Teacher
    }

    suspend fun studentLogin(account: String, password: String): BaseResult<String> {
        return if (studentManager.login(account.toIntSafely(), password)) {
            BaseResult.Success("${studentManager.current?.name}，欢迎你~")
        } else {
            BaseResult.Failure("登陆失败")
        }
    }

    suspend fun teacherLogin(account: String, password: String): BaseResult<String> {
        return if (teacherManager.login(account.toIntSafely(), password)) {
            BaseResult.Success("${teacherManager.current?.name}，欢迎你~")
        } else {
            BaseResult.Failure("登陆失败")
        }
    }
}

sealed interface LoginOption {
    object Student : LoginOption

    object Teacher : LoginOption
}