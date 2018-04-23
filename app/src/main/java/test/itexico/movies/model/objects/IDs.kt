package test.itexico.movies.model.objects

class IDs(val trakt: String ="",
          val tvdb: String ="",
          val imdb: String ="",
          val tmdb: String ="",
          val tvrange: String ="") {

    override fun toString(): String {
        super.toString()
        return """{"trakt":"$trakt", "tvdb":"$tvdb", "imdb":"$imdb", "tmdb":"$tmdb", "tvrange":"$tvrange"}"""
    }
}