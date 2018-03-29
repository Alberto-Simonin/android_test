package test.itexico.movies.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import test.itexico.movies.R;
import test.itexico.movies.presenter.SeasonsActivityPresenterImpl;

public class SeasonsActivity extends AppCompatActivity {

    GridView gridSeasons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seasons_activity);
        gridSeasons = (GridView)findViewById(R.id.grid_seasons);
        SeasonsActivityPresenterImpl seasonsActivityPresenter = new SeasonsActivityPresenterImpl(this, gridSeasons);
        seasonsActivityPresenter.populateSeasons();
    }
}
