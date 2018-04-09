package test.itexico.movies.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.activity_container.*
import test.itexico.movies.R

class SeasonsActivity : AppCompatActivity() {

    internal var container: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        container = main_container
        if (container != null) {
            if (savedInstanceState != null) {
                return
            }
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.main_container, SeasonsFragment())
            fragmentTransaction.commit()
        }
    }
}
