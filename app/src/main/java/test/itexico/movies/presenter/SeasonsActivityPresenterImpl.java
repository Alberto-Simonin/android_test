package test.itexico.movies.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.itexico.movies.R;
import test.itexico.movies.adapters.GridSeasonsAdapter;
import test.itexico.movies.model.SeasonsActivityModelImpl;
import test.itexico.movies.view.EpisodesActivity;

public class SeasonsActivityPresenterImpl implements SeasonsActivityPresenter, Response.Listener<JSONArray>, Response.ErrorListener {

    private final Context context;
    private final GridView view;
    private GridSeasonsAdapter gridSeasonsAdapter;

    public SeasonsActivityPresenterImpl(Context context, GridView view){
        this.context = context;
        this.view = view;
    }

    @Override
    public void populateSeasons() {
        SeasonsActivityModelImpl seasonsActivityModel = new SeasonsActivityModelImpl(this.context);
        seasonsActivityModel.getData(this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.d("Err ", error.toString());
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(context.getResources().getString(R.string.err_auth_title))
            .setMessage(context.getResources().getString(R.string.err_auth_text))
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    ((Activity)context).finish();
                }
            })
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show();
    }

    @Override
    public void onResponse(JSONArray response) {
        gridSeasonsAdapter = new GridSeasonsAdapter(context, response);
        view.setAdapter(gridSeasonsAdapter);
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(context, EpisodesActivity.class);
                intent.putExtra(context.getResources().getString(R.string.key_sesionId), gridSeasonsAdapter.getItemId(i)+"");
                try {
                    intent.putExtra(context.getResources().getString(R.string.key_seasonNum), ((JSONObject)gridSeasonsAdapter.getItem(i)).getString("number"));
                    intent.putExtra(context.getResources().getString(R.string.key_seasonEpisodes), ((JSONObject)gridSeasonsAdapter.getItem(i)).getString("episode_count"));
                    intent.putExtra(context.getResources().getString(R.string.key_seasonVotes), ((JSONObject)gridSeasonsAdapter.getItem(i)).getString("votes"));
                    intent.putExtra(context.getResources().getString(R.string.key_seasonRating), ((JSONObject)gridSeasonsAdapter.getItem(i)).getString("rating"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ((AppCompatActivity)context).startActivity(intent);
            }
        });
    }
}
