package test.itexico.movies.managers;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import test.itexico.movies.utils.Trakt;

public class StandardRequest extends JsonArrayRequest {

    public StandardRequest(int method, String url, JSONArray jsonRequest, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() {
        Map<String, String>  headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("trackt-api-version", "2");
        headers.put("trackt-api-key", Trakt.getClientID());
        return headers;
    }
}
