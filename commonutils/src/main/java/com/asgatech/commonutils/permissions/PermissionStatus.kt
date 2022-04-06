package com.asgatech.commonutils.permissions

/**
 * @usage Enum Class that represents the permission result status
 */
enum class PermissionStatus(val value: Int) {
    GRANTED(1), DENIED(2), BLOCKED(3)
}