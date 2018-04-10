package test.itexico.movies.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.google.gson.Gson
import org.json.JSONException
import test.itexico.movies.R
import test.itexico.movies.database.AppDatabase
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.managers.StandardRequest
import test.itexico.movies.utils.Network
import test.itexico.movies.utils.Trakt
import java.util.*

class EpisodesListModel(private val context: Context) {

    fun getData(seasonId: Int, onResponseCallback: Response.Listener<ArrayList<Episode>>, onError: Response.ErrorListener) {
        if (Network.isAvailable(context)) {
            val requestManager = RequestManager.getInstance(context)
            val url = Trakt.getEpisodesURL(seasonId.toString() + "")
            val request = StandardRequest(method = Request.Method.GET, url = url, successlistener = Response.Listener { response ->
                val gson = Gson()
                val episodesList = ArrayList<Episode>()
                for (i in 0 until response.length()) {
                    try {
                        val obj = response.getJSONObject(i)
                        val episode = gson.fromJson(obj.toString(), Episode::class.java)
                        episodesList.add(episode)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                AppDatabase.getInstance(context).episodeDAO().insertAll(episodesList)

                onResponseCallback.onResponse(episodesList)
            }, errorListener = onError)
            requestManager.addToRequestQueue(request)
        }else{
            val episodesList = AppDatabase.getInstance(context).episodeDAO().getAllEpisodes(seasonId) as ArrayList<Episode>
            if(episodesList.size>0) {
                onResponseCallback.onResponse(episodesList)
            }else{
                onError.onErrorResponse(VolleyError(context.resources.getString(R.string.err_no_data_text)))
            }
        }
    }
}
