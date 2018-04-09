package test.itexico.movies.model

import android.content.Context

import com.android.volley.Request
import com.android.volley.Response
import com.google.gson.Gson

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList

import test.itexico.movies.managers.RequestManager
import test.itexico.movies.managers.StandardRequest
import test.itexico.movies.utils.Trakt

class EpisodesListModel(private val context: Context) {

    fun getData(seasonId: Long, onResponseCallback: Response.Listener<ArrayList<Episode>>, onError: Response.ErrorListener) {
        val requestManager = RequestManager.getInstance(context)
        val url = Trakt.getEpisodesURL(seasonId.toString() + "")
        val request = StandardRequest(method = Request.Method.GET, url = url, successlistener = Response.Listener { response ->
            val gson = Gson()
            val episodesList = ArrayList<Episode>()
            for (i in 0 until response.length()) {
                try {
                    val obj = response.getJSONObject(i)
                    val episode = gson.fromJson(obj.toString(), Episode::class.java)
                    episode.ids = obj.getJSONObject("ids")
                    episodesList.add(episode)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
            onResponseCallback.onResponse(episodesList)
        }, errorListener = onError)
        requestManager.addToRequestQueue(request)
    }
}
