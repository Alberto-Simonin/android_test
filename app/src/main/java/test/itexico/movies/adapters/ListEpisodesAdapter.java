package test.itexico.movies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.itexico.movies.R;

public class ListEpisodesAdapter extends BaseAdapter {
    private Context context;
    private JSONArray data;

    public ListEpisodesAdapter(Context context, JSONArray data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return data.getJSONObject(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.episodes_list_item,parent,false);
            viewHolder.txtEpisodeNum = (TextView) convertView.findViewById(R.id.txt_episode_num);
            viewHolder.txtEpisodeName = (TextView) convertView.findViewById(R.id.txt_episode_name);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)convertView.getTag();
        }

        JSONObject item = (JSONObject) getItem(i);
        try {
            viewHolder.txtEpisodeNum.setText(context.getResources().getString(R.string.lbl_episode_num)+ item.getString("number"));
            viewHolder.txtEpisodeName.setText(item.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return convertView;
    }


    class ViewHolder{

        protected TextView txtEpisodeNum;
        protected TextView txtEpisodeName;

    }
}
