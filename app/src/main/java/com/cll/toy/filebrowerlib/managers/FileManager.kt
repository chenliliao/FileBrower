package com.cll.toy.filebrowerlib.managers

import android.util.Log
import java.io.File

/**
 * Created by cll on 2018-09-03.
 */

enum class FileManager {

    SINGLETON;


    public val TYPE_DIR : Int = 0;
    public val TYPE_FILE : Int = 1;
    fun create(filePath: String, type : Int) : Boolean{
        val file = File(filePath)
        if (type == TYPE_DIR) return file.mkdirs() else return file.createNewFile();
    }

    fun create(dir: String, file_name : String, type : Int) : Boolean{
        val file = File(dir, file_name);
        if (type == TYPE_DIR) return file.mkdirs() else return file.createNewFile();
    }

    fun create(file: File, type : Int) : Boolean{
        if (type == TYPE_DIR) return file.mkdirs() else return file.createNewFile();
    }

    fun delete(filePath: String) : Boolean{
        val file = File(filePath);
        if (file.isFile){
            return file.delete();
        }else {
            forFiles(file)
            return file.delete();
        }
    }

    private fun forFiles(file: File){
        val list = file.listFiles()
        if (list.size > 0) {
            for (i in 0..list.size - 1) {
                if(list[i].isFile){
                    delete(list[i])
                }else{
                    forFiles(list[i])
                }
            }
        }
    }


    fun delete(file: File) : Boolean{
        if (!file.exists()){
            return false;
        }
        if (file.isDirectory){
            forFiles(file);
        }
        return file.delete();
    }
}
