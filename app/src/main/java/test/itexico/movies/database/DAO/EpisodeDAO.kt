package test.itexico.movies.database.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import test.itexico.movies.model.Episode

@Dao
interface EpisodeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(episode: Episode)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(episodeList: List<Episode>)

    @Update
    fun update(episode: Episode)

    @Update
    fun updateAll(episodeList: List<Episode>)

    @Delete
    fun delete(episode: Episode)

    @Delete
    fun deleteAll(episodeList: List<Episode>)

    @Query("Select * from episode where season=:season")
    fun getAllEpisodes(season: Int): LiveData<List<Episode>>

    @Query("Select * from episode where season=:season and number=:number")
    fun getEpisode(season: Int, number: Int): LiveData<Episode>
}