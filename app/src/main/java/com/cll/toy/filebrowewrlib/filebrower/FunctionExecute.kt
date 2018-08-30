package com.cll.toy.filebrowewrlib.filebrower

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.cll.toy.filebrowewrlib.filebrower.bean.CurrentInfoBean
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import com.cll.toy.filebrowewrlib.filebrower.browerview.ListLayoutView
import java.io.File
import java.util.*

/**
 * Created by cll on 2018-08-30.
 */

class FunctionExecute(context : Context){

////    var rootDir: String? = null;
//    var isHideFiles : Boolean = false

    var mContext = context;
    var mView : ListLayoutView? = null;
    var fileFilter : FileContentFilter? = null;

    init{
        fileFilter = FileContentFilter()
                .setRootDirectory(Environment.getExternalStorageDirectory().absolutePath)
                .showHiddenFiles(false)
    }

    fun showView() : ListLayoutView?{
        return displayList(fileFilter!!.mRootDirectory, fileFilter!!.isHideFiles);
    }


    private fun displayList(rootDir : String?, isHideFiles : Boolean) : ListLayoutView?{
        mView = ListLayoutView(mContext, rootDir, isHideFiles);
        return mView;
    }


    fun onKeyDownBack(): Boolean {
        if (mView != null){
            return mView!!.onKeyDownBack()
        }
        return false;
    }


    fun fileData(rootDir : String?) : ArrayList<FileBean> {
        val data = ArrayList<FileBean>();
        if (!TextUtils.isEmpty(rootDir)){
            val file = File(rootDir);
            val files = file.listFiles();
            if (files != null){
                Arrays.sort(files)
                for(i in 0..files.size - 1){
                    if (files[i].isHidden){
                        if (CurrentInfoBean().isHideFiles){
                            continue;
                        }else{
                            data.add(addFileAttribute(files[i]));
                        }
                    }else{
                        data.add(addFileAttribute(files[i]));
                    }
                }
                return data;
            }else{
                Throwable("check permission read...")
            }
        }else{
            Throwable("root dir has not to set")
        }
        return data;
    }

    private fun addFileAttribute(file : File) : FileBean{
        val bean = FileBean()
        bean.file_name = file.name;
        if (file.isFile){
            bean.file_size = file.length().toString() + "B"
            bean.file_image = R.drawable.ic_launcher_foreground
        }else{
            bean.file_size = File(file.absolutePath).listFiles().size.toString() + "内容"
            bean.file_image = R.drawable.icon_folderblue
        }
        bean.file_path = file.absolutePath;
        return bean;
    }
}
