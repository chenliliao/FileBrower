package com.cll.toy.filebrowewrlib.filebrower

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.EditText

import java.util.HashMap

/**
 * Created by cll on 2018-09-06.
 */

class test : Activity(), AdapterView.OnItemClickListener {
    private fun test(context: Context) {
        val editText = EditText(context)
        editText.setText("ddd")
        val map = HashMap<String, Int>()
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

    }
}
