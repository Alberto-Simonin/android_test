package test.itexico.movies.managers;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestManager {
    private static RequestManager instance;
    private final Context context;
    private RequestQueue mRequestQueue;

    private RequestManager(Context context){
        this.context = context;
        mRequestQueue = getRequestQueue();
    }

    public static RequestManager getInstance(Context context) {
        return (instance==null) ? new RequestManager(context) : instance;

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
