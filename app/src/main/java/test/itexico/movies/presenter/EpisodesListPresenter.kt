package test.itexico.movies.presenter

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.episodes_fragment.view.*
import org.json.JSONException
import test.itexico.movies.R
import test.itexico.movies.adapters.ListEpisodesAdapter
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.model.Episode
import test.itexico.movies.utils.Trakt
import test.itexico.movies.view.DialogAlert
import java.util.*

class EpisodesListPresenter(private val context: Context, private val header: ConstraintLayout, private val recyclerView: RecyclerView) : Response.ErrorListener{
    internal var txtSeason: TextView? = null
    internal var txtEpisodes: TextView? = null
    internal var txtRating: TextView? = null
    internal var txtVotes: TextView? = null

    fun setHeaderInfo(extras: Bundle) {
        txtSeason = header.txt_season
        txtEpisodes = header.txt_episodes
        txtRating = header.txt_rating
        txtVotes = header.txt_votes

        txtSeason?.text = "${context.resources.getString(R.string.lbl_season)} ${extras.getInt(context.resources.getString(R.string.key_seasonNum))}"
        txtEpisodes?.text = "${context.resources.getString(R.string.lbl_episodes)} ${extras.getString(context.resources.getString(R.string.key_seasonEpisodes))}"
        txtRating?.text = "${extras.getString(context.resources.getString(R.string.key_seasonRating))?.substring(0, 4)}"
        txtVotes?.text = "${context.resources.getString(R.string.lbl_votes)} ${extras.getString(context.resources.getString(R.string.key_seasonVotes))}"
    }

    override fun onErrorResponse(error: VolleyError) {
        DialogAlert.show(context,
                context.resources.getString(R.string.err_auth_title),
                context.resources.getString(R.string.err_auth_text),
                DialogInterface.OnClickListener { _, _ -> (context as Activity).finish() })
    }

    fun setData(response: ArrayList<Episode>) {
        val listEpisodesAdapter = ListEpisodesAdapter(context, response)
        recyclerView.adapter = listEpisodesAdapter

        val requestManager = RequestManager.getInstance(context)
        val i = response[0].season.toString() + ""
        val url = Trakt.getImagesService(i)
        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            try {
                val path = response.getJSONArray("posters").getJSONObject(1).getString("file_path")
                val url = Trakt.getImagesURL(path, "200")
                Picasso.get().load(url).into(header.img_cover as ImageView)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> Log.d("Err ", error.toString()) })

        val requestPoster = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            try {
                val path = response.getJSONArray("posters").getJSONObject(1).getString("file_path")
                val url = Trakt.getImagesURL(path, "300")
                Picasso.get().load(url).into(header.img_poster as ImageView)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> Log.d("Err ", error.toString()) })


        requestManager.addToRequestQueue(request)
        requestManager.addToRequestQueue(requestPoster)
    }
}
