//package com.cll.toy.filebrowewrlib.filebrower.bean
//
//import android.content.Context
//import android.content.Intent
//import com.cll.toy.filebrowewrlib.filebrower.canstants.BroadcastAction
//import java.io.FileFilter
//
///**
// * Created by cll on 2018-08-30.
// */
//
//class CurrentInfoBean(){
//
//
//    internal var currentPath : String? = null;
//    internal var isHideFiles : Boolean = false;
//    private var mListener : ChangeListener? = null;
//    fun setChangeListener(listener : ChangeListener){
//        mListener = listener;
//    }
//
//    fun setCurrentPath(path : String?){
//        currentPath = path;
//        if (mListener != null){
//            mListener!!.change()
//        }
//    }
//
//    fun setDouble(path : String?, isHide : Boolean){
//        setCurrentPath(path);
//        isHideFiles = isHide;
//    }
//
//    public interface ChangeListener{
//        public fun change();
//    }
//}
