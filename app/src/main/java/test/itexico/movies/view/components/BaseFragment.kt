package test.itexico.movies.view.components

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.itexico.movies.R

open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        val toolbar = activity.actionBar
        toolbar?.setCustomView(R.layout.toolbar_layout)
        return rootView
    }
}
