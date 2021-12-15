package org.gsm.software.gsmranking.model

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.adapter.MainAdapter

object BindingAdapter {
    @BindingAdapter("rankingSet")
    @JvmStatic
    fun rankingSet(view:TextView,text : String){
       view.text = text.toString() + "등"
    }

    @BindingAdapter("bindList")
    @JvmStatic
    fun bindList(recyclerView: RecyclerView,items : ObservableArrayList<RankingResultQuery.Ranking>?){
        if(recyclerView.adapter == null){
            val adapter = MainAdapter()
            recyclerView.adapter = adapter
        }
        if (items != null) {
            (recyclerView.adapter as MainAdapter).items = items
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }
}