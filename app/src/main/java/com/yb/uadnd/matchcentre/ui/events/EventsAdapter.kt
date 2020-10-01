package com.yb.uadnd.matchcentre.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yb.uadnd.matchcentre.Utils
import com.yb.uadnd.matchcentre.databinding.EventListItemBinding
import com.yb.uadnd.matchcentre.ui.models.UiMatchEvent

class EventsAdapter : ListAdapter<UiMatchEvent, EventsAdapter.EventViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bind(getItem(position))

    class EventViewHolder(private val binding: EventListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: UiMatchEvent) {
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
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiMatchEvent>() {

            override fun areItemsTheSame(oldItem: UiMatchEvent, newItem: UiMatchEvent): Boolean = oldItem === newItem

            override fun areContentsTheSame(oldItem: UiMatchEvent, newItem: UiMatchEvent): Boolean = oldItem == newItem

        }
    }
}