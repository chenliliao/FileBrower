package com.cll.toy.filebrowewrlib.filebrower.browerview

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.cll.toy.filebrowewrlib.filebrower.FunctionExecute
import com.cll.toy.filebrowewrlib.filebrower.R
import com.cll.toy.filebrowewrlib.filebrower.adapter.FileBrowerAdapter
import com.cll.toy.filebrowewrlib.filebrower.bean.CurrentInfoBean
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import kotlinx.android.synthetic.main.listview_mode_brower_layout.view.*
import java.io.File
import java.util.*

/**
 * Created by cll on 2018-08-29.
 */
class ListLayoutView(context: Context?, rootDir : String?, isHideFiles : Boolean) : FrameLayout(context) {

    var adapter : FileBrowerAdapter? = null;
    var sView : View? = null;
    var currentInfo : CurrentInfoBean? = null;
    var data : ArrayList<FileBean>?  = null;

    init {
        if (currentInfo == null){
            currentInfo = CurrentInfoBean();
        }

        currentInfo!!.isHideFiles = isHideFiles;
        init(rootDir)
    }

    private fun init(rootDir : String?){
        sView = LayoutInflater.from(context).inflate(R.layout.listview_mode_brower_layout, this);
        updataAdapter(rootDir, currentInfo!!.isHideFiles);
        sView!!.brower_listview_layout.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var file = File(data!!.get(position).file_path)
            if (file.isDirectory) updataAdapter(file.absolutePath, currentInfo!!.isHideFiles);
            else  Toast.makeText(context, "无法打开文件", Toast.LENGTH_LONG).show()
        };
    }

     fun onKeyDownBack() : Boolean{
        if (currentInfo != null){
            var path = File(currentInfo!!.currentPath).parent
            if (!TextUtils.equals(path, Environment.getExternalStorageDirectory().parent)){
                updataAdapter(File(currentInfo!!.currentPath).parent, currentInfo!!.isHideFiles);
                return true;
            }else{
//                 Toast.makeText(context, "无法向上", Toast.LENGTH_LONG).show()
                return false;
            }
        }
         return false;
    }
     fun updataAdapter(path : String?, isHideFiles :Boolean){
         if (TextUtils.isEmpty(path)){
             currentInfo!!.currentPath = currentInfo!!.currentPath
         }else{
             currentInfo!!.currentPath = path;
         }

         currentInfo!!.isHideFiles = isHideFiles;
        data = FunctionExecute(context).fileData(currentInfo!!);
        adapter = FileBrowerAdapter(context, data);
        sView!!.brower_listview_layout.adapter = adapter;
        sView!!.brower_current_path.text =  currentInfo!!.currentPath;
    }
}