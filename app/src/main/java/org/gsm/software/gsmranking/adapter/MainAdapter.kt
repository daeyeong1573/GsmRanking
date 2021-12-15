package org.gsm.software.gsmranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.databinding.RankingItemBinding
import org.gsm.software.gsmranking.viewmodel.MainViewModel

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var items = ArrayList<RankingResultQuery.Ranking>()


    inner class ViewHolder(private val binding: RankingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item : RankingResultQuery.Ranking) = with(binding){
            ritem = item
        }
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.ranking_item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RankingItemBinding>(
            layoutInflater,
            viewType,
            parent,
            false
        )
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}