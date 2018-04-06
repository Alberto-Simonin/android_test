package test.itexico.movies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import test.itexico.movies.R;
import test.itexico.movies.model.Episode;

public class ListEpisodesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Episode> data;

    public ListEpisodesAdapter(Context context, ArrayList<Episode> data) {
        this.context = context;
        this.data = data;
    }

    public Episode getItem(int i) {
        return data.get(i);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.episodes_list_item, parent, false);
        return new ViewHolder(viewLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Episode item = getItem(position);
        ((ViewHolder)holder).txtEpisodeNum.setText(context.getResources().getString(R.string.lbl_episode_num)+ item.getNumber());
        ((ViewHolder)holder).txtEpisodeName.setText(item.getTitle());
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getNumber();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        protected TextView txtEpisodeNum;
        protected TextView txtEpisodeName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtEpisodeNum = itemView.findViewById(R.id.txt_episode_num);
            txtEpisodeName = itemView.findViewById(R.id.txt_episode_name);
        }
    }
}
