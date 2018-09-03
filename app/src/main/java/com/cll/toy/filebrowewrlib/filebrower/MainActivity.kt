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

    var mExecute  : FunctionExecute =  FunctionExecute(this);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (PermissionUtils.checkPermission(this,  Manifest.permission.READ_EXTERNAL_STORAGE, 1)){
            this.contact_framlayout.addView(mExecute.showView())
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setContentView(mExecute.showView())
            }else{
                PermissionUtils.checkPermission(this,  Manifest.permission.READ_EXTERNAL_STORAGE, 1)
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            if (mExecute.onKeyDownBack()){
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
            if (mExecute.getFileFilter()!!.isHideFiles){
                item.title = "隐藏文件"
                mExecute.updateFileFilter(false)
            }else{
                item.title = "显示隐藏文件"
                mExecute.updateFileFilter(true)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}