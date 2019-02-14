package com.cll.toy.filebrowerlib.browerview

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
import com.cll.toy.filebrowerlib.R
import com.cll.toy.filebrowerlib.FileContentFilter
import com.cll.toy.filebrowerlib.FunctionExecute
import com.cll.toy.filebrowerlib.adapter.FileBrowerAdapter
import com.cll.toy.filebrowerlib.bean.FileBean
import com.cll.toy.filebrowerlib.canstants.BroadcastAction
import com.cll.toy.filebrowerlib.event.UpdataAdapterEvent
import com.cll.toy.filebrowerlib.managers.FileManager
import com.cll.toy.filebrowerlib.managers.PopupWindowManager
import com.cll.toy.filebrowerlib.type.FileExecuteType
import kotlinx.android.synthetic.main.listview_mode_brower_layout.view.*
import java.io.File
import java.io.FileFilter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by cll on 2018-08-29.
 */
class ListLayoutView(context: Context?, fileFilter : FileContentFilter) : FrameLayout(context){

    private val TAG = "ListLayoutView";
    private var adapter : FileBrowerAdapter? = null;
    private var sView : View? = null;
//    private var currentInfo : CurrentInfoBean? = null;
    private var data : ArrayList<FileBean>?  = null;
    private var mFileFilter : FileContentFilter? = fileFilter;
    private var mFirstVisibleItem : Int = 0;
    private val mScrollListener = object : AbsListView.OnScrollListener{
        override fun onScroll(view: AbsListView?, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
            mFirstVisibleItem = firstVisibleItem;
        }
        override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        }
    }

    private var mRecordMap = HashMap<String, Int>();

    init {
        mFileFilter?.let {
            mFileFilter!!.setChangeListener(object : FileContentFilter.ChangeListener{
                override fun change() {
                    data = FunctionExecute().fileData(mFileFilter!!, mFileFilter!!.type, mFileFilter!!.letter);
                    updataAdapter()
                }
            })
            init(mFileFilter!!)
        }
    }

    //    ****************************************************
    //    **************** private  a ************************
    //    ****************************************************
    private fun init(fileFilter : FileContentFilter){
        sView = LayoutInflater.from(context).inflate(R.layout.listview_mode_brower_layout, this);
        updataFileFilter(fileFilter.dirPath, fileFilter.isHideFiles, fileFilter.type, null);
        data = FunctionExecute().fileData(mFileFilter!!,mFileFilter!!.type, mFileFilter!!.letter);
        adapter = FileBrowerAdapter(context, data);
        sView?.let {
            brower_listview_layout.adapter = adapter;
            brower_current_path.text = mFileFilter!!.dirPath;
            brower_listview_layout.setOnScrollListener(mScrollListener);
        }
        listener(fileFilter)
    }

    private fun listener(fileFilter : FileContentFilter){
        sView!!.brower_listview_layout.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            var file = File(data!!.get(position).file_path)
            mRecordMap.put(file.parent, mFirstVisibleItem)
            if (file.isDirectory) updataFileFilter(file.absolutePath, fileFilter!!.isHideFiles, FunctionExecute.FILE_FILTER_ALL_DATA_LIST, null);
            else  Toast.makeText(context, context.resources.getString(R.string.toast_show_content_display_files), Toast.LENGTH_LONG).show()
        };
        sView!!.brower_file_edit_button.setOnClickListener {
            PopupWindowManager.Edit().with(context)
                    .setTitle("创建文件夹")
                    .setHideText(R.string.edit_hint_message_new_file_dir)
                    .setButtonClick(object : PopupWindowManager.OnButtonClickListener{
                        override fun onNegativeClick() {
                            Toast.makeText(context, "nagative", Toast.LENGTH_LONG).show()
                        }

                        override fun onPositiveClick(content : String) {
                            val result = FileManager.SINGLETON.create(mFileFilter!!.dirPath!!, content, FileManager.SINGLETON.TYPE_DIR);
                            if (result) {
                                data = FunctionExecute().fileData(mFileFilter!!,mFileFilter!!.type, mFileFilter!!.letter);
                                updataAdapter()
                            }
                            Toast.makeText(context,  content + "  "+result, Toast.LENGTH_LONG).show()
                        }
                    }).create(this); }
        sView!!.brower_listview_layout.onItemLongClickListener =  AdapterView.OnItemLongClickListener{parent, view, position, id ->
            var list = arrayOf(FileExecuteType.COPY.mValue, FileExecuteType.CUT.mValue, FileExecuteType.DELETE.mValue)
            val path = data!!.get(position).file_path;
            PopupWindowManager.SINGLETON.init(context, view, list, object : PopupWindowManager.OnItemClick{
                override fun onClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (position == FileExecuteType.DELETE.mIndex){
                        val result = FileManager.SINGLETON.delete(File(path))
                        if (result) {
                            data = FunctionExecute().fileData(mFileFilter!!,mFileFilter!!.type, mFileFilter!!.letter);
                            updataAdapter()
                        }
                        Toast.makeText(context,  path + "  "+result, Toast.LENGTH_LONG).show()
                    }
                }
            })
            true;
        }

        sView!!.brower_file_edit_button2.setOnClickListener {
            updataFileFilter(fileFilter!!.dirPath, fileFilter!!.isHideFiles, FunctionExecute.FILE_FILTER_LETTER_DATA_LIST, "s");
        }
    }

    private fun updataAdapter(){
        if (data == null) return;
        if (adapter != null){
            Log.w(TAG, "test updataAdapter getdata x= " + data!!.size)
            adapter!!.mData = data
//            adapter!!.type = type
            adapter!!.notifyDataSetChanged()
            val recordItem = mRecordMap!!.get(mFileFilter!!.dirPath);
            mFirstVisibleItem = if (recordItem == null) 0 else recordItem;
            sView!!.brower_listview_layout.setSelection(mFirstVisibleItem)
            sView!!.brower_current_path.text = mFileFilter!!.dirPath;
        }

    }
    //    **************** private  b ************************

    //    ****************************************************
    //    **************** public  a ************************
    //    ****************************************************
    fun updataFileFilter(path : String?, isHideFiles :Boolean, type : Int, letter : String?){
        if (TextUtils.isEmpty(path)){
            mFileFilter!!.setAll(mFileFilter!!.dirPath!!, isHideFiles, type, letter)
        }else{
            mFileFilter!!.setAll(path!!, isHideFiles, type, letter)
        }
    }
    fun onKeyDownBack() : Boolean{
        if (mFileFilter != null){
            var path = File(mFileFilter!!.dirPath).parent
            if (!TextUtils.equals(path, Environment.getExternalStorageDirectory().parent)){
                updataFileFilter(File(mFileFilter!!.dirPath).parent, mFileFilter!!.isHideFiles, mFileFilter!!.type, null);
                return true;
            }else{
                if (mFileFilter!!.type == FunctionExecute.FILE_FILTER_ALL_DATA_LIST){
                    updataFileFilter(mFileFilter!!.dirPath, mFileFilter!!.isHideFiles,FunctionExecute.FILE_FILTER_ALL_DATA_LIST, mFileFilter!!.letter);
                    return true;
                }
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