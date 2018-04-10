package test.itexico.movies.model

class IDs(val trakt: Int =0,
          val tvdb: Int =0,
          val imdb: Int =0,
          val tmdb: Int =0,
          val tvrange: Int =0) {

    override fun toString(): String {
        super.toString()
        return """{"trakt":"$trakt", "tvdb":"$tvdb", "imdb":"$imdb", "tmdb":"$tmdb", "tvrange":"$tvrange"}"""
    }
}