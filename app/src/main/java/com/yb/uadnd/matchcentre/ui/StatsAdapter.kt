package com.yb.uadnd.matchcentre.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.TeamStat

class StatsAdapter(private var mStats: ArrayList<TeamStat>): RecyclerView.Adapter<StatsAdapter.StatViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.stats_list_item, parent, false)
        return StatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mStats.size
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val stat = mStats.get(position)
        holder.stat_name.text = stat.statName
        holder.home_stat.text = stat.homeText
        holder.away_stat.text = stat.awayText

        var params = holder.home_stat.layoutParams as LinearLayout.LayoutParams
        params.weight = stat.homeWeight
        holder.home_stat.layoutParams = params

        params = holder.away_stat.layoutParams as LinearLayout.LayoutParams
        params.weight = stat.awayWeight
        holder.away_stat.layoutParams = params
    }

    class StatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var stat_name: TextView
        var home_stat: TextView
        var away_stat: TextView
        init {
            stat_name = itemView.findViewById(R.id.stat_name)
            home_stat = itemView.findViewById(R.id.home_stat)
            away_stat = itemView.findViewById(R.id.away_stat)
        }
    }
}