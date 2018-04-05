package test.itexico.movies.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import test.itexico.movies.R;
import test.itexico.movies.presenter.SeasonsListPresenterImpl;

public class SeasonsActivity extends AppCompatActivity {

    RecyclerView gridSeasons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seasons_activity);
        gridSeasons = (RecyclerView)findViewById(R.id.grid_seasons);
        gridSeasons.setLayoutManager(new GridLayoutManager(this, 2));
        SeasonsListPresenterImpl seasonsActivityPresenter = new SeasonsListPresenterImpl(this, gridSeasons);
        seasonsActivityPresenter.populateSeasons();
    }
}
