package com.cll.toy.filebrowewrlib.filebrower.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.cll.toy.filebrowewrlib.filebrower.R
import com.cll.toy.filebrowewrlib.filebrower.bean.FileBean
import kotlinx.android.synthetic.main.listview_mode_item_layout.view.*

/**
 * Created by cll on 2018-08-29.
 */

class FileBrowerAdapter(context : Context?, val data : ArrayList<FileBean>?) : BaseAdapter() {

    val mContext : Context? = context;

    override fun getCount(): Int {
        return data!!.size
    }

    override fun getItem(position: Int): Any? {
        return data?.get(position)
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
        holder.file_name!!.text = data!!.get(position).file_name
        holder.file_size!!.text = data!!.get(position).file_size
        holder.file_image!!.setImageResource(data!!.get(position).file_image)
        return view
    }


    class Holder(){
        var file_name : TextView? = null;
        var file_size : TextView? = null;
        var file_image : ImageView? = null;
    }
}
