package test.itexico.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.itexico.movies.R;
import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.utils.Trakt;

public class GridSeasonsAdapter extends RecyclerView.Adapter<GridSeasonsAdapter.ViewHolder> {

    private final Context context;
    private final JSONArray data;

    public GridSeasonsAdapter(Context context, JSONArray data){
        this.context = context;
        this.data = data;
    }

    @Override
    public long getItemId(int i) {
        try {
            return data.getJSONObject(i).getInt("number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public JSONObject getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return data.length();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.seasons_list_item, parent, false);
        return new ViewHolder(viewLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        try {
            JSONObject item = getItem(position);
            viewHolder.txtSeason.setText(context.getResources().getString(R.string.lbl_season)+ item.getString("number"));
            viewHolder.txtEpisodes.setText(context.getResources().getString(R.string.lbl_episodes)+ item.getString("episode_count"));
            viewHolder.txtRating.setText(context.getResources().getString(R.string.lbl_rating)+ item.getString("rating").substring(0,4));

            RequestManager requestManager = RequestManager.getInstance(context);
            String url = Trakt.getImagesService(position+"");
            final ViewHolder finalViewHolder = viewHolder;
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        String path = response.getJSONArray("posters").getJSONObject(0).getString("file_path");
                        String url = Trakt.getImagesURL(path, "500");
                        Picasso.get().load(url).into(finalViewHolder.imgCover);
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


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgCover;
        public TextView txtSeason;
        public TextView txtEpisodes;
        public TextView txtRating;

        public ViewHolder(View itemView) {
            super(itemView);
            txtSeason = itemView.findViewById(R.id.txt_season);
            txtEpisodes = itemView.findViewById(R.id.txt_episodes);
            txtRating = itemView.findViewById(R.id.txt_rating);
            imgCover = itemView.findViewById(R.id.img_cover);
        }
    }
}
