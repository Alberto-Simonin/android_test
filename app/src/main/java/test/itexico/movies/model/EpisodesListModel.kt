package test.itexico.movies.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
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


class EpisodesListModel(application: Application, seasonId: Int) : AndroidViewModel(application){

    val observableEpisodes: MediatorLiveData<ArrayList<Episode>>
    //var seasonId: Int = -1

    init {
        observableEpisodes = MediatorLiveData()
        var episodesList = ArrayList<Episode>()
        val context = application.applicationContext
        if (Network.isAvailable(context)) {
            val requestManager = RequestManager.getInstance(context)
            val url = Trakt.getEpisodesURL(seasonId.toString() + "")
            val request = StandardRequest(method = Request.Method.GET, url = url, successlistener = Response.Listener { response ->
                val gson = Gson()
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
                observableEpisodes.postValue(episodesList)
            }, errorListener = Response.ErrorListener { VolleyError(context.resources.getString(R.string.err_no_data_text)) })
            requestManager.addToRequestQueue(request)
        }else{
            val episodesList = AppDatabase.getInstance(context).episodeDAO().getAllEpisodes(seasonId) as ArrayList<Episode>
            if(episodesList.size>0) {
                observableEpisodes.postValue(episodesList)
            }
        }
    }

    fun getData():MediatorLiveData<ArrayList<Episode>> {
        return observableEpisodes
    }
}

class EpisodesListModelFactory(private val mApplication: Application, private val seasonId: Int) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesListModel(mApplication, seasonId) as T
    }
}