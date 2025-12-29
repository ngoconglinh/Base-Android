package com.universe.base.core.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {

    fun checkPermission(
        activity: Activity,
        permission: String,
        onGranted: () -> Unit = {},
        onDenied: (shouldShowRationale: Boolean) -> Unit
    ) {
        when {
            ContextCompat.checkSelfPermission(activity, permission) == PERMISSION_GRANTED -> {
                onGranted()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) -> {
                // In an educational UI, explain to the user why your app requires this
                // permission for a specific feature to behave as expected, and what
                // features are disabled if it's declined. In this UI, include a
                // "cancel" or "no thanks" button that lets the user continue
                // using your app without granting the permission.
                onDenied(true)
            }

            else -> {
                // You can directly ask for the permission.
                onDenied(false)
            }
        }
    }

    fun isPermissionGranted(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PERMISSION_GRANTED
    }

    fun arePermissionsGranted(context: Context, permissions: List<String>): Boolean {
        return permissions.all { permission ->
            ContextCompat.checkSelfPermission(context, permission) == PERMISSION_GRANTED
        }
    }

    fun hasCameraPermissionGranted(context: Context): Boolean {
        return isPermissionGranted(context, Manifest.permission.CAMERA)
    }
}