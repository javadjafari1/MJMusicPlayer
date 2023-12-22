package ir.thatsmejavad.mjmusic.ui.common

import android.content.Context
import ir.thatsmejavad.mjmusic.R

enum class MusicCategory(val value: Int) {
    ALBUM(R.string.label_albums),
    ARTISTS(R.string.label_artists),
    SONGS(R.string.label_songs),
    PLAYLISTS(R.string.label_playlists),
    GENRES(R.string.label_genres),
    FOLDERS(R.string.label_folders),
}

fun Context.getMusicCategories() = MusicCategory.values().map { getString(it.value) }
