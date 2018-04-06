package test.itexico.movies.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.managers.StandardRequest;
import test.itexico.movies.utils.Trakt;

public class SeasonsListModel {

    private final Context context;

    public SeasonsListModel(Context context) {
        this.context = context;
    }

    public void getData(final Response.Listener<ArrayList<Season>> onResponseCallback, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getSeasonsURL();
        StandardRequest request = new StandardRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                ArrayList<Season> seasonsList = new ArrayList<>();
                for (int i=0; i<response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Season season = gson.fromJson(obj.toString(), Season.class);
                        season.setIds(obj.getJSONObject("ids"));
                        seasonsList.add(season);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                onResponseCallback.onResponse(seasonsList);
            }
        }, onError);
        requestManager.addToRequestQueue(request);

    }


}
