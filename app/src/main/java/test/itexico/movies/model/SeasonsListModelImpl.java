package test.itexico.movies.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;

import org.json.JSONArray;

import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.managers.StandardRequest;
import test.itexico.movies.utils.Trakt;

public class SeasonsListModelImpl implements SeasonsListModel {

    private final Context context;

    public SeasonsListModelImpl(Context context) {
        this.context = context;
    }

    @Override
    public void getData(Response.Listener<JSONArray> onResponse, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getSeasonsURL();
        StandardRequest request = new StandardRequest(Request.Method.GET, url, null, onResponse, onError);
        requestManager.addToRequestQueue(request);
    }


}
