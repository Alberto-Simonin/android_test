package test.itexico.movies.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import test.itexico.movies.database.DAO.EpisodeDAO
import test.itexico.movies.database.DAO.SeasonDAO
import test.itexico.movies.model.objects.Episode
import test.itexico.movies.model.objects.Season

@Database(entities = arrayOf(Season::class, Episode::class), version = 1)
@TypeConverters(DataConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun seasonDAO() : SeasonDAO
    abstract fun episodeDAO(): EpisodeDAO

    companion object {

        private val DB_NAME = "moviesDatabase"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if(instance ==null)  {
                instance = buildDatabase(context)
            }
            return instance as AppDatabase
        }

        private fun buildDatabase(context: Context): AppDatabase {
            //TODO change queries to working thread
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME).allowMainThreadQueries().build()
        }
    }

}