package com.yb.uadnd.matchcentre.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Commentary
import com.yb.uadnd.matchcentre.model.Commentary.Data.CommentaryEntry

class CommentaryAdpater(private var mCommentary: ArrayList<CommentaryEntry>):
        RecyclerView.Adapter<CommentaryAdpater.CommentaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentaryViewHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        var view: View = layoutInflater.inflate(R.layout.commentary_list_item, parent, false)
        return CommentaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentaryViewHolder, position: Int) {
        var entry: CommentaryEntry = mCommentary.get(position)
        holder.time.text = entry.time
//        holder.type.background =
        holder.type.text = entry.type
        holder.comment.text = entry.comment
    }

    override fun getItemCount(): Int {
        return mCommentary.size
    }

    class CommentaryViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var time: TextView
        lateinit var type: TextView
        lateinit var comment: TextView
        init {
            time = itemView.findViewById(R.id.time)
            type = itemView.findViewById(R.id.type)
            comment = itemView.findViewById(R.id.comment)
        }
    }
}