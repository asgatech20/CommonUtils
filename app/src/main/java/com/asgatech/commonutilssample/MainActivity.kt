package com.asgatech.commonutilssample

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asgatech.commonutils.LocalityHelper
import com.asgatech.commonutils.ScreenHelpers
import com.asgatech.commonutils.permissions.PermissionHandler
import com.asgatech.commonutils.permissions.PermissionsCallback

class MainActivity : AppCompatActivity(), PermissionsCallback {
    private lateinit var permissionHandler: PermissionHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ScreenHelpers().setFullscreen(this, findViewById(R.id.mainView))
        permissionHandler = PermissionHandler()
        permissionHandler.askForPermissions(
            this,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            this
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionHandler.onPermissionResult(this, requestCode, permissions)
    }

    override fun onPermissionGranted(grantedPermissions: ArrayList<String>) {
        Log.e("permissions", "onPermissionGranted: ${grantedPermissions.toString()}")
    }

    override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
        Log.e("permissions", "onPermissionDenied: ${deniedPermissions.toString()}")

    }

    override fun onPermissionBlocked(blockedPermissions: ArrayList<String>) {
        Log.e("permissions", "onPermissionBlocked: ${blockedPermissions.toString()}")
        for (permission in blockedPermissions)
        Toast.makeText(this, permissionHandler.getPermissionLocalizedName(this, permission),Toast.LENGTH_LONG).show()

    }

}