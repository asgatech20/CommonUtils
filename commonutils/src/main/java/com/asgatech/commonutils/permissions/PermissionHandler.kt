package com.asgatech.commonutils.permissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * @usage: handle the checking of granted permission and to make it easier to handle asking for not granted permissions
 */
class PermissionHandler {
    private val PERMISSION_REQ = 452
    private var permissionCallback: PermissionsCallback? = null

    /**
     * Check if certain permission is granted
     */
    fun isPermissionGranted(context: Context, permission: String): Boolean {
        val selfPermission = ContextCompat.checkSelfPermission(context, permission)
        return selfPermission == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Check if certain permissions are granted or not.
     */
    fun isPermissionGranted(context: Context, permissions: Array<String>): Boolean {
        val grantedOnes: MutableList<String> = ArrayList()
        for (permission in permissions) {
            if (isPermissionGranted(context, permission)) grantedOnes.add(permission)
        }
        return grantedOnes.size == permissions.size
    }

    /**
     * Ask user to grant certain permission
     */
    fun askForPermission(
        context: AppCompatActivity,
        permission: String,
        permissionCallback: PermissionsCallback
    ) {
        this.permissionCallback = permissionCallback
        ActivityCompat.requestPermissions(
            context,
            arrayOf(permission),
            PERMISSION_REQ
        )
    }

    /**
     * Ask user to grant certain permissions
     */
    fun askForPermissions(
        context: AppCompatActivity,
        permissions: Array<String>,
        permissionCallback: PermissionsCallback
    ) {
        this.permissionCallback = permissionCallback
        ActivityCompat.requestPermissions(
            context,
            permissions,
            PERMISSION_REQ
        )
    }

    /**
     * Check the status of requested permissions (granted, denied or blocked)
     */
    fun onPermissionResult(
        context: AppCompatActivity,
        requestCode: Int,
        permissions: Array<String>
    ) {
        if (permissionCallback != null && requestCode == PERMISSION_REQ && !permissions.isNullOrEmpty()) {
            val grantedList = ArrayList<String>()
            val blockedList = ArrayList<String>()
            val deniedList = ArrayList<String>()
            // fetch the status of every permission
            checkPermissionsStatus(permissions, context, grantedList, deniedList, blockedList)
            // set the permission result to the callback
            setPermissionsResult(blockedList, deniedList, grantedList)
        }

    }

    /**
     * fetch the permissions status
     */
    private fun setPermissionsResult(
        blockedList: ArrayList<String>,
        deniedList: ArrayList<String>,
        grantedList: ArrayList<String>
    ) {
        when {
            blockedList.size > 0 -> permissionCallback!!.onPermissionBlocked(blockedList)
            deniedList.size > 0 -> permissionCallback!!.onPermissionDenied(deniedList)
            grantedList.size > 0 -> permissionCallback!!.onPermissionGranted(grantedList)
        }
    }

    /**
     * Check the result permissions status
     */
    private fun checkPermissionsStatus(
        permissions: Array<String>,
        context: AppCompatActivity,
        grantedList: ArrayList<String>,
        deniedList: ArrayList<String>,
        blockedList: ArrayList<String>
    ) {
        for (permission in permissions) {
            when {
                getPermissionStatus(
                    context,
                    permission
                ) == PermissionStatus.GRANTED.value -> grantedList.add(permission)
                getPermissionStatus(
                    context,
                    permission
                ) == PermissionStatus.DENIED.value -> deniedList.add(permission)
                getPermissionStatus(
                    context,
                    permission
                ) == PermissionStatus.BLOCKED.value -> blockedList.add(permission)
            }
        }
    }

    /**
     * Return the result status of certain permission
     */
    private fun getPermissionStatus(activity: Activity?, permission: String?): Int {
        return if (ContextCompat.checkSelfPermission(
                activity!!,
                permission!!
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                PermissionStatus.BLOCKED.value
            } else PermissionStatus.DENIED.value
        } else PermissionStatus.GRANTED.value
    }

    /**
     * Get localized name for certain permission
     */
    fun getPermissionLocalizedName(context: AppCompatActivity, permission: String): String {
        val pm: PackageManager = context.packageManager
        val info = pm.getPermissionGroupInfo(permission, 0);
        return info.loadLabel(pm).toString()
    }
}