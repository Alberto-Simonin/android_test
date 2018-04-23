package test.itexico.movies.database

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import test.itexico.movies.model.objects.Episode
import test.itexico.movies.model.objects.IDs
import test.itexico.movies.model.objects.Season

class DataConverters {

    @TypeConverter
    fun fromIDs(ids: IDs): String {
        return ids.toString()
    }

    @TypeConverter
    fun toIDs(idsString: String): IDs {
        return Gson().fromJson(idsString, IDs::class.java)
    }

    @TypeConverter
    fun fromSeason(season: Season): String {
        return season.toString()
    }

    @TypeConverter
    fun toSeason(seasonString: String): Season {
        return Gson().fromJson(seasonString, Season::class.java)
    }

    @TypeConverter
    fun fromEpisode(episode: Episode): String {
        return episode.toString()
    }

    @TypeConverter
    fun toEpisode(episodeString: String): Episode {
        return Gson().fromJson(episodeString, Episode::class.java)
    }

}