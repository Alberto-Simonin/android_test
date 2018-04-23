package test.itexico.movies.database.DAO

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import test.itexico.movies.model.objects.Season

@Dao
interface SeasonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(season: Season)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(seasonList: List<Season>)

    @Update
    fun update(season: Season)

    @Update
    fun updateAll(seasonList: List<Season>)

    @Delete
    fun delete(season: Season)

    @Delete
    fun deleteAll(seasonList: List<Season>)

    @Query("Select * from season")
    fun getAllSeasons(): LiveData<List<Season>>

    @Query ("Select * from season where number=:number")
    fun getSeason(number: Int): LiveData<Season>
}