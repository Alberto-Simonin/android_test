package test.itexico.movies.database.DAO

import android.arch.lifecycle.LiveData
import android.arch.paging.DataSource
import android.arch.paging.PagedList
import android.arch.persistence.room.*
import test.itexico.movies.model.Season

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
    fun getAllSeasons(): DataSource.Factory<Integer, Season>

    @Query ("Select * from season where number=:number")
    fun getSeason(number: Int): LiveData<Season>
}