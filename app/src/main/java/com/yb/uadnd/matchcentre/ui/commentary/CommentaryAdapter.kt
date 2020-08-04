package com.yb.uadnd.matchcentre.ui.commentary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.databinding.CommentaryListItemBinding
import com.yb.uadnd.matchcentre.model.database.Comment

class CommentaryAdapter(private val commentary: MutableList<Comment> = mutableListOf()):
        RecyclerView.Adapter<CommentaryAdapter.CommentaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CommentaryListItemBinding.inflate(layoutInflater, parent, false)
        return CommentaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentaryViewHolder, position: Int) = holder.bind(commentary[position])

    override fun getItemCount(): Int = commentary.size

    fun updateList(commentList: List<Comment>) {
        commentary.clear()
        commentary.addAll(commentList)
        notifyDataSetChanged()
    }

    class CommentaryViewHolder( private val binding: CommentaryListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(entry: Comment) {
            with(binding) {
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