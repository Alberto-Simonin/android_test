package test.itexico.movies.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;

import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.managers.StandardRequest;
import test.itexico.movies.utils.Trakt;

public class EpisodesListModelImpl {

    private final Context context;

    public EpisodesListModelImpl(Context context) {
        this.context = context;
    }

    public void getData(long seasonId, Response.Listener<JSONArray> onResponse, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getEpisodesURL(seasonId+"");
        StandardRequest request = new StandardRequest(Request.Method.GET, url, null, onResponse, onError);
        requestManager.addToRequestQueue(request);
    }
}
