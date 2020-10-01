package com.yb.uadnd.matchcentre.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yb.uadnd.matchcentre.ui.models.UiTeamStat
import com.yb.uadnd.matchcentre.databinding.StatsListItemBinding

class StatsAdapter : ListAdapter<UiTeamStat, StatsAdapter.StatViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val binding = StatsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) = holder.bind(getItem(position))

    class StatViewHolder(private val binding: StatsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stat: UiTeamStat) {
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

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UiTeamStat>() {

            override fun areItemsTheSame(oldItem: UiTeamStat, newItem: UiTeamStat): Boolean = oldItem === newItem

            override fun areContentsTheSame(oldItem: UiTeamStat, newItem: UiTeamStat): Boolean = oldItem == newItem

        }

    }
}