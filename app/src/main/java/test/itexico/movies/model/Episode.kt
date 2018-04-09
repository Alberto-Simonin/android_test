package test.itexico.movies.model

import org.json.JSONObject

class Episode(var season: Int,
              var number: Int,
              var title: String?,
              var ids: JSONObject?,
              var number_abs: String?,
              var overview: String?,
              var rating: String?,
              var votes: String?,
              var comment_count: String?,
              var first_aired: String?,
              var updated_at: String?,
              @field:Transient var available_translations:
              String?, var runtime: String?)
