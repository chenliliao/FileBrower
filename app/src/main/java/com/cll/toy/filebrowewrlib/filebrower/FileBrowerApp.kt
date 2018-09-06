package com.cll.toy.filebrowewrlib.filebrower

import android.app.Application
import android.content.Context

/**
 * Created by cll on 2018-09-05.
 */

class FileBrowerApp : Application(){


    override fun onCreate() {
        super.onCreate()
        content = this;
    }

    companion object {
        var content : Context? = null
        private set
    }

//    fun getAppContent(): Context? {
//        return mContent;
//    }
}
