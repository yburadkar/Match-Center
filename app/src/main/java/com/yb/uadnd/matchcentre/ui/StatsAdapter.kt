package com.yb.uadnd.matchcentre.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.R
import com.yb.uadnd.matchcentre.model.TeamStat
import kotlinx.android.synthetic.main.stats_list_item.view.*

class StatsAdapter(private val mStats: MutableList<TeamStat> = mutableListOf()): RecyclerView.Adapter<StatsAdapter.StatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stats_list_item, parent, false)
        return StatViewHolder(view)
    }

    override fun getItemCount(): Int = mStats.size

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) = holder.bind(mStats[position])

    fun updateList(statList: List<TeamStat>) {
        mStats.clear()
        mStats.addAll(statList)
        notifyDataSetChanged()
    }

    class StatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(stat: TeamStat) {
            with(itemView) {
                stat_name.text = stat.statName
                home_stat.text = stat.homeText
                away_stat.text = stat.awayText

                var params = home_stat.layoutParams as LinearLayout.LayoutParams
                params.weight = stat.homeWeight
                home_stat.layoutParams = params

                params = away_stat.layoutParams as LinearLayout.LayoutParams
                params.weight = stat.awayWeight
                away_stat.layoutParams = params
            }
        }
    }
}