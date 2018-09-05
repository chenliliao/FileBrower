package com.cll.toy.filebrowewrlib.filebrower

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import com.cll.toy.filebrowewrlib.filebrower.browerview.ListLayoutView
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-30.
 */

class FunctionExecute(){

    fun fileData(fileFilter : FileContentFilter) : ArrayList<FileBean> {
        val data = ArrayList<FileBean>();
        val fileData = ArrayList<FileBean>();
        if (!TextUtils.isEmpty(fileFilter.dirPath)){
            val file = File(fileFilter.dirPath);
            val files = file.listFiles();
            if (files != null){
                Arrays.sort(files)
                for(i in 0..files.size - 1){
                    if (files[i].isFile){
                        addData(files[i], fileData, fileFilter.isHideFiles)
                    }else{
                        addData(files[i], data, fileFilter.isHideFiles)
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
