package test.itexico.movies.managers

import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import org.json.JSONArray
import test.itexico.movies.utils.Trakt
import java.util.*

class StandardRequest(method: Int,
                      url: String,
                      jsonRequest: JSONArray? = null,
                      successlistener: Response.Listener<JSONArray>,
                      errorListener: Response.ErrorListener) :
        JsonArrayRequest(method, url, jsonRequest, successlistener, errorListener) {

    override fun getHeaders(): Map<String, String> {
        val headers = HashMap<String, String>()
        headers["Content-Type"] = "application/json"
        headers["trackt-api-version"] = "2"
        headers["trackt-api-key"] = Trakt.clientID
        return headers
    }
}
