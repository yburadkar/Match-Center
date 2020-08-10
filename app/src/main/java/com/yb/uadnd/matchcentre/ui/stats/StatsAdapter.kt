package com.yb.uadnd.matchcentre.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.databinding.StatsListItemBinding
import com.yb.uadnd.matchcentre.data.TeamStat

class StatsAdapter: ListAdapter<TeamStat, StatsAdapter.StatViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = StatsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) = holder.bind(getItem(position))

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

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamStat>() {

            override fun areItemsTheSame(oldItem: TeamStat, newItem: TeamStat): Boolean = oldItem === newItem
            override fun areContentsTheSame(oldItem: TeamStat, newItem: TeamStat): Boolean = oldItem == newItem

        }

    }
}