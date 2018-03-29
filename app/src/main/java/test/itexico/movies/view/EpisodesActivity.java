package test.itexico.movies.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import test.itexico.movies.R;
import test.itexico.movies.presenter.EpisodesActivityPresenterImpl;

public class EpisodesActivity extends AppCompatActivity {

    private ImageView img_poster;
    private ImageView img_cover;
    private TextView txtSeason;
    private TextView txtEpisodes;
    private TextView txtRating;
    private ListView listEpisodes;
    private TextView txtVotes;

    private int lastTopValueAssigned = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String seasonId = extras.getString(getResources().getString(R.string.key_sesionId));
        //Log.d("seasonId", seasonId);
        setContentView(R.layout.episodes_activity);
        listEpisodes = (ListView)findViewById(R.id.list_episodes);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.episodes_list_header, listEpisodes, false);
        listEpisodes.addHeaderView(header, null, false);

        img_poster = (ImageView)findViewById(R.id.img_poster);
        img_cover = (ImageView)findViewById(R.id.img_cover);
        txtSeason = (TextView) findViewById(R.id.txt_season);
        txtEpisodes = (TextView) findViewById(R.id.txt_episodes);
        txtRating = (TextView) findViewById(R.id.txt_rating);
        txtVotes = (TextView) findViewById(R.id.txt_votes);

        txtSeason.setText(getResources().getString(R.string.lbl_season)+extras.getString(getResources().getString(R.string.key_seasonNum)));
        txtEpisodes.setText(getResources().getString(R.string.lbl_episodes)+extras.getString(getResources().getString(R.string.key_seasonEpisodes)));
        txtRating.setText(extras.getString(getResources().getString(R.string.key_seasonRating)).substring(0,4));
        txtVotes.setText(getResources().getString(R.string.lbl_votes)+extras.getString(getResources().getString(R.string.key_seasonVotes)));

        EpisodesActivityPresenterImpl episodesActivityPresenter = new EpisodesActivityPresenterImpl(this, listEpisodes);
        if(Integer.valueOf(seasonId)<0){
            AlertDialog.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
            } else {
                builder = new AlertDialog.Builder(this);
            }
            builder.setTitle(getResources().getString(R.string.err_no_data_title))
                    .setMessage(getResources().getString(R.string.err_no_data_text))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else {
            episodesActivityPresenter.populateEpisodesFromSeason(Integer.valueOf(seasonId));

            listEpisodes.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView absListView, int i) {

                }

                @Override
                public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                    Rect rect = new Rect();
                    img_poster.getLocalVisibleRect(rect);
                    if (lastTopValueAssigned != rect.top) {
                        lastTopValueAssigned = rect.top;
                        img_poster.setY((float) (rect.top / 2.0));
                    }
                }
            });
        }
    }

}
