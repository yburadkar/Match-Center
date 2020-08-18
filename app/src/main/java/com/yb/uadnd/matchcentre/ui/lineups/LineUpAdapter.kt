package com.yb.uadnd.matchcentre.ui.lineups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.data.remote.TeamPlayer
import com.yb.uadnd.matchcentre.databinding.LineUpListItemBinding

class LineUpAdapter : ListAdapter<TeamPlayer, LineUpAdapter.PlayerViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = LineUpListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) = holder.bind(getItem(position))

    class PlayerViewHolder(private val binding: LineUpListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: TeamPlayer) {
            with(binding) {
                shirtNumber.text = player.shirtNumber.toString()
                name.text = player.getPlayerName()
                position.text = player.position
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamPlayer>() {

            override fun areItemsTheSame(oldItem: TeamPlayer, newItem: TeamPlayer): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TeamPlayer, newItem: TeamPlayer): Boolean = oldItem == newItem

        }
    }
}