package test.itexico.movies.model;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.utils.Trakt;

public class EpisodesActivityModelImpl implements EpisodesActivityModel {

    private final Context context;

    public EpisodesActivityModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getData(long seasonId, Response.Listener<JSONArray> onResponse, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getEpisodesURL(seasonId+"");
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, onResponse, onError){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("trackt-api-version", "2");
                headers.put("trackt-api-key", Trakt.getClientID());
                return headers;
            }
        };

        requestManager.addToRequestQueue(request);
    }
}
