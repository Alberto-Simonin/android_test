package test.itexico.movies.model;

import com.android.volley.Response;

import org.json.JSONArray;

public interface SeasonsActivityModel {

    public void getData(Response.Listener<JSONArray> onResponse, Response.ErrorListener onError);
}
