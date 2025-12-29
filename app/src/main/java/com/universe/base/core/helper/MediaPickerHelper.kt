package com.universe.base.core.helper

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.universe.base.utils.Constants.MIME_TYPE_IMAGE
import com.universe.base.utils.Constants.MIME_TYPE_VIDEO
import com.universe.base.utils.VersionUtils

class MediaPickerHelper(
    private val activity: ComponentActivity,
    private val onMediaSelected: (Uri) -> Unit
) {
    private var modernMediaPicker: ActivityResultLauncher<PickVisualMediaRequest>? = null
    private var legacyDocumentPicker: ActivityResultLauncher<Array<String>>? = null

    fun initialize() {
        if (VersionUtils.hasR()) {
            modernMediaPicker =
                activity.registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                    handleMediaSelection(uri)
                }
        } else {
            legacyDocumentPicker =
                activity.registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
                    handleMediaSelection(uri)
                }
        }
    }

    private fun handleMediaSelection(uri: Uri?) {
        uri?.let {
            try {
                activity.contentResolver.takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
                onMediaSelected(uri)
            } catch (e: SecurityException) {
                e.printStackTrace()
                onMediaSelected(uri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun selectImage() {
        if (VersionUtils.hasR()) {
            modernMediaPicker?.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        } else {
            legacyDocumentPicker?.launch(arrayOf(MIME_TYPE_IMAGE))
        }
    }

    fun selectVideo() {
        if (VersionUtils.hasR()) {
            modernMediaPicker?.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly)
            )
        } else {
            legacyDocumentPicker?.launch(arrayOf(MIME_TYPE_VIDEO))
        }
    }

    fun selectImageOrVideo() {
        if (VersionUtils.hasR()) {
            modernMediaPicker?.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
            )
        } else {
            legacyDocumentPicker?.launch(arrayOf(MIME_TYPE_IMAGE, MIME_TYPE_VIDEO))
        }
    }

    fun clear() {
        modernMediaPicker = null
        legacyDocumentPicker = null
    }
}