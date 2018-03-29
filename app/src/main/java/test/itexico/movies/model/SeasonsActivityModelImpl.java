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

public class SeasonsActivityModelImpl implements SeasonsActivityModel {

    private final Context context;

    public SeasonsActivityModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getData(Response.Listener<JSONArray> onResponse, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getSeasonsURL();
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
