package ir.thatsmejavad.mjmusic.enums

import android.Manifest
import android.os.Build
import ir.thatsmejavad.mjmusic.R
import ir.thatsmejavad.mjmusic.enums.PermissionTypes.BLUETOOTH
import ir.thatsmejavad.mjmusic.enums.PermissionTypes.MEDIA_AUDIO
import ir.thatsmejavad.mjmusic.enums.PermissionTypes.NOTIFICATION
import ir.thatsmejavad.mjmusic.enums.PermissionTypes.PHONE_STATUS
import ir.thatsmejavad.mjmusic.enums.PermissionTypes.STORAGE

enum class PermissionTypes(val permission: String) {
    STORAGE(permission = Manifest.permission.READ_EXTERNAL_STORAGE),
    MEDIA_AUDIO(permission = Manifest.permission.READ_MEDIA_AUDIO),
    NOTIFICATION(permission = Manifest.permission.POST_NOTIFICATIONS),
    PHONE_STATUS(permission = Manifest.permission.READ_PHONE_STATE),
    BLUETOOTH(permission = Manifest.permission.BLUETOOTH_ADMIN)
}

fun PermissionTypes.getTitle(): Int {
    return when (this) {
        STORAGE -> R.string.label_storage_access
        MEDIA_AUDIO -> R.string.label_media_library_access
        NOTIFICATION -> R.string.label_notification_access
        PHONE_STATUS -> R.string.label_phone_status_and_identity
        BLUETOOTH -> R.string.label_bluetooth_access
    }
}

fun PermissionTypes.getDescription(): Int {
    return when (this) {
        STORAGE -> R.string.label_storage_access_description
        MEDIA_AUDIO -> R.string.label_media_library_access_description
        NOTIFICATION -> R.string.label_notification_access_description
        PHONE_STATUS -> R.string.label_phone_status_description
        BLUETOOTH -> R.string.label_bluetooth_access_description
    }
}

fun getPermissionsList(): List<String> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            PHONE_STATUS.permission,
            MEDIA_AUDIO.permission,
            NOTIFICATION.permission
        )
    } else {
        listOf(
            PHONE_STATUS.permission,
            STORAGE.permission
        )
    }
}
