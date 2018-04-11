package test.itexico.movies.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
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

class SeasonsListModel(application: Application): AndroidViewModel(application) {

    val observableSeasons: MediatorLiveData<ArrayList<Season>>

    init{
        observableSeasons = MediatorLiveData()
        var seasonsList = ArrayList<Season>()
        val context = application.applicationContext
        if(Network.isAvailable(context)) {
            val requestManager = RequestManager.getInstance(context)
            val url = Trakt.seasonsURL
            val request = StandardRequest(Request.Method.GET, url, null, Response.Listener { response ->
                val gson = Gson()
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
                observableSeasons.postValue(seasonsList)
                //onResponseCallback.onResponse(seasonsList)
            }, Response.ErrorListener { VolleyError(context.resources.getString(R.string.err_no_data_text)) })
            requestManager.addToRequestQueue(request)
        }else{
            seasonsList = AppDatabase.getInstance(context).seasonDAO().getAllSeasons() as ArrayList<Season>
            if(seasonsList.size>0) {
                //onResponseCallback.onResponse(seasonsList)
                observableSeasons.postValue(seasonsList)
            }else{
                //onError.onErrorResponse(VolleyError(context.resources.getString(R.string.err_no_data_text)))
            }
        }
    }

    fun getData(): MediatorLiveData<ArrayList<Season>> {
        return observableSeasons
    }


}
