package com.cll.toy.filebrowerlib.utils

import android.text.TextUtils
import android.util.Log
import com.github.promeg.pinyinhelper.Pinyin

/**
 * Created by cll on 2018-09-07.
 */

object SpellingUtil{

    fun getHeadLettle(content : String) : String{
        val head : String = Pinyin.toPinyin(content, "")
        Log.w("TAG", "test get pingyin  head = " +  head)
        if (TextUtils.isEmpty(head)) return "" else return head.substring(0,1).toLowerCase()
    }
}
