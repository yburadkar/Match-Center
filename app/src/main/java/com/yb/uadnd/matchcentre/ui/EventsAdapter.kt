package com.yb.uadnd.matchcentre.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.MyApp
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match.Data.Event

class EventsAdapter(var mEvents: ArrayList<Event> , var mContext: Context ):
        RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mEvents.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = mEvents.get(position)
        holder.time.text = event.time
        holder.team_logo.setImageDrawable(getTeamLogo(event.teamId?.substring(1)))
        val typeStyle = getEventTypeStyle(event.type)
        holder.type.text = typeStyle.text
        holder.type.background = typeStyle.drawable
        holder.event_notes.text = event.getEventText()
    }

    fun getTeamLogo(teamId: String?): Drawable? {
        mContext.applicationContext as MyApp
        return when(teamId) {
            "1" -> mContext.getDrawable(R.drawable.manunited)
            "13" -> mContext.getDrawable(R.drawable.leicestercity)
            else -> null
        }
    }

    private fun getEventTypeStyle(type: String?): TypeStyle {
        return when(type) {
            "Kick Off" -> TypeStyle("START 1", mContext.getDrawable(R.color.grey))
            "Half Time" -> TypeStyle("END 1", mContext.getDrawable(R.color.grey))
            "Second Half Start" -> TypeStyle("START 2", mContext.getDrawable(R.color.grey))
            "Full Time" -> TypeStyle("END 2", mContext.getDrawable(R.color.grey))
            "Goal" -> TypeStyle("GOAL", mContext.getDrawable(R.color.green))
            "Substitution" -> TypeStyle("SUB", mContext.getDrawable(R.color.purple))
            "Yellow Card" -> TypeStyle("YELLOW", mContext.getDrawable(R.color.yellow))
            "Red Card" -> TypeStyle("RED", mContext.getDrawable(R.color.red))
            else -> throw Exception("Unknown type: " + type)
        }
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var time: TextView
        var team_logo: ImageView
        var type: TextView
        var event_notes: TextView
        init {
            time = itemView.findViewById(R.id.time)
            team_logo = itemView.findViewById(R.id.team_logo)
            type = itemView.findViewById(R.id.type)
            event_notes = itemView.findViewById(R.id.event_notes)
        }
    }

    class TypeStyle(var text: String, var drawable: Drawable?){

    }
}