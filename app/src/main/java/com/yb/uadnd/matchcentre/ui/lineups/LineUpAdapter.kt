package com.yb.uadnd.matchcentre.ui.lineups

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match.Data.Team.Player
import kotlinx.android.synthetic.main.line_up_list_item.view.*

class LineUpAdapter(private val mPlayers: MutableList<Player> = mutableListOf()): RecyclerView.Adapter<LineUpAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.line_up_list_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int = mPlayers.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) = holder.bind(mPlayers[position])

    fun updateList(playerList: List<Player>) {
        mPlayers.clear()
        mPlayers.addAll(playerList)
        notifyDataSetChanged()
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player) {
            with(itemView) {
                shirt_number.text = player.shirtNumber.toString()
                name.text = player.getPlayerName()
                position.text = player.position
            }
        }
    }
}