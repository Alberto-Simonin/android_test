package test.itexico.movies.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.itexico.movies.R;
import test.itexico.movies.presenter.SeasonsListPresenter;

public class SeasonsFragment extends Fragment {

    @BindView(R.id.grid_seasons) RecyclerView gridSeasons;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.seasons_fragment, container, false);
        ButterKnife.bind(this, rootView);
        gridSeasons.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SeasonsListPresenter seasonsActivityPresenter = new SeasonsListPresenter(getContext(), gridSeasons);
        seasonsActivityPresenter.populateSeasons();
        return rootView;
    }
}
