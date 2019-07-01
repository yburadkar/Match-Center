package com.yb.uadnd.matchcentre.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.Match.Data.Team.Player

class LineUpAdapter(var mPlayers: ArrayList<Player>): RecyclerView.Adapter<LineUpAdapter.PlayerViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        mContext = parent.context
        var view = LayoutInflater.from(mContext).inflate(R.layout.line_up_list_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mPlayers.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = mPlayers.get(position)
        holder.shirtNumber.text = player.shirtNumber.toString()
        holder.name.text = player.getPlayerName()
        holder.position.text = player.position
    }

    class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var shirtNumber: TextView
        var name: TextView
        var position: TextView
        init {
            shirtNumber = itemView.findViewById(R.id.shirt_number)
            name = itemView.findViewById(R.id.name)
            position = itemView.findViewById(R.id.position)
        }
    }
}