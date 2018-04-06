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

import test.itexico.movies.R;
import test.itexico.movies.presenter.EpisodesListPresenterImpl;

public class EpisodesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.episodes_fragment, container, false);

        Bundle extras = getArguments();
        String seasonId = extras.getString(getResources().getString(R.string.key_sesionId));

        RecyclerView listEpisodes = rootView.findViewById(R.id.list_episodes);
        ConstraintLayout headerLayout = rootView.findViewById(R.id.headerLayout);

        listEpisodes.setLayoutManager(new LinearLayoutManager(getContext()));

        EpisodesListPresenterImpl episodesActivityPresenter = new EpisodesListPresenterImpl(getContext(), headerLayout, listEpisodes);
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
