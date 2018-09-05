package com.cll.toy.filebrowewrlib.filebrower

import android.app.Application
import android.content.Context

/**
 * Created by cll on 2018-09-05.
 */

class FlieBrowerApp : Application(){

    private var mContent : Context? = null;
    override fun onCreate() {
        super.onCreate()
        mContent = applicationContext;
    }

    fun getAppContent(): Context? {
        return mContent;
    }
}
