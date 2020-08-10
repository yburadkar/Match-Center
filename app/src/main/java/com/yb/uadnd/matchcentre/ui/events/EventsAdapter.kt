package com.yb.uadnd.matchcentre.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.databinding.EventListItemBinding
import com.yb.uadnd.matchcentre.data.Match.Data.Event

class EventsAdapter : ListAdapter<Event, EventsAdapter.EventViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bind(getItem(position))

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

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Event>() {
            override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                return oldItem == newItem
            }

        }
    }
}