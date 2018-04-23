package test.itexico.movies.model.objects

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Season(@PrimaryKey var number: Int,
             @ColumnInfo var ids: IDs?,
             @ColumnInfo var rating: String?,
             @ColumnInfo var votes: String?,
             @ColumnInfo var episode_count: String?,
             @ColumnInfo var aired_episodes: String?,
             @ColumnInfo var title: String?,
             @ColumnInfo var overview: String?,
             @ColumnInfo var first_aired: String?,
             @ColumnInfo var network: String?)
