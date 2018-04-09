package test.itexico.movies.view

import android.content.DialogInterface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.episodes_fragment.view.*
import test.itexico.movies.R
import test.itexico.movies.presenter.EpisodesListPresenter

class EpisodesFragment : Fragment() {

    internal var listEpisodes: RecyclerView? = null
    internal var headerLayout: ConstraintLayout? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.episodes_fragment, container, false)
        val extras = arguments
        val seasonId = extras.getString(resources.getString(R.string.key_sesionId))

        listEpisodes = rootView?.list_episodes
        headerLayout = rootView?.header_layout

        listEpisodes?.layoutManager = LinearLayoutManager(context)

        val episodesActivityPresenter = EpisodesListPresenter(context, headerLayout as ConstraintLayout, listEpisodes as RecyclerView)
        episodesActivityPresenter.setHeaderInfo(extras)

        if (Integer.valueOf(seasonId) < 0) {
            DialogAlert.show(context,
                    resources.getString(R.string.err_no_data_title),
                    resources.getString(R.string.err_no_data_text),
                    DialogInterface.OnClickListener { _, _ -> activity.finish() })
        } else {
            episodesActivityPresenter.populateEpisodesFromSeason(Integer.valueOf(seasonId))
        }

        return rootView
    }
}
