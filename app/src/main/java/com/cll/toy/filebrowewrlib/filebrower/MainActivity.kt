package com.cll.toy.filebrowewrlib.filebrower

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import com.cll.toy.filebrowewrlib.filebrower.utils.PermissionUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mProxy : ProxyExecute? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mProxy = ProxyExecute(this)
        if (PermissionUtils.up6Premission(this)){
            this.contact_framlayout.addView(mProxy!!.showView())
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setContentView(mProxy!!.showView())
            }else{
                PermissionUtils.up6Premission(this)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mProxy!!.onKeyDownBack()){
                return true;
            }else{
                return  super.onKeyDown(keyCode, event)
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId;
        if (id == R.id.menu_setting){
            val info = mProxy!!.getFileFilter();
            if (info == null) return false;
            if (info.isHideFiles){
                item.title = this.resources.getString(R.string.text_show_content_hide_files)
                mProxy!!.updataCurrentInfo(false)
            }else{
                item.title = this.resources.getString(R.string.text_show_content_display_files)
                mProxy!!.updataCurrentInfo(true)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}