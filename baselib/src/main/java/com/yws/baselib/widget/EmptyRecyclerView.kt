package com.yws.baselib.widget

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.yws.baselib.utils.gone
import com.yws.baselib.utils.visible

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 含有空白页的RecyclerView
 * SinceVer:
 */
class EmptyRecyclerView(private val emptyView: View, context: Context) : RecyclerView(context) {
    private val mObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            checkIfEmpty()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            checkIfEmpty()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            checkIfEmpty()
        }
    }

    override fun setAdapter(newAdapter: Adapter<*>?) {
        adapter?.unregisterAdapterDataObserver(mObserver)
        super.setAdapter(newAdapter)
        newAdapter?.registerAdapterDataObserver(mObserver)
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        val isEmptyViewVisible = adapter?.itemCount == 0
        if (isEmptyViewVisible) {
            emptyView.visible()
        } else {
            emptyView.gone()
        }
    }
}