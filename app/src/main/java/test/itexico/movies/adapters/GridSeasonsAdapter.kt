package test.itexico.movies.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.seasons_list_item.view.*
import org.json.JSONException
import test.itexico.movies.R
import test.itexico.movies.managers.RequestManager
import test.itexico.movies.model.objects.Season
import test.itexico.movies.utils.Trakt
import java.util.*

class GridSeasonsAdapter(private val context: Context, private val data: ArrayList<Season>) : RecyclerView.Adapter<GridSeasonsAdapter.ViewHolder>() {

    override fun getItemId(i: Int): Long {
        return data[i].number.toLong()
    }

    fun getItem(i: Int): Season {
        return data[i]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.seasons_list_item, parent, false)
        return ViewHolder(viewLayout)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.txtSeason?.text = "${context.resources.getString(R.string.lbl_season)} ${item.number}"
        viewHolder.txtEpisodes?.text = "${context.resources.getString(R.string.lbl_episodes)} ${item.episode_count}"
        viewHolder.txtRating?.text = "${context.resources.getString(R.string.lbl_rating)} ${item.rating?.substring(0, 4)}"

        val requestManager = RequestManager.getInstance(context)
        val url = Trakt.getImagesService(position.toString() + "")

        val request = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
            try {
                val path = response.getJSONArray("posters").getJSONObject(0).getString("file_path")
                val url = Trakt.getImagesURL(path, "500")
                Picasso.get().load(url).into(viewHolder.imgCover)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> Log.d("Err ", error.toString()) })
        requestManager.addToRequestQueue(request)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCover: ImageView? = null
        var txtSeason: TextView? = null
        var txtEpisodes: TextView? = null
        var txtRating: TextView? = null

        init {
            imgCover = itemView.img_cover
            txtSeason = itemView.txt_season
            txtEpisodes = itemView.txt_episodes
            txtRating = itemView.txt_rating
        }
    }
}
