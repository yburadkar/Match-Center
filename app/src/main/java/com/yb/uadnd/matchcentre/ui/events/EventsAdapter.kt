package com.yb.uadnd.matchcentre.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.databinding.EventListItemBinding
import com.yb.uadnd.matchcentre.model.Match.Data.Event

class EventsAdapter(private val events: MutableList<Event> = mutableListOf()) : RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bind(events[position])

    override fun getItemCount(): Int = events.size

    fun updateEventList(eventList: List<Event>) {
        events.clear()
        events.addAll(eventList)
        notifyDataSetChanged()
    }

    class EventViewHolder(private val binding: EventListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            with(binding) {
                time.text = event.time
                Picasso.get().load(event.teamImageUrl).into(teamLogo)
                val typeStyle = Utils.getEventTypeStyle(event.type)
                type.text = typeStyle.text
                type.setBackgroundResource(typeStyle.colorRes)
                eventNotes.text = event.getEventText()
            }
        }
    }
}