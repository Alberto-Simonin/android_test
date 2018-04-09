package test.itexico.movies.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.google.gson.Gson
import org.json.JSONException
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.managers.StandardRequest
import test.itexico.movies.utils.Trakt
import java.util.*

class SeasonsListModel(private val context: Context) {

    fun getData(onResponseCallback: Response.Listener<ArrayList<Season>>, onError: Response.ErrorListener) {
        val requestManager = RequestManager.getInstance(context)
        val url = Trakt.seasonsURL
        val request = StandardRequest(Request.Method.GET, url, null, Response.Listener { response ->
            val gson = Gson()
            val seasonsList = ArrayList<Season>()
            for (i in 0 until response.length()) {
                try {
                    val obj = response.getJSONObject(i)
                    val season = gson.fromJson(obj.toString(), Season::class.java)
                    season.ids = obj.getJSONObject("ids")
                    seasonsList.add(season)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
            onResponseCallback.onResponse(seasonsList)
        }, onError)
        requestManager.addToRequestQueue(request)

    }


}
