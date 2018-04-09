package test.itexico.movies.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.seasons_fragment.view.*
import test.itexico.movies.R
import test.itexico.movies.presenter.SeasonsListPresenter

class SeasonsFragment : Fragment() {

    internal var gridSeasons: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater?.inflate(R.layout.seasons_fragment, container, false)
        gridSeasons = rootView?.grid_seasons
        gridSeasons?.layoutManager = GridLayoutManager(context, 2)
        val seasonsActivityPresenter = SeasonsListPresenter(context, gridSeasons as RecyclerView)
        seasonsActivityPresenter.populateSeasons()
        return rootView
    }
}
