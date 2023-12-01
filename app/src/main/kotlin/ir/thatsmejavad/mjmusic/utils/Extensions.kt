package ir.thatsmejavad.mjmusic.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast

internal fun Context.openApplicationSettings() {
    runCatching {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        intent.data = Uri.parse("package:$packageName")
        startActivity(intent)
    }.getOrElse { error ->
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }
}
