package test.itexico.movies.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import test.itexico.movies.R;
import test.itexico.movies.presenter.SeasonsListPresenterImpl;

public class SeasonsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.seasons_fragment, container, false);
        RecyclerView gridSeasons = (RecyclerView) rootView.findViewById(R.id.grid_seasons);
        gridSeasons.setLayoutManager(new GridLayoutManager(getContext(), 2));
        SeasonsListPresenterImpl seasonsActivityPresenter = new SeasonsListPresenterImpl(getContext(), gridSeasons);
        seasonsActivityPresenter.populateSeasons();
        return rootView;
    }
}
