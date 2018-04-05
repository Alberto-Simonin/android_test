package test.itexico.movies.model;

import com.android.volley.Response;

import org.json.JSONArray;

public interface EpisodesListModel {
    public void getData(long seasonId, Response.Listener<JSONArray> onResponse, Response.ErrorListener onError);
}
