package com.yb.uadnd.matchcentre.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.database.Comment
import java.lang.Exception

class CommentaryAdpater(private var mCommentary: ArrayList<Comment>, private var mContext: Context?):
        RecyclerView.Adapter<CommentaryAdpater.CommentaryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentaryViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.commentary_list_item, parent, false)
        return CommentaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentaryViewHolder, position: Int) {
        val entry: Comment = mCommentary.get(position)

        holder.time.text = entry.time
        var type: String? = entry.type
        if(entry.type.equals("start")) type = (entry.type + entry.period)
        val style: TypeStyle = getCommentaryTypeStyle(type)
        holder.type.text = style.text
        holder.type.background = style.drawable
        holder.comment.text = entry.comment
    }

    private fun getCommentaryTypeStyle(type: String?): TypeStyle {
        return when(type) {
            "end 14" -> TypeStyle("FINISH", mContext?.getDrawable(R.color.grey))
            "end 2" -> TypeStyle("END 2", mContext?.getDrawable(R.color.grey))
            "end 1" -> TypeStyle("END 1", mContext?.getDrawable(R.color.grey))
            "start2" -> TypeStyle("START 2", mContext?.getDrawable(R.color.grey))
            "start1" -> TypeStyle("START 1", mContext?.getDrawable(R.color.grey))
            "end delay" -> TypeStyle("RESUME", mContext?.getDrawable(R.color.grey))
            "start delay" -> TypeStyle("PAUSED", mContext?.getDrawable(R.color.grey))
            "miss" -> TypeStyle("MISS", mContext?.getDrawable(R.color.lime))
            "corner" -> TypeStyle("CORNER", mContext?.getDrawable(R.color.orange))
            "goal" -> TypeStyle("GOAL", mContext?.getDrawable(R.color.green))
            "attempt blocked" -> TypeStyle("BLOCK", mContext?.getDrawable(R.color.blue))
            "attempt saved" -> TypeStyle("SAVE", mContext?.getDrawable(R.color.indigo))
            "offside" -> TypeStyle("OFFSIDE", mContext?.getDrawable(R.color.black))
            "substitution" -> TypeStyle("SUB", mContext?.getDrawable(R.color.purple))
            "free kick lost" -> TypeStyle("FREEKICK", mContext?.getDrawable(R.color.pink))
            "free kick won" -> TypeStyle("FREEKICK", mContext?.getDrawable(R.color.brown))
            "yellow card" -> TypeStyle("YELLOW", mContext?.getDrawable(R.color.yellow))
            "red card" -> TypeStyle("RED", mContext?.getDrawable(R.color.red))
            "lineup" -> TypeStyle("LINEUP", mContext?.getDrawable(R.color.dark_green))
            else -> throw Exception("Unknown type: " + type)
        }
    }

    override fun getItemCount(): Int {
        return mCommentary.size
    }

    class CommentaryViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){
        var time: TextView
        var type: TextView
        var comment: TextView
        init {
            time = itemView.findViewById(R.id.time)
            type = itemView.findViewById(R.id.type)
            comment = itemView.findViewById(R.id.comment)
        }
    }

    class TypeStyle(var text: String, var drawable: Drawable?){
    }
}