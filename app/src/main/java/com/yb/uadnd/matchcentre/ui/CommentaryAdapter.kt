package com.yb.uadnd.matchcentre.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.model.database.Comment
import kotlinx.android.synthetic.main.commentary_list_item.view.*

class CommentaryAdapter(private val commentary: MutableList<Comment> = mutableListOf()):
        RecyclerView.Adapter<CommentaryAdapter.CommentaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.commentary_list_item, parent, false)
        return CommentaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentaryViewHolder, position: Int) = holder.bind(commentary[position])

    override fun getItemCount(): Int = commentary.size

    fun updateList(commentList: List<Comment>) {
        commentary.clear()
        commentary.addAll(commentList)
        notifyDataSetChanged()
    }

    class CommentaryViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(entry: Comment) {
            with(itemView) {
                time.text = entry.time
                var commentType: String? = entry.type
                if(entry.type == "start") commentType = (entry.type + entry.period)
                val style: Utils.TypeStyle = Utils.getCommentaryTypeStyle(commentType)
                type.text = style.text
                type.setBackgroundResource(style.colorRes)
                comment.text = entry.comment
            }
        }
    }
}