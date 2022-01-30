package com.asgatech.commonutils.permissions

/**
 * @usage Handles the result of permissions if user grants, denied or blocked the permissions
 */
interface PermissionsCallback {
    fun onPermissionGranted(grantedPermissions: ArrayList<String>)

    fun onPermissionDenied(deniedPermissions: ArrayList<String>)

    fun onPermissionBlocked(blockedPermissions: ArrayList<String>)
}