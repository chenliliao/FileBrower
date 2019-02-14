package com.cll.toy.filebrowerlib

import android.app.Application
import android.content.Context
import com.github.promeg.pinyinhelper.Pinyin
import com.github.promeg.tinypinyin.lexicons.android.cncity.CnCityDict

/**
 * Created by cll on 2018-09-05.
 */

class FileBrowerApp : Application(){


    override fun onCreate() {
        super.onCreate()
        content = this;
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(this)));
    }

    companion object {
        var content : Context? = null
        private set
    }

//    fun getAppContent(): Context? {
//        return mContent;
//    }
}
