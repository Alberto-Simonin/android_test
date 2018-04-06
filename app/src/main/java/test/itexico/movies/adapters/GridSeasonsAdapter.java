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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.itexico.movies.R;
import test.itexico.movies.managers.RequestManager;
import test.itexico.movies.model.Season;
import test.itexico.movies.utils.Trakt;

public class GridSeasonsAdapter extends RecyclerView.Adapter<GridSeasonsAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Season> data;

    public GridSeasonsAdapter(Context context, ArrayList<Season> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public long getItemId(int i) {
        return data.get(i).getNumber();
    }

    public Season getItem(int i) {
        return data.get(i);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.seasons_list_item, parent, false);
        return new ViewHolder(viewLayout);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Season item = getItem(position);
        viewHolder.txtSeason.setText(context.getResources().getString(R.string.lbl_season)+ item.getNumber());
        viewHolder.txtEpisodes.setText(context.getResources().getString(R.string.lbl_episodes)+ item.getEpisode_count());
        viewHolder.txtRating.setText(context.getResources().getString(R.string.lbl_rating)+ item.getRating().substring(0,4));

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
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_cover) ImageView imgCover;
        @BindView(R.id.txt_season) TextView txtSeason;
        @BindView(R.id.txt_episodes) TextView txtEpisodes;
        @BindView(R.id.txt_rating) TextView txtRating;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
