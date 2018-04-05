package test.itexico.movies.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import test.itexico.movies.R;
import test.itexico.movies.presenter.EpisodesListPresenterImpl;

public class EpisodesActivity extends AppCompatActivity {

    private RecyclerView listEpisodes;
    private ConstraintLayout headerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String seasonId = extras.getString(getResources().getString(R.string.key_sesionId));
        setContentView(R.layout.episodes_activity);
        listEpisodes = findViewById(R.id.list_episodes);
        headerLayout = findViewById(R.id.headerLayout);
        listEpisodes.setLayoutManager(new LinearLayoutManager(this));

        EpisodesListPresenterImpl episodesActivityPresenter = new EpisodesListPresenterImpl(this, headerLayout, listEpisodes);
        episodesActivityPresenter.setHeaderInfo(extras);
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
        }
    }

}
