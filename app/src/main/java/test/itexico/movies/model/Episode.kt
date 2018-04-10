package test.itexico.movies.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Episode(@ColumnInfo var season: Int,
              @PrimaryKey var number: Int,
              @ColumnInfo var title: String?,
              @ColumnInfo var ids: IDs?,
              @ColumnInfo var number_abs: String?,
              @ColumnInfo var overview: String?,
              @ColumnInfo var rating: String?,
              @ColumnInfo var votes: String?,
              @ColumnInfo var comment_count: String?,
              @ColumnInfo var first_aired: String?,
              @ColumnInfo var updated_at: String?,
              @ColumnInfo @field:Transient var available_translations: String?,
              @ColumnInfo var runtime: String?)
