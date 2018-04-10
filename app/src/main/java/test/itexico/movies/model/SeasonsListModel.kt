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

class SeasonsListModel(private val context: Context) {

    fun getData(onResponseCallback: Response.Listener<ArrayList<Season>>, onError: Response.ErrorListener) {
        if(Network.isAvailable(context)) {
            val requestManager = RequestManager.getInstance(context)
            val url = Trakt.seasonsURL
            val request = StandardRequest(Request.Method.GET, url, null, Response.Listener { response ->
                val gson = Gson()
                val seasonsList = ArrayList<Season>()
                for (i in 0 until response.length()) {
                    try {
                        val obj = response.getJSONObject(i)
                        val season = gson.fromJson(obj.toString(), Season::class.java)
                        seasonsList.add(season)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                AppDatabase.getInstance(context).seasonDAO().insertAll(seasonsList)

                onResponseCallback.onResponse(seasonsList)
            }, onError)
            requestManager.addToRequestQueue(request)
        }else{
            val seasonsList = AppDatabase.getInstance(context).seasonDAO().getAllSeasons() as ArrayList<Season>
            if(seasonsList.size>0) {
                onResponseCallback.onResponse(seasonsList)
            }else{
                onError.onErrorResponse(VolleyError(context.resources.getString(R.string.err_no_data_text)))
            }
        }
    }


}
