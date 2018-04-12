package test.itexico.movies.view

import android.arch.lifecycle.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Response
import kotlinx.android.synthetic.main.seasons_fragment.view.*
import test.itexico.movies.R
import test.itexico.movies.model.SeasonsListModel
import test.itexico.movies.presenter.SeasonsListPresenter
import android.arch.lifecycle.Lifecycle.Event.*

class SeasonsFragment : Fragment(), LifecycleObserver {

    internal var gridSeasons: RecyclerView? = null
    lateinit var seasonsActivityPresenter: SeasonsListPresenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.seasons_fragment, container, false)
        gridSeasons = rootView?.grid_seasons
        gridSeasons?.layoutManager = GridLayoutManager(context, 2)
        seasonsActivityPresenter = SeasonsListPresenter(context, gridSeasons as RecyclerView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            val viewModel = ViewModelProviders.of(this,
                    SeasonsListModel.SeasonsListModelFactory(
                            this.activity.application,
                            Response.ErrorListener { error ->
                                seasonsActivityPresenter.onErrorResponse(error)
                            }))
                    .get(SeasonsListModel::class.java)
            viewModel.getData().observe(this, android.arch.lifecycle.Observer { results ->
                seasonsActivityPresenter.setData(results!!)
            })
        }
    }
}
