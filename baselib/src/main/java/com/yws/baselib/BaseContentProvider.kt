package com.yws.baselib

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import com.yws.baselib.utils.initAppUtils

/**
 * Author: yanwensheng@yy.com
 * Date: 2023/6/21
 * Desc: 用来初始化[initAppUtils]
 * SinceVer:
 */
class BaseContentProvider : ContentProvider() {
    override fun onCreate(): Boolean {
        initAppUtils(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }
}