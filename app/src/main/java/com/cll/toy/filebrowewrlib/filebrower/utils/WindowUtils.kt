package com.cll.toy.filebrowewrlib.filebrower.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.Display
import android.view.WindowManager
import com.cll.toy.filebrowewrlib.filebrower.FileBrowerApp

/**
 * Created by cll on 2018/7/4.
 */

class WindowUtils {


    private var sMetrics: DisplayMetrics? = null
    private var sDisplay: Display? = null

    val displayPxWidth: Int
        get() = getDisplayMetrics().widthPixels

    val displayPxHeight: Int
        get() = getDisplayMetrics().heightPixels

    val screenDensity: Int
        get() = getDisplayMetrics().densityDpi

    private fun getDisplay(): Display {
        if (sDisplay == null) {
            sDisplay = (FileBrowerApp.content!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
        }
        return sDisplay!!;
    }

    private fun getDisplayMetrics(): DisplayMetrics {
        if (sMetrics == null) {
            sMetrics = DisplayMetrics()
            getDisplay().getMetrics(sMetrics)
        }
        return sMetrics!!;
    }
}
