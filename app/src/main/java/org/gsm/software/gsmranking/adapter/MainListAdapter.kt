package org.gsm.software.gsmranking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.databinding.RankingItemBinding
import org.gsm.software.gsmranking.viewmodel.MainViewModel


class MainListAdapter(private val viewmodel : MainViewModel)
    : ListAdapter<RankingResultQuery.Ranking, MainListAdapter.ViewHolder>(MainDiffUtil){

    inner class ViewHolder(private val binding: RankingItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RankingResultQuery.Ranking) {
            binding.ritem = item
            binding.vm = viewmodel

            binding.executePendingBindings() //데이터가 수정되면 즉각 바인딩
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<RankingItemBinding>(layoutInflater,viewType,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.ranking_item
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }


    override fun onBindViewHolder(holder:MainListAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object MainDiffUtil: DiffUtil.ItemCallback<RankingResultQuery.Ranking>(){
        override fun areItemsTheSame(oldItem: RankingResultQuery.Ranking, newItem: RankingResultQuery.Ranking): Boolean {
            //각 아이템들의 고유한 값을 비교해야 한다.
            return oldItem.repos_url == newItem.repos_url
        }

        override fun areContentsTheSame(oldItem: RankingResultQuery.Ranking, newItem: RankingResultQuery.Ranking): Boolean {
            return oldItem==newItem
        }
    }

}