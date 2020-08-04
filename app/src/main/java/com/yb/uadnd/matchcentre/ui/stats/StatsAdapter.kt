package com.yb.uadnd.matchcentre.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.databinding.StatsListItemBinding
import com.yb.uadnd.matchcentre.model.TeamStat

class StatsAdapter(private val stats: MutableList<TeamStat> = mutableListOf()): RecyclerView.Adapter<StatsAdapter.StatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = StatsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun getItemCount(): Int = stats.size

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) = holder.bind(stats[position])

    fun updateList(statList: List<TeamStat>) {
        stats.clear()
        stats.addAll(statList)
        notifyDataSetChanged()
    }

    class StatViewHolder(private val binding: StatsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stat: TeamStat) {
            with(binding) {
                statName.text = stat.statName
                homeStat.text = stat.homeText
                awayStat.text = stat.awayText

                var params = homeStat.layoutParams as LinearLayout.LayoutParams
                params.weight = stat.homePercent
                homeStat.layoutParams = params

                params = awayStat.layoutParams as LinearLayout.LayoutParams
                params.weight = stat.awayPercent
                awayStat.layoutParams = params
            }
        }
    }
}