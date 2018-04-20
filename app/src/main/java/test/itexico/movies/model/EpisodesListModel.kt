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


class EpisodesListModel(application: Application, seasonId: Int, errorListener: Response.ErrorListener) : AndroidViewModel(application){

    val observableEpisodes: MediatorLiveData<ArrayList<Episode>> = MediatorLiveData()
    val episodesList = ArrayList<Episode>()
    val mApplication: Application
    val seasonId: Int
    val errorListener: Response.ErrorListener

    init {
        this.mApplication = application
        this.seasonId = seasonId
        this.errorListener = errorListener
    }

    fun getStandardRequest(url: String): StandardRequest{
        return StandardRequest(method = Request.Method.GET, url = url, successlistener = Response.Listener { response ->
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

            AppDatabase.getInstance(mApplication.applicationContext).episodeDAO().insertAll(episodesList)
            getObservableEpisodesList().postValue(episodesList)
        }, errorListener = getErrorListenerReference())
    }

    fun getData():MediatorLiveData<ArrayList<Episode>> {
        if (networkIsAvailable()) {
            val url = Trakt.getEpisodesURL(seasonId.toString() + "")
            val request = getStandardRequest(url)
            getRequestManagerInstance().addToRequestQueue(request)
        }else{
            val episodesList = getEpisodesInfoFromDB()
            if(getEpisodesArrayList().size>0) {
                getObservableEpisodesList().postValue(getEpisodesArrayList())
            }else{
                getErrorListenerReference().onErrorResponse(VolleyError(getErrorMessage()))
            }

        }
        return getObservableEpisodesList()
    }

    fun getEpisodesArrayList(): ArrayList<Episode> {
        return episodesList
    }

    fun networkIsAvailable():Boolean{
        return Network.isAvailable(mApplication.applicationContext)
    }

    fun getRequestManagerInstance(): RequestManager{
        return RequestManager.getInstance(mApplication.applicationContext)
    }

    fun getErrorListenerReference(): Response.ErrorListener{
        return errorListener;
    }

    fun getObservableEpisodesList(): MediatorLiveData<ArrayList<Episode>> {
        return observableEpisodes
    }

    fun getEpisodesInfoFromDB(): ArrayList<Episode> {
        return AppDatabase.getInstance(mApplication.applicationContext).episodeDAO().getAllEpisodes(seasonId) as ArrayList<Episode>
    }

    fun getErrorMessage(): String{
        return mApplication.applicationContext.resources.getString(R.string.err_no_data_text)
    }

    class EpisodesListModelFactory(private val mApplication: Application, private val seasonId: Int, private val errorListener: Response.ErrorListener) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EpisodesListModel(mApplication, seasonId, errorListener) as T
        }
    }

}
