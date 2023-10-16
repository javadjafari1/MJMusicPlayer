package ir.thatsmejavad.mjmusic.di

import androidx.room.Room
import ir.thatsmejavad.mjmusic.data.db.MJMusicDatabase
import ir.thatsmejavad.mjmusic.data.db.dao.AlbumDao
import ir.thatsmejavad.mjmusic.data.db.dao.ArtistDao
import ir.thatsmejavad.mjmusic.data.db.dao.SongArtistCrossRefDao
import ir.thatsmejavad.mjmusic.data.db.dao.SongDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<MJMusicDatabase> {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = MJMusicDatabase::class.java,
            name = "MJMusic_db"
        ).build()
    }

    single<SongDao> { get<MJMusicDatabase>().getSongDao() }
    single<AlbumDao> { get<MJMusicDatabase>().getAlbumDao() }
    single<ArtistDao> { get<MJMusicDatabase>().getArtistsDao() }
    single<SongArtistCrossRefDao> { get<MJMusicDatabase>().getSongArtistCrossRefDao() }
}
