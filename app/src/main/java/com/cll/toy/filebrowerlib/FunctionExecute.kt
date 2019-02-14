package com.cll.toy.filebrowerlib

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import com.cll.toy.filebrowerlib.R
import com.cll.toy.filebrowerlib.bean.FileBean
import com.cll.toy.filebrowerlib.browerview.ListLayoutView
import com.cll.toy.filebrowerlib.utils.SpellingUtil
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Created by cll on 2018-08-30.
 */

class FunctionExecute(){



    companion object {
//        var headLetterData = ArrayList<FileBean>()
        var headData = ArrayList<FileBean>()
        var filterData = ArrayList<FileBean>()
        const val FILE_FILTER_ALL_DATA_LIST : Int= 1
        const val FILE_FILTER_LETTER_DATA_LIST : Int= 2
        const val FILE_FILTER_LETTER_LIST : Int= 3
    }

    fun filterData(fileFilter : FileContentFilter, letter : String){

    }

    fun fileData(fileFilter : FileContentFilter, type : Int, letter : String?) : ArrayList<FileBean> {
        val data = ArrayList<FileBean>();
        val fileData_tmp = ArrayList<FileBean>();
//        val handLetterData = ArrayList<String>();
        if (!TextUtils.isEmpty(fileFilter.dirPath)){
            val file = File(fileFilter.dirPath);
            val files = file.listFiles();
            if (files != null){
                Arrays.sort(files)
                var tmp = ArrayList<FileBean>();
                for(i in 0..files.size - 1){
                    val head =  SpellingUtil.getHeadLettle(files[i].name)
//                    Log.w("TAG", "test fileData type , letter head 3 = " + head)
                    if ((type == 2 && TextUtils.equals(head, letter)) || type == 3){
                        if (tmp == null){
                            tmp = ArrayList<FileBean>();
                        }
                        if (files[i].isFile){
                            addData(files[i], fileData_tmp, fileFilter.isHideFiles, head)
                        }else{
                            addData(files[i], tmp, fileFilter.isHideFiles, head);
                        }
                    }else if (type ==1){
                        if (files[i].isFile){
                            addData(files[i], fileData_tmp, fileFilter.isHideFiles, head)
                        }else{
                            addData(files[i], data, fileFilter.isHideFiles, head);
                        }
                    }
                }
                if (type == 3 ){
                    tmp?.let { data.clear(); data.addAll(letterFilter(tmp)) }
                }else if ((type == 2 )){
                    data.addAll(tmp)
                }
                Log.w("TAG", "test updataAdapter getdata 0.1= " + data!!.size)
                fileData_tmp?.let { data.addAll(fileData_tmp) }
                return data;
            }else{
                Throwable("check permission read...")
            }
        }else{
            Throwable("root dir has not to set")
        }
        return data;
    }

    private fun addData(file: File, data : ArrayList<FileBean>, isHideFiles : Boolean , head :String){
        if (file.isHidden){
            if (isHideFiles){
//                    continue;
            }else{
                data.add(addFileAttribute(file, head));
            }
        }else{
            data.add(addFileAttribute(file, head));
        }
    }

    private fun addFileAttribute(file : File, head :String) : FileBean{
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
        bean.file_head_letter = head;
        return bean;
    }

    private fun letterFilter(list : ArrayList<FileBean>) : ArrayList<FileBean>{
//        Log.w("TAG", "test get head letterFilter 1set " + list.size)
        val head = ArrayList<String>();
        for (i in 0..list.size - 1){
            head.add(list[i].file_head_letter!!)
        }
        list.clear()
        val set = HashSet<String>();
        if (set.addAll(head)){
            head.clear()
            head.addAll(set)
            Collections.sort(head)
            for (i in 0..head.size - 1){
                val newInfo = FileBean(head[i])
                list.add(newInfo)
            }
        }
        return list;
    }
}
