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

import test.itexico.movies.R;

public class ListEpisodesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private JSONArray data;

    public ListEpisodesAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    public Object getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.episodes_list_item, parent, false);
        return new ViewHolder(viewLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        try {
            JSONObject item = (JSONObject) getItem(position);
            ((ViewHolder)holder).txtEpisodeNum.setText(context.getResources().getString(R.string.lbl_episode_num)+ item.getString("number"));
            ((ViewHolder)holder).txtEpisodeName.setText(item.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int i) {
        try {
            return ((JSONObject)getItem(i)).getInt("number");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return data.length();
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
