package com.yb.uadnd.matchcentre.ui.lineups

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.databinding.LineUpListItemBinding
import com.yb.uadnd.matchcentre.data.Match.Data.Team.Player

class LineUpAdapter(private val players: MutableList<Player> = mutableListOf()): RecyclerView.Adapter<LineUpAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding = LineUpListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) = holder.bind(players[position])

    fun updateList(playerList: List<Player>) {
        players.clear()
        players.addAll(playerList)
        notifyDataSetChanged()
    }

    class PlayerViewHolder(private val binding: LineUpListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            with(binding) {
                shirtNumber.text = player.shirtNumber.toString()
                name.text = player.getPlayerName()
                position.text = player.position
            }
        }
    }
}