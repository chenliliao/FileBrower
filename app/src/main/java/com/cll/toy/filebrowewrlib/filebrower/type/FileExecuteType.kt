package com.cll.toy.filebrowewrlib.filebrower.type

/**
 * Created by cll on 2018-09-05.
 */

enum class FileExecuteType private constructor(var mIndex: Int, var mValue: String) {


    COPY(0, "复制"), CUT(1, "剪切"), DELETE(2, "删除")

    //    public String getValuess(){
    //        return mValue;
    //    }
    //
    //    public int getIndexss(){
    //        return mIndex;
    //    }
}
