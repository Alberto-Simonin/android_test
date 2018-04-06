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

public class EpisodesListModel {

    private final Context context;

    public EpisodesListModel(Context context) {
        this.context = context;
    }

    public void getData(long seasonId, final Response.Listener<ArrayList<Episode>> onResponseCallback, Response.ErrorListener onError) {
        RequestManager requestManager = RequestManager.getInstance(context);
        String url = Trakt.getEpisodesURL(seasonId+"");
        StandardRequest request = new StandardRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Gson gson = new Gson();
                ArrayList<Episode> episodesList = new ArrayList<>();
                for(int i=0; i<response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Episode episode = gson.fromJson(obj.toString(), Episode.class);
                        episode.setIds(obj.getJSONObject("ids"));
                        episodesList.add(episode);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                onResponseCallback.onResponse(episodesList);
            }
        }, onError);
        requestManager.addToRequestQueue(request);
    }
}
