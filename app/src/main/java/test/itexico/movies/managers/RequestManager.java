package test.itexico.movies.managers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    private static RequestManager instance;

    private RequestQueue mRequestQueue;

    private RequestManager(Context context){
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
    }

    public static RequestManager getInstance(Context context) {
        if(instance==null){
            instance = new RequestManager(context);
        }
        return instance;

    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
