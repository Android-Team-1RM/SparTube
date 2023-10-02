import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.naebaecamteam1rm_spartube.R
import com.example.naebaecamteam1rm_spartube.playlistpage.PlayListModel

class PlaylistAdapter(private val context: Context, private val playlistItems: ArrayList<PlayListModel>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_playlist, parent, false)
        return PlaylistViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val item = playlistItems[position]
        holder.titleTextView.text = item.title
    }

    override fun getItemCount(): Int {
        return playlistItems.size
    }

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.fragment_playlist)
    }
}
