package com.cll.toy.filebrowewrlib.filebrower

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import com.cll.toy.filebrowewrlib.filebrower.browerview.ListLayoutView
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-28.
 */

class FileContentFilter(){

    var mRootDirectory : String? = null;
    var isHideFiles = false;

    fun setRootDirectory(dir : String): FileContentFilter {
        mRootDirectory = dir;
        return this;
    }

    fun showHiddenFiles(status : Boolean) : FileContentFilter{
        isHideFiles = status
        return this;
    }

    private fun setChange(){

    }
}