package com.yb.uadnd.matchcentre.ui.commentary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.data.local.Comment
import com.yb.uadnd.matchcentre.databinding.CommentaryListItemBinding

class CommentaryAdapter : ListAdapter<Comment, CommentaryAdapter.CommentaryViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentaryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CommentaryListItemBinding.inflate(layoutInflater, parent, false)
        return CommentaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentaryViewHolder, position: Int) = holder.bind(getItem(position))

    class CommentaryViewHolder(private val binding: CommentaryListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Comment) {
            with(binding) {
                time.text = entry.time
                var commentType: String? = entry.type
                if (entry.type == "start") commentType = (entry.type + entry.period)
                val style: Utils.TypeStyle = Utils.getCommentaryTypeStyle(commentType)
                type.text = style.text
                type.setBackgroundResource(style.colorRes)
                comment.text = entry.comment
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Comment>() {

            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem === newItem

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean = oldItem == newItem

        }

    }

}