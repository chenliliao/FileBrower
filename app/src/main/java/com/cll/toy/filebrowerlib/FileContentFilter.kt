package com.cll.toy.filebrowerlib

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.cll.toy.filebrowerlib.bean.FileBean
import com.cll.toy.filebrowerlib.browerview.ListLayoutView
import com.cll.toy.filebrowerlib.canstants.BroadcastAction
import com.cll.toy.filebrowerlib.event.UpdataAdapterEvent
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-28.
 */

class FileContentFilter(type : Int){

    var dirPath : String? = null;
    var isHideFiles = false;
    var type : Int = type;
    var letter : String? = null;

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

    fun setAll(path : String, isHide : Boolean, type_ : Int, letter_ : String?){
        dirPath = path;
        isHideFiles = isHide;
        type = type_;
        letter = letter_;
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