package com.cll.toy.filebrowewrlib.filebrower.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by cll on 2018/7/13.
 */

public class PermissionUtils {


    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
    };

    public synchronized static boolean isUnderLollipop(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            Toast.makeText(App.getAppContext(), "under", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }



    public synchronized static boolean up6Premission(final Activity activity){

        ArrayList<String> list = new ArrayList<>();
        list.clear();
        for (String permission : PERMISSIONS_STORAGE){
            if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
                list.add(permission);
            }
        }
        if (list != null && list.size() > 0){
            String[] newPermission = new String[list.size()];
            list.toArray(newPermission);
            ActivityCompat.requestPermissions(activity, newPermission,1);
            return false;
        }
        return true;
    }
    public synchronized static void checkPermission(final Activity activity, final String permission){
        Log.w("TAG","test not permission 1");
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
            Log.w("TAG","test not permission 2");
            ActivityCompat.requestPermissions(activity, new String[]{permission}, 2);
        }
    }

    public synchronized static boolean checkPermission(final Activity activity, final String permission, int code){
        Log.w("TAG","test not permission 1");
        if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED){
            Log.w("TAG","test not permission 2");
            ActivityCompat.requestPermissions(activity, new String[]{permission}, code);
            return false;
        }
        return true;
    }


}
