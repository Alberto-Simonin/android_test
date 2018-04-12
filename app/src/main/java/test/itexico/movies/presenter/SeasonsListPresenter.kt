package test.itexico.movies.presenter

import android.app.Activity
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import com.android.volley.Response
import com.android.volley.VolleyError
import test.itexico.movies.R
import test.itexico.movies.adapters.GridSeasonsAdapter
import test.itexico.movies.model.Season
import test.itexico.movies.model.SeasonsListModel
import test.itexico.movies.view.DialogAlert
import test.itexico.movies.view.EpisodesActivity
import java.util.*


class SeasonsListPresenter(private val context: Context, private val view: RecyclerView): Response.ErrorListener {
    private var gridSeasonsAdapter: GridSeasonsAdapter? = null

    override fun onErrorResponse(error: VolleyError) {
        DialogAlert.show(context,
                context.resources.getString(R.string.err_auth_title),
                context.resources.getString(R.string.err_auth_text),
                DialogInterface.OnClickListener { _, _ -> (context as Activity).finish() })
    }

    fun setData(response: ArrayList<Season>) {
        gridSeasonsAdapter = GridSeasonsAdapter(context, response)
        view.adapter = gridSeasonsAdapter

        view.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            internal val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(motionEvent: MotionEvent): Boolean {
                    return true
                }
            })

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                val viewChild = view.findChildViewUnder(e.x, e.y)
                if (viewChild != null && gestureDetector.onTouchEvent(e)) {
                    val i = view.getChildAdapterPosition(viewChild)
                    val intent = Intent(context, EpisodesActivity::class.java)
                    intent.putExtra(context.resources.getString(R.string.key_sesionId), gridSeasonsAdapter?.getItemId(i).toString() + "")
                    intent.putExtra(context.resources.getString(R.string.key_seasonNum), gridSeasonsAdapter?.getItem(i)?.number)
                    intent.putExtra(context.resources.getString(R.string.key_seasonEpisodes), gridSeasonsAdapter?.getItem(i)?.episode_count)
                    intent.putExtra(context.resources.getString(R.string.key_seasonVotes), gridSeasonsAdapter?.getItem(i)?.votes)
                    intent.putExtra(context.resources.getString(R.string.key_seasonRating), gridSeasonsAdapter?.getItem(i)?.rating)
                    context.startActivity(intent)
                }
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

            }
        })
    }
}
