package test.itexico.movies.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.itexico.movies.R;
import test.itexico.movies.adapters.ListEpisodesAdapter;
import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.model.Episode;
import test.itexico.movies.model.EpisodesListModel;
import test.itexico.movies.utils.Trakt;
import test.itexico.movies.view.DialogAlert;

public class EpisodesListPresenter implements Response.Listener<ArrayList<Episode>>, Response.ErrorListener {

    private final ConstraintLayout header;
    private final Context context;
    private final RecyclerView recyclerView;
    @BindView(R.id.txt_season) TextView txtSeason;
    @BindView(R.id.txt_episodes) TextView txtEpisodes;
    @BindView(R.id.txt_rating) TextView txtRating;
    @BindView(R.id.txt_votes) TextView txtVotes;

    public EpisodesListPresenter(Context context, ConstraintLayout header, RecyclerView recyclerView){
        this.context = context;
        this.header = header;
        this.recyclerView = recyclerView;
    }

    public void setHeaderInfo(Bundle extras){
        ButterKnife.bind(this, header);

        txtSeason.setText(context.getResources().getString(R.string.lbl_season)+extras.getInt(context.getResources().getString(R.string.key_seasonNum)));
        txtEpisodes.setText(context.getResources().getString(R.string.lbl_episodes)+extras.getString(context.getResources().getString(R.string.key_seasonEpisodes)));
        txtRating.setText(extras.getString(context.getResources().getString(R.string.key_seasonRating)).substring(0,4));
        txtVotes.setText(context.getResources().getString(R.string.lbl_votes)+extras.getString(context.getResources().getString(R.string.key_seasonVotes)));
    }

    public void populateEpisodesFromSeason(int seasonId) {
        EpisodesListModel episodesActivityModel = new EpisodesListModel(this.context);
        episodesActivityModel.getData(seasonId,this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        DialogAlert.show(context,
            context.getResources().getString(R.string.err_auth_title),
            context.getResources().getString(R.string.err_auth_text),
            new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                });
    }

    @Override
    public void onResponse(ArrayList<Episode> response) {
        ListEpisodesAdapter listEpisodesAdapter = new ListEpisodesAdapter(context, response);
        recyclerView.setAdapter(listEpisodesAdapter);

        RequestManager requestManager = RequestManager.getInstance(context);
        String i = response.get(0).getSeason()+"";
        String url = Trakt.getImagesService(i);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String path = response.getJSONArray("posters").getJSONObject(1).getString("file_path");
                    String url = Trakt.getImagesURL(path, "200");
                    Picasso.get().load(url).into((ImageView) header.findViewById(R.id.img_cover));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Err ", error.toString());
            }
        });

        JsonObjectRequest requestPoster = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String path = response.getJSONArray("posters").getJSONObject(1).getString("file_path");
                    String url = Trakt.getImagesURL(path, "300");
                    Picasso.get().load(url).into((ImageView) header.findViewById(R.id.img_poster));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Err ", error.toString());
            }
        });


        requestManager.addToRequestQueue(request);
        requestManager.addToRequestQueue(requestPoster);
    }
}
