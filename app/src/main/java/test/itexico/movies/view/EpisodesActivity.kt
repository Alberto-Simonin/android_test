package test.itexico.movies.view

import android.arch.lifecycle.LifecycleOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_container.*
import test.itexico.movies.R

class EpisodesActivity : AppCompatActivity(), LifecycleOwner {

    internal var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        container = main_container
        val extras = intent.extras
        if (container != null) {
            if (savedInstanceState != null) {
                return
            }
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val episodesFragment = EpisodesFragment()
            episodesFragment.arguments = extras
            fragmentTransaction.add(R.id.main_container, episodesFragment)
            fragmentTransaction.commit()
            lifecycle.addObserver(episodesFragment)
        }
    }

}
