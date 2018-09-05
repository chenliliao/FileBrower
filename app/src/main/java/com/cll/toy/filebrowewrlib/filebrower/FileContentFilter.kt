package com.cll.toy.filebrowewrlib.filebrower

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import com.cll.toy.filebrowewrlib.filebrower.browerview.ListLayoutView
import com.cll.toy.filebrowewrlib.filebrower.canstants.BroadcastAction
import com.cll.toy.filebrowewrlib.filebrower.event.UpdataAdapterEvent
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-28.
 */

class FileContentFilter(){

    var dirPath : String? = null;
    var isHideFiles = false;

    fun setDirPath(dir : String): FileContentFilter {
        dirPath = dir;
        if (mListener != null) mListener!!.change();
        return this;
    }

    fun setHiddenFiles(status : Boolean) : FileContentFilter{
        isHideFiles = status;
        if (mListener != null) mListener!!.change();
        return this;
    }

    fun setDouble(path : String, isHide : Boolean){
        dirPath = path;
        isHideFiles = isHide;
        if (mListener != null) mListener!!.change();
    }

    private var mListener : ChangeListener? = null;
    fun setChangeListener(listener : ChangeListener){
        mListener = listener;
    }

    public interface ChangeListener{
        public fun change();
    }
}