package ir.thatsmejavad.mjmusic.core.contentProvider.extension

import android.content.ContentResolver
import android.database.ContentObserver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

fun ContentResolver.query(
    uri: Uri,
    projection: Array<String>? = null,
    selection: String = "",
    selectionArguments: Array<String>? = null,
    order: String = MediaStore.MediaColumns._ID,
    ascending: Boolean = true,
    offset: Int = 0,
    limit: Int = Int.MAX_VALUE
): Cursor? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val queryArgs = Bundle().apply {
            putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
            putInt(ContentResolver.QUERY_ARG_OFFSET, offset)
            putStringArray(ContentResolver.QUERY_ARG_SORT_COLUMNS, arrayOf(order))
            putInt(
                ContentResolver.QUERY_ARG_SORT_DIRECTION,
                if (ascending) {
                    ContentResolver.QUERY_SORT_DIRECTION_ASCENDING
                }
                else {
                    ContentResolver.QUERY_SORT_DIRECTION_DESCENDING
                }
            )
            if (selectionArguments != null) {
                putStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, selectionArguments)
            }
            putString(ContentResolver.QUERY_ARG_SQL_SELECTION, selection)
        }
        return query(uri, projection, queryArgs, null)
    } else {
        //language=SQL
        val sortOrder = order + (if (ascending) " ASC" else " DESC") + " LIMIT $limit OFFSET $offset"
        return query(uri, projection, selection, selectionArguments, sortOrder)}
}

fun ContentResolver.observeChanges(uri: Uri) = callbackFlow {
    val observer = object : ContentObserver(null) {
        override fun onChange(selfChange: Boolean) {
            super.onChange(selfChange)
            trySend(selfChange)
        }
    }
    registerContentObserver(uri, true, observer)
    // trigger first
    trySend(true)
    awaitClose {
        unregisterContentObserver(observer)
    }
}
