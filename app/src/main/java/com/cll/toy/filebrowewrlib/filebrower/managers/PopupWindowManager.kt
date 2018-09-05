package com.cll.toy.filebrowewrlib.filebrower.managers

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.ListPopupWindow
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cll.toy.filebrowewrlib.filebrower.R
import kotlinx.android.synthetic.main.popup_edit_window_layout.view.*
import java.util.*

/**
 * Created by cll on 2018-09-03.
 */

enum class PopupWindowManager {
    SINGLETON;


    fun init(context : Context, anachor : View, item : ArrayList<String>, listener: OnItemClick) : ListPopupWindow{
        val popupWindow = ListPopupWindow(context);
        popupWindow.width = ListPopupWindow.WRAP_CONTENT;
        popupWindow.height = ListPopupWindow.WRAP_CONTENT;
        popupWindow.anchorView = anachor;
        popupWindow.setBackgroundDrawable(BitmapDrawable());
        popupWindow.setAdapter(ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, item))
        popupWindow.isModal = true;
        popupWindow.setOnItemClickListener{ parent, view, position, id ->
            if (listener != null) {
                listener.onClick(parent, view, position, id)
            }

            if (popupWindow != null && popupWindow.isShowing) {
                popupWindow.dismiss()
            }
        }
        return popupWindow;
    }


    internal class Edit(){

        private var mContext : Context? = null ;
        private var mTitle : String? = null;
        private var mListener : OnButtonClickListener? = null;
        fun with(context: Context) : Edit{
            mContext = context;
            return this;
        }

        fun setTitle(title : String): Edit {
            mTitle = title;
            return this;
        }

        fun setButtonClick(listener : OnButtonClickListener) : Edit{
            mListener = listener;
           return this;
        }

        fun create(anchor : View) : Edit{
            val window = PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            val view = LayoutInflater.from(mContext).inflate(R.layout.popup_edit_window_layout, null);
            window.contentView = view;
            window.setBackgroundDrawable(BitmapDrawable());
            window.isOutsideTouchable = true;
            window.isFocusable = true;
            view.popup_edit_title.text = mTitle;
            view.popup_edit_confirm.setOnClickListener {
                window.dismiss();
                mListener!!.onPositiveClick()
            }
            view.popup_edit_cancel.setOnClickListener {
                window.dismiss();
                mListener!!.onNegativeClick()
            }
            view.popup_edit_root_layout.setOnClickListener {
                Toast.makeText(mContext, "view", Toast.LENGTH_LONG).show()
            }
            window.showAtLocation(anchor, Gravity.CENTER, 0, 0)
            return this;
        }
    }





    public interface OnButtonClickListener {
        fun onPositiveClick();
        fun onNegativeClick();
    }

    public interface OnItemClick {
        fun onClick(parent : AdapterView<*>, view : View, position : Int, id : Long)
    }
}
