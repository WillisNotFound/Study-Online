package com.yws.baselib.services.api

import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 统一确认弹框展示
 * SinceVer:
 */
interface ICommonDialogService {

    /**
     * 便捷的展示确认对话框方法，对话框内容见[ConfirmDialogBuilder]
     * 这里忽略了activity重建的场景，重建则按键无效
     * @return true为确认，false为取消或者无选择关闭
     */
    suspend fun showConfirmDialog(fragment: Fragment, builder: ConfirmDialogBuilder): Boolean

    suspend fun showConfirmDialog(fragmentManager: FragmentManager, builder: ConfirmDialogBuilder): Boolean

    /**
     * 便捷的展示确认对话框方法，对话框内容见[ConfirmDialogBuilder]
     * 支持三态
     * @return true为确认，false为取消，null为无选择关闭
     */
    suspend fun showConfirmDialogEx(fragment: Fragment, builder: ConfirmDialogBuilder): Boolean?

    suspend fun showConfirmDialogEx(fragmentManager: FragmentManager, builder: ConfirmDialogBuilder): Boolean?
}

interface ILoadingDialog {
    /**
     * 关闭loading对话框
     */
    fun dismiss()
}

class ConfirmDialogBuilder(val title: CharSequence) {

    /**
     * 内容
     */
    var messageContent: CharSequence? = null

    /**
     * 内容排版，通常为[Gravity.CENTER]或[Gravity.LEFT]
     * 默认为[Gravity.CENTER]
     */
    var messageContentGravity: Int = Gravity.CENTER

    /**
     * 确认按钮文案
     */
    var confirmButton: String = "确定"

    /**
     * 确认按钮颜色
     */
    var confirmButtonTextColor: Int = 0xFFFF4D4D.toInt()

    /**
     * 取消按钮文案，null则不展示
     */
    var cancelButton: String? = "取消"

    /**
     * 取消按钮颜色
     */
    var cancelButtonTextColor: Int = 0xFF848896.toInt()

    /**
     * 是否可取消（不能取消则只能通过按钮点击关闭）
     * 默认为可取消
     */
    var isCancellable: Boolean = true

    /**
     * 是否可点击外部取消
     * 默认为可取消
     */
    var isCanceledOnTouchOutside: Boolean = false
}