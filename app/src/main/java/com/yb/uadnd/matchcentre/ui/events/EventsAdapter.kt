package com.yb.uadnd.matchcentre.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.model.Match.Data.Event
import kotlinx.android.synthetic.main.event_list_item.view.*

class EventsAdapter(private val events: MutableList<Event> = mutableListOf()) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bind(events[position])

    override fun getItemCount(): Int = events.size

    fun updateEventList(eventList: List<Event>) {
        events.clear()
        events.addAll(eventList)
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(event: Event) {
            with(itemView) {
                time.text = event.time
                Picasso.get().load(event.teamImageUrl).into(team_logo)
                val typeStyle = Utils.getEventTypeStyle(event.type)
                type.text = typeStyle.text
                type.setBackgroundResource(typeStyle.colorRes)
                event_notes.text = event.getEventText()
            }
        }
    }
}