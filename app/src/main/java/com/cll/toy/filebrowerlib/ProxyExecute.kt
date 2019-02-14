package com.cll.toy.filebrowerlib

import android.content.Context
import android.os.Environment
import android.util.Log
import com.cll.toy.filebrowerlib.browerview.ListLayoutView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by cll on 2018-09-05.
 */
class ProxyExecute(context : Context){
    internal var mContext = context;
    internal var mView : ListLayoutView? = null;
    internal var fileFilter : FileContentFilter? = null;

    init{
        fileFilter = FileContentFilter(FunctionExecute.FILE_FILTER_ALL_DATA_LIST)
                .setDirPath(Environment.getExternalStorageDirectory().absolutePath)
                .setHiddenFiles(true)
    }

    fun updataCurrentInfo(isHideFiles : Boolean){
        Log.w("TAG","test getCurrentInfo 3 " + isHideFiles)
        getListView()!!.updataFileFilter(null, isHideFiles,getFileFilter()!!.type, getFileFilter()!!.letter);
    }

    fun showView() : ListLayoutView?{
        return displayList();
    }


    private fun displayList() : ListLayoutView?{
        mView = ListLayoutView(mContext, fileFilter!!);
        return mView;
    }


    fun onKeyDownBack(): Boolean {
        if (mView != null){
            return mView!!.onKeyDownBack()
        }
        return false;
    }

    fun getListView(): ListLayoutView? {
        return mView
    }

    fun getFileFilter() : FileContentFilter?{
        return mView!!.getFileFilter();
    }
}