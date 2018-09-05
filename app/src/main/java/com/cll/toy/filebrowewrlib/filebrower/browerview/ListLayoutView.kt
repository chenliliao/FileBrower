package com.cll.toy.filebrowewrlib.filebrower.browerview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Environment
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.*
import com.cll.toy.filebrowewrlib.filebrower.FileContentFilter
import com.cll.toy.filebrowewrlib.filebrower.FunctionExecute
import com.cll.toy.filebrowewrlib.filebrower.R
import com.cll.toy.filebrowewrlib.filebrower.adapter.FileBrowerAdapter
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import com.cll.toy.filebrowewrlib.filebrower.canstants.BroadcastAction
import com.cll.toy.filebrowewrlib.filebrower.event.UpdataAdapterEvent
import com.cll.toy.filebrowewrlib.filebrower.managers.FileManager
import com.cll.toy.filebrowewrlib.filebrower.managers.PopupWindowManager
import kotlinx.android.synthetic.main.listview_mode_brower_layout.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusBuilder
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.io.FileFilter
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by cll on 2018-08-29.
 */
class ListLayoutView(context: Context?, fileFilter : FileContentFilter) : FrameLayout(context) {

    private val TAG = "ListLayoutView";
    private var adapter : FileBrowerAdapter? = null;
    private var sView : View? = null;
//    private var currentInfo : CurrentInfoBean? = null;
    private var data : ArrayList<FileBean>?  = null;

    private var mFileFilter : FileContentFilter? = fileFilter;


    init {
        if (mFileFilter != null){
            mFileFilter!!.setChangeListener(object : FileContentFilter.ChangeListener{
                override fun change() {
                    updataAdapter()
                }
            })
            init(mFileFilter!!)
        };

    }

    //    ****************************************************
    //    **************** private  a ************************
    //    ****************************************************
    private fun init(fileFilter : FileContentFilter){
        sView = LayoutInflater.from(context).inflate(R.layout.listview_mode_brower_layout, this);
        updataCurrentInfo(fileFilter.dirPath, fileFilter.isHideFiles);
        sView!!.brower_listview_layout.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var file = File(data!!.get(position).file_path)
            if (file.isDirectory) updataCurrentInfo(file.absolutePath, fileFilter!!.isHideFiles);
            else  Toast.makeText(context, "无法打开文件", Toast.LENGTH_LONG).show()
        };
        sView!!.brower_listview_layout.onItemLongClickListener =  AdapterView.OnItemLongClickListener{parent, view, position, id ->
//            val list = ArrayList<String>();
//            list.add("0")
//            val v = PopupWindowManager.SINGLETON.init(context, view, list, object : PopupWindowManager.OnItemClick{
//                override fun onClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
////                    Log.w("TAG", "test PopupWindowManager " + currentInfo!!.currentPath + "/ss.txt")
////                    FileManager.SINGLETON.create(File(currentInfo!!.currentPath + "/ss.txt"))
////                    updataAdapter(currentInfo!!.currentPath, currentInfo!!.isHideFiles);
//                    PopupWindowManager.Edit().with(context).create(view);
//                }
//            })
//            v.show()
            PopupWindowManager.Edit().with(context)
                    .setTitle("我是标题，i am a title")
                    .setButtonClick(object : PopupWindowManager.OnButtonClickListener{
                        override fun onNegativeClick() {
                            Toast.makeText(context, "nagative", Toast.LENGTH_LONG).show()
                        }

                        override fun onPositiveClick() {
                            Toast.makeText(context, "positive", Toast.LENGTH_LONG).show()
                        }
                    }).create(view);
            true;
        }
        listener()
    }

    private fun listener(){
        sView!!.brower_file_edit_button.setOnClickListener {  }
    }

    private fun updataAdapter(){
        data = FunctionExecute().fileData(mFileFilter!!);
        if (data == null) return;

        adapter = FileBrowerAdapter(context, data);
        sView!!.brower_listview_layout.adapter = adapter;
        sView!!.brower_current_path.text =  mFileFilter!!.dirPath;
    }
    //    **************** private  b ************************

    //    ****************************************************
    //    **************** public  a ************************
    //    ****************************************************
    fun updataCurrentInfo(path : String?, isHideFiles :Boolean){
        if (TextUtils.isEmpty(path)){
            mFileFilter!!.setDouble(mFileFilter!!.dirPath!!, isHideFiles)
        }else{
            mFileFilter!!.setDouble(path!!, isHideFiles)
        }
    }
    fun onKeyDownBack() : Boolean{
        if (mFileFilter != null){
            var path = File(mFileFilter!!.dirPath).parent
            if (!TextUtils.equals(path, Environment.getExternalStorageDirectory().parent)){
                updataCurrentInfo(File(mFileFilter!!.dirPath).parent, mFileFilter!!.isHideFiles);
                return true;
            }else{
//                 Toast.makeText(context, "无法向上", Toast.LENGTH_LONG).show()
                return false;
            }
        }
        return false;
    }

    fun getFileFilter(): FileContentFilter? {
        return mFileFilter;
    }
    //    **************** public  b ************************



//    **************** listener ************************
}