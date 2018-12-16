package com.example.lzl.java.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PermissionsHelper {
    private static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    private static final String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    public static void requestPermissions(Context contect){
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(contect,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if (ContextCompat.checkSelfPermission(contect,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()){  //申请的集合不为空时，表示有需要申请的权限
//            ActivityCompat.requestPermissions((Activity) contect,permissionList.toArray(new String[permissionList.size()]),0);

            ActivityCompat.requestPermissions(
                    (Activity) contect, new String[] {READ_PHONE_STATE,WRITE_EXTERNAL_STORAGE}, 0);
        }else { //所有的权限都已经授权过了
            Log.e("yyy","已经受过权了");
        }
    }

    public static boolean hasCameraPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    /** Check to see if we need to show the rationale for this permission. */
    public static boolean shouldShowRequestPermissionRationale(Activity activity) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, READ_PHONE_STATE)&&
                ActivityCompat.shouldShowRequestPermissionRationale(activity, WRITE_EXTERNAL_STORAGE);
    }

    /** Launch Application Setting to grant permission. */
    public static void launchPermissionSettings(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
    }
}
