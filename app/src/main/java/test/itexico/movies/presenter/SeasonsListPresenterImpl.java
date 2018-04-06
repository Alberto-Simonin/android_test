package test.itexico.movies.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import test.itexico.movies.R;
import test.itexico.movies.adapters.GridSeasonsAdapter;
import test.itexico.movies.model.Season;
import test.itexico.movies.model.SeasonsListModelImpl;
import test.itexico.movies.view.DialogAlert;
import test.itexico.movies.view.EpisodesActivity;


public class SeasonsListPresenterImpl implements Response.Listener<ArrayList<Season>>, Response.ErrorListener {

    private final Context context;
    private final RecyclerView view;
    private GridSeasonsAdapter gridSeasonsAdapter;

    public SeasonsListPresenterImpl(Context context, RecyclerView view){
        this.context = context;
        this.view = view;
    }

    public void populateSeasons() {
        SeasonsListModelImpl seasonsActivityModel = new SeasonsListModelImpl(this.context);
        seasonsActivityModel.getData(this, this);
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
    public void onResponse(ArrayList<Season> response) {
        gridSeasonsAdapter = new GridSeasonsAdapter(context, response);
        view.setAdapter(gridSeasonsAdapter);

        view.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {
                    return true;
                }
            });

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View viewChild = view.findChildViewUnder(e.getX(), e.getY());
                if(viewChild != null && gestureDetector.onTouchEvent(e)) {
                    int i  = view.getChildAdapterPosition(viewChild);
                    Intent intent = new Intent(context, EpisodesActivity.class);
                    intent.putExtra(context.getResources().getString(R.string.key_sesionId), gridSeasonsAdapter.getItemId(i)+"");
                    intent.putExtra(context.getResources().getString(R.string.key_seasonNum), gridSeasonsAdapter.getItem(i).getNumber());
                    intent.putExtra(context.getResources().getString(R.string.key_seasonEpisodes), gridSeasonsAdapter.getItem(i).getEpisode_count());
                    intent.putExtra(context.getResources().getString(R.string.key_seasonVotes), gridSeasonsAdapter.getItem(i).getVotes());
                    intent.putExtra(context.getResources().getString(R.string.key_seasonRating), gridSeasonsAdapter.getItem(i).getRating());
                    context.startActivity(intent);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
