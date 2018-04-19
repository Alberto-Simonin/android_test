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
import test.itexico.movies.R
import test.itexico.movies.database.AppDatabase
import test.itexico.movies.database.DAO.SeasonDAO
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.managers.StandardRequest
import test.itexico.movies.utils.Network
import test.itexico.movies.utils.Trakt

class SeasonsListModel(application: Application, errorListener: Response.ErrorListener): AndroidViewModel(application) {

    var observableSeasons = MediatorLiveData<ArrayList<Season>>()
    var mApplication: Application
    var errorListener: Response.ErrorListener
    var seasonsList = ArrayList<Season>()

    init{
        //observableSeasons = MediatorLiveData()
        this.mApplication = application
        this.errorListener = errorListener
    }

    fun networkIsAvailable():Boolean{
        return Network.isAvailable(mApplication.applicationContext)
    }

    fun getRequestManagerInstance():RequestManager{
        return RequestManager.getInstance(mApplication.applicationContext)
    }

    fun getStandardRequest(url: String):StandardRequest{
        return StandardRequest(Request.Method.GET, url, null, Response.Listener { response ->
            val gson = Gson()
            for (i in 0 until response.length()) {
                try {
                    val obj = response.getJSONObject(i)
                    val season = gson.fromJson(obj.toString(), Season::class.java)
                    seasonsList.add(season)
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            AppDatabase.getInstance(mApplication.applicationContext).seasonDAO().insertAll(seasonsList)
            getObservableSeasonsList().postValue(seasonsList)
        }, getErrorListenerReference())
    }

    fun getSeasonDAO():SeasonDAO{
        return AppDatabase.getInstance(mApplication.applicationContext).seasonDAO()
    }

    fun getSeasonsArayList():ArrayList<Season>{
        return seasonsList
    }

    fun getSeasonsInfoFromDB(): ArrayList<Season> {
        return getSeasonDAO().getAllSeasons().value as ArrayList<Season>
    }

    fun getObservableSeasonsList(): MediatorLiveData<ArrayList<Season>> {
        return observableSeasons
    }

    fun getErrorListenerReference(): Response.ErrorListener{
        return errorListener
    }
    
    fun getData(): MediatorLiveData<ArrayList<Season>> {
        if(networkIsAvailable()) {
            val url = Trakt.seasonsURL
            val request = getStandardRequest(url)
            getRequestManagerInstance().addToRequestQueue(request)
        }else{
            seasonsList = getSeasonsInfoFromDB()
            if(getSeasonsArayList().size > 0) {
                getObservableSeasonsList().postValue(getSeasonsArayList())
            }else{
                getErrorListenerReference().onErrorResponse(VolleyError(getErrorMessage()))
            }
        }
        return getObservableSeasonsList()
    }

    fun getErrorMessage(): String{
        return mApplication.applicationContext.resources.getString(R.string.err_no_data_text)
    }

    class SeasonsListModelFactory(private val mApplication: Application, private val errorListener: Response.ErrorListener) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SeasonsListModel(mApplication, errorListener) as T
        }
    }

}
