package test.itexico.movies.managers

import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import test.itexico.movies.utils.Trakt
import java.util.HashMap

class ImageRequest(method: Int,
                   url: String,
                   jsonRequest: JSONObject? = null,
                   listener: Response.Listener<JSONObject>,
                   errorListener: Response.ErrorListener)
    : JsonObjectRequest(method, url, jsonRequest, listener, errorListener) {

    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        headers["trackt-api-version"] = "2"
        headers["trackt-api-key"] = Trakt.clientID
        return headers
    }
}