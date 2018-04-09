package test.itexico.movies.utils

object Trakt {

    private val traktURL = "https://private-18a5d-trakt.apiary-proxy.com"

    val clientID: String
        get() {
            return "ee910a122f9dbf4808cb68cd4e935401d6806b51b9cc911f22d1eeefe730ed31"
        }

    val seasonsURL: String
        get() = "$traktURL/shows/game-of-thrones/seasons?extended=full"

    fun getEpisodesURL(seasonId: String): String {
        return "$traktURL/shows/game-of-thrones/seasons/$seasonId?extended=full"
    }

    fun getImagesService(seasonId: String): String {
        return "https://api.themoviedb.org/3/tv/1399/season/$seasonId/images?api_key=eb1d66508c874fa4dd101a8883626046"
    }

    fun getImagesURL(path: String, size: String): String {
        return "https://image.tmdb.org/t/p/w$size$path"
    }


}
