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
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-30.
 */

class FunctionExecute(context : Context){

////    var rootDir: String? = null;
//    var isHideFiles : Boolean = false

    internal var mContext = context;
    internal var mView : ListLayoutView? = null;
    internal var fileFilter : FileContentFilter? = null;

    init{
        fileFilter = FileContentFilter()
                .setRootDirectory(Environment.getExternalStorageDirectory().absolutePath)
                .showHiddenFiles(false)
    }

    fun getFileFilter() : FileContentFilter?{
        return fileFilter;
    }

    fun updateFileFilter(isHideFiles : Boolean){
        Log.w("TAG", "test updata file filter isHideFiles 1" + isHideFiles)
        fileFilter!!.isHideFiles = isHideFiles
        Log.w("TAG", "test updata file filter isHideFiles 2" + isHideFiles)
        mView!!.updataAdapter(null, fileFilter!!.isHideFiles);
    }

    fun showView() : ListLayoutView?{
        Log.w("TAG", "test updata file filter isHideFiles 3" + fileFilter!!.isHideFiles)
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


    fun fileData(currentInfo : CurrentInfoBean) : ArrayList<FileBean> {
        val data = ArrayList<FileBean>();
        val fileData = ArrayList<FileBean>();
        if (!TextUtils.isEmpty(currentInfo.currentPath)){
            val file = File(currentInfo.currentPath);
            val files = file.listFiles();
            if (files != null){
                Arrays.sort(files)
                for(i in 0..files.size - 1){
                    if (files[i].isFile){
                        addData(files[i], fileData, currentInfo.isHideFiles)
                    }else{
                        addData(files[i], data, currentInfo.isHideFiles)
                    }
                }
                data.addAll(fileData)
                return data;
            }else{
                Throwable("check permission read...")
            }
        }else{
            Throwable("root dir has not to set")
        }
        return data;
    }

    private fun addData(file: File, data : ArrayList<FileBean>, isHideFiles : Boolean){
        if (file.isHidden){
            if (isHideFiles){
//                    continue;
            }else{
                data.add(addFileAttribute(file));
            }
        }else{
            data.add(addFileAttribute(file));
        }
    }

    private fun addFileAttribute(file : File) : FileBean{
        val bean = FileBean()
        bean.file_name = file.name;
        if (file.isFile){
            bean.file_size = file.length().toString() + "B"
//            bean.file_image = R.drawable.ic_launcher_foreground
            bean.file_image = R.mipmap.ic_launcher;
        }else{
            bean.file_size = File(file.absolutePath).listFiles().size.toString() + "内容"
            bean.file_image = R.drawable.icon_folderblue
        }
        bean.file_path = file.absolutePath;
        return bean;
    }
}
