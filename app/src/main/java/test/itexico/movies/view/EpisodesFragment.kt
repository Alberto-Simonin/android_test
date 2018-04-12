package test.itexico.movies.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Response
import kotlinx.android.synthetic.main.episodes_fragment.view.*
import test.itexico.movies.R
import test.itexico.movies.model.EpisodesListModel
import test.itexico.movies.model.EpisodesListModel.*
import test.itexico.movies.presenter.EpisodesListPresenter

class EpisodesFragment : Fragment(), LifecycleObserver{

    internal var listEpisodes: RecyclerView? = null
    internal var headerLayout: ConstraintLayout? = null
    lateinit var episodesActivityPresenter: EpisodesListPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.episodes_fragment, container, false)
        val seasonId = arguments.getInt(resources.getString(R.string.key_seasonNum))

        listEpisodes = rootView?.list_episodes
        headerLayout = rootView?.header_layout

        listEpisodes?.layoutManager = LinearLayoutManager(context)

        episodesActivityPresenter = EpisodesListPresenter(context, headerLayout as ConstraintLayout, listEpisodes as RecyclerView)
        episodesActivityPresenter.setHeaderInfo(arguments)

        if (seasonId < 0) {
            DialogAlert.show(context,
                    resources.getString(R.string.err_no_data_title),
                    resources.getString(R.string.err_no_data_text),
                    DialogInterface.OnClickListener { _, _ -> activity.finish() })
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            val seasonId = arguments.getInt(resources.getString(R.string.key_seasonNum))
            val viewModel = ViewModelProviders.of(this,
                    EpisodesListModelFactory(
                            this.activity.application,
                            seasonId,
                            Response.ErrorListener { error ->
                                episodesActivityPresenter.onErrorResponse(error)
                            }))
                    .get(EpisodesListModel::class.java)
            viewModel.getData().observe(this, android.arch.lifecycle.Observer { results ->
                episodesActivityPresenter.setData(results!!)
            })
        }
    }
}
