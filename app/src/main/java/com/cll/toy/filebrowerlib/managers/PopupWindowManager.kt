package com.cll.toy.filebrowerlib.managers

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.ListPopupWindow
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.cll.toy.filebrowerlib.R
import com.cll.toy.filebrowerlib.utils.WindowUtils
import kotlinx.android.synthetic.main.popup_edit_window_layout.view.*

/**
 * Created by cll on 2018-09-03.
 */

enum class PopupWindowManager {
    SINGLETON;


    fun init(context : Context, anachor : View, item : Array<String>, listener: OnItemClick) : ListPopupWindow{
        val popupWindow = ListPopupWindow(context);
        popupWindow.width = WindowUtils().displayPxWidth / 2
//        popupWindow.width =  ListPopupWindow.WRAP_CONTENT;
        popupWindow.height = ListPopupWindow.WRAP_CONTENT;
        popupWindow.anchorView = anachor;
        popupWindow.setBackgroundDrawable(BitmapDrawable());
        popupWindow.setAdapter(ArrayAdapter<String>(context, R.layout.popup_dropdown_item_textview, item))
        popupWindow.setDropDownGravity(Gravity.END)
        popupWindow.isModal = true;
        popupWindow.setOnItemClickListener{ parent, view, position, id ->
            if (listener != null) {
                listener.onClick(parent, view, position, id)
            }

            if (popupWindow != null && popupWindow.isShowing) {
                popupWindow.dismiss()
            }
        }
        popupWindow.show()
        return popupWindow;
    }


    internal class Edit(){

        private var mContext : Context? = null ;
        private var mTitle : String? = null;
        private var mListener : OnButtonClickListener? = null;
        private var mHideText : String? = null;
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

        fun setHideText(text : String) : Edit{
            mHideText = text;
            return this;
        }

        fun setHideText(text : Int) : Edit{
            mHideText = mContext!!.resources.getString(text);
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
                val text = view.popup_edit_edittext.text.toString();
                if (TextUtils.isEmpty(text)) Toast.makeText(mContext, "内容不能为空", Toast.LENGTH_SHORT).show() else  if (window.isShowing)  window.dismiss();
                mListener!!.onPositiveClick(text)
            }
            view.popup_edit_cancel.setOnClickListener {
                window.dismiss();
                mListener!!.onNegativeClick()
            }
            view.popup_edit_root_layout.setOnClickListener {
                Toast.makeText(mContext, "view", Toast.LENGTH_LONG).show()
            }
            view.popup_edit_edittext.setText(mHideText)
//            view.popup_edit_edittext.setHint(if (TextUtils.isEmpty(mHideText)) mContext!!.resources.getString(R.string.edit_hint_message_default) else mHideText!!);
            window.showAtLocation(anchor, Gravity.CENTER, 0, 0)
            return this;
        }
    }





    public interface OnButtonClickListener {
        fun onPositiveClick(content : String);
        fun onNegativeClick();
    }

    public interface OnItemClick {
        fun onClick(parent : AdapterView<*>, view : View, position : Int, id : Long)
    }
}
