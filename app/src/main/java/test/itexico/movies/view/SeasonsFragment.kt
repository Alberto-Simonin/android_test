package test.itexico.movies.view

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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
import test.itexico.movies.view.components.BaseFragment

class SeasonsFragment : BaseFragment(), LifecycleObserver {

    internal var gridSeasons: RecyclerView? = null
    lateinit var seasonsActivityPresenter: SeasonsListPresenter
    lateinit var viewModel: SeasonsListModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.seasons_fragment, container, false)
        gridSeasons = rootView?.grid_seasons
        gridSeasons?.layoutManager = GridLayoutManager(context, 2)
        seasonsActivityPresenter = SeasonsListPresenter(context, gridSeasons as RecyclerView)
        viewModel = ViewModelProviders.of(this,
                SeasonsListModel.SeasonsListModelFactory(
                        this.activity.application,
                        Response.ErrorListener { error ->
                            seasonsActivityPresenter.onErrorResponse(error)
                        }))
                .get(SeasonsListModel::class.java)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
            viewModel.getData().observe(this, android.arch.lifecycle.Observer { results ->
                seasonsActivityPresenter.setData(results!!)
            })
        }
    }
}
