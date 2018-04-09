package test.itexico.movies.managers

import android.content.Context

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class RequestManager private constructor(context: Context) {

    var requestQueue: RequestQueue? = null
        private set

    init {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.applicationContext)
        }
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue?.add(req)
    }

    companion object {
        private var instance: RequestManager? = null

        fun getInstance(context: Context): RequestManager {
            if (instance == null) {
                instance = RequestManager(context)
            }
            return instance as RequestManager

        }
    }
}
