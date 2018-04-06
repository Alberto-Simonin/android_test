package test.itexico.movies.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.itexico.movies.R;
import test.itexico.movies.presenter.EpisodesListPresenter;

public class EpisodesFragment extends Fragment {

    @BindView(R.id.list_episodes) RecyclerView listEpisodes;
    @BindView(R.id.headerLayout) ConstraintLayout headerLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.episodes_fragment, container, false);

        Bundle extras = getArguments();
        String seasonId = extras.getString(getResources().getString(R.string.key_sesionId));

        ButterKnife.bind(this, rootView);

        listEpisodes.setLayoutManager(new LinearLayoutManager(getContext()));

        EpisodesListPresenter episodesActivityPresenter = new EpisodesListPresenter(getContext(), headerLayout, listEpisodes);
        episodesActivityPresenter.setHeaderInfo(extras);

        if(Integer.valueOf(seasonId)<0){
            DialogAlert.show(getContext(),
                    getResources().getString(R.string.err_no_data_title),
                    getResources().getString(R.string.err_no_data_text),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            getActivity().finish();
                        }
            });
        }else {
            episodesActivityPresenter.populateEpisodesFromSeason(Integer.valueOf(seasonId));
        }

        return rootView;
    }
}
