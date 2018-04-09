package test.itexico.movies.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.ArrayList

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.episodes_list_item.view.*
import test.itexico.movies.R
import test.itexico.movies.model.Episode

class ListEpisodesAdapter(private val context: Context, private val data: ArrayList<Episode>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun getItem(i: Int): Episode {
        return data[i]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context).inflate(R.layout.episodes_list_item, parent, false)
        return ViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ViewHolder).txtEpisodeNum?.text = "${context.resources.getString(R.string.lbl_episode_num)} ${item.number}"
        holder.txtEpisodeName?.text = item.title
    }

    override fun getItemId(i: Int): Long {
        return getItem(i).number.toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtEpisodeNum: TextView? = null
        var txtEpisodeName: TextView? = null

        init {
            txtEpisodeNum = itemView.txt_episode_num
            txtEpisodeName = itemView.txt_episode_name
        }
    }
}
