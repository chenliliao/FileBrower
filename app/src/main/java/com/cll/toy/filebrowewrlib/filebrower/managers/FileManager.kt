package com.cll.toy.filebrowewrlib.filebrower.managers

import java.io.File

/**
 * Created by cll on 2018-09-03.
 */

enum class FileManager {

    SINGLETON;


    fun create(filePath: String) : Boolean{
        val file = File(filePath);
        return file.createNewFile();
    }

    fun create(file: File) : Boolean{
        return file.createNewFile();
    }

    fun delete(filePath: String) : Boolean{
        val file = File(filePath);
        return file.delete();
    }

    fun delete(file: File) : Boolean{
        return file.delete();
    }
}
