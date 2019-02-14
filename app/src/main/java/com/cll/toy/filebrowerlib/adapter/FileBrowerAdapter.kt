package com.cll.toy.filebrowerlib.adapter

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cll.toy.filebrowerlib.FunctionExecute
import com.cll.toy.filebrowerlib.R
import com.cll.toy.filebrowerlib.bean.FileBean
import kotlinx.android.synthetic.main.listview_mode_item_layout.view.*

/**
 * Created by cll on 2018-08-29.
 */

class FileBrowerAdapter(context : Context?, datas : ArrayList<FileBean>?) : BaseAdapter() {

    private val mContext : Context? = context;
    var mData : ArrayList<FileBean>? = datas;
    var type : Int? = FunctionExecute.FILE_FILTER_LETTER_LIST;

    override fun getCount(): Int {
        return if (mData!!.size <= 0) 0 else mData!!.size
    }

    override fun getItem(position: Int): Any? {
        return mData?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder : Holder;
        var view = convertView;

        if (view == null){
            holder = Holder();
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_mode_item_layout, null)
            holder.file_name = view.file_name;
            holder.file_size = view.file_size;
            holder.file_image = view.file_image;
            view.tag = holder;
        }

        holder = view?.tag as Holder;

//        if (type == FunctionExecute.FILE_FILTER_LETTER_LIST){
//            holder.file_image!!.setImageResource(R.drawable.icon_a)
//            setIconLetter(mData?.get(position)?.file_head_letter, holder.file_image!!)
//        }else{
            holder.file_name!!.text = mData?.get(position)?.file_name
            holder.file_size!!.text = mData?.get(position)?.file_size
            holder.file_image!!.setImageResource(mData?.get(position)?.file_image!!)
//        }
        return view
    }


    class Holder(){
        var file_name : TextView? = null;
        var file_size : TextView? = null;
        var file_image : ImageView? = null;
    }

    private fun setIconLetter(letter : String?, view : ImageView){
        Log.w("TAG", "test get head setIconLetter xxxxxx " + mData!!.size)
        Log.w("TAG", "test get head setIconLetter xxxxxx s " + letter)
        when(letter){
            "b" -> {view.setImageResource(R.drawable.icon_b); Log.w("TAG", "test get head setIconLetter b") }
            "c" -> {view.setImageResource(R.drawable.icon_c) ; Log.w("TAG", "test get head setIconLetter c") }
            "d" -> {view.setImageResource(R.drawable.icon_d) ; Log.w("TAG", "test get head setIconLetter d") }
            else -> {view.setImageResource(R.drawable.icon_a)}
        }
    }

}
