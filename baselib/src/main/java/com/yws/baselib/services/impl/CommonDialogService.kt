package com.yws.baselib.services.impl

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.yws.baselib.dialog.CommonConfirmDialogFragment
import com.yws.baselib.services.api.ConfirmDialogBuilder
import com.yws.baselib.services.api.ICommonDialogService
import kotlinx.coroutines.suspendCancellableCoroutine

/**
 * User: lxl
 * Date: 2021/10/11
 * Desc: 通用对话框实现，自定义就自己实现
 * SinceVer: 1.0.0
 */
object CommonDialogService : ICommonDialogService {
    private const val TAG = "DialogService"

    override suspend fun showConfirmDialogEx(
        fragmentManager: FragmentManager,
        builder: ConfirmDialogBuilder
    ): Boolean? {
        return suspendCancellableCoroutine { continuation ->
            CommonConfirmDialogFragment().apply {
                this.mContinuation = continuation
                this.mBuilder = builder
            }.show(fragmentManager, toString())
        }
    }

    override suspend fun showConfirmDialogEx(fragment: Fragment, builder: ConfirmDialogBuilder): Boolean? {
        return showConfirmDialogEx(fragment.childFragmentManager, builder)
    }

    override suspend fun showConfirmDialog(fragmentManager: FragmentManager, builder: ConfirmDialogBuilder): Boolean {
        return showConfirmDialogEx(fragmentManager, builder) == true
    }

    override suspend fun showConfirmDialog(fragment: Fragment, builder: ConfirmDialogBuilder): Boolean {
        return showConfirmDialog(fragment.childFragmentManager, builder)
    }
}