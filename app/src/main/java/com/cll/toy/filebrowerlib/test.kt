package com.cll.toy.filebrowerlib

import android.text.TextUtils

import java.text.Collator
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.stream.Collector

/**
 * Created by cll on 2018-09-07.
 */

class test {

    //    test(String a){
    //
    //    }
    //    test(int a){
    //
    //    }
    private fun s() {
        val list = ArrayList<String>()
        Collections.sort(list) { source, target ->
            if (TextUtils.isEmpty(source) && TextUtils.isEmpty(target)) {

            }
            0
        }

    }
}
