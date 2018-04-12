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

class SeasonsListModel(application: Application, errorListener: Response.ErrorListener): AndroidViewModel(application) {

    val observableSeasons: MediatorLiveData<ArrayList<Season>>
    val mApplication: Application
    val errorListener: Response.ErrorListener

    init{
        observableSeasons = MediatorLiveData()
        this.mApplication = application
        this.errorListener = errorListener
    }

    fun getData(): MediatorLiveData<ArrayList<Season>> {
        var seasonsList = ArrayList<Season>()
        val context = mApplication.applicationContext
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
            }, errorListener)
            requestManager.addToRequestQueue(request)
        }else{
            seasonsList = AppDatabase.getInstance(context).seasonDAO().getAllSeasons() as ArrayList<Season>
            if(seasonsList.size>0) {
                observableSeasons.postValue(seasonsList)
            }else{
                errorListener.onErrorResponse(VolleyError(context.resources.getString(R.string.err_no_data_text)))
            }
        }
        return observableSeasons
    }

    class SeasonsListModelFactory(private val mApplication: Application, private val errorListener: Response.ErrorListener) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SeasonsListModel(mApplication, errorListener) as T
        }
    }

}
