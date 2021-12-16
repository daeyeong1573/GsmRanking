package org.gsm.software.gsmranking.model

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.adapter.MainAdapter
import org.gsm.software.gsmranking.adapter.MainListAdapter

object BindingAdapter {

    @BindingAdapter("rankSet")
    @JvmStatic
    fun rankingSet(view:TextView,text : List<String>){
       for (i in text.indices){
           view.text = text[i]+"등"
       }
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

    @BindingAdapter("listData")
    @JvmStatic
    fun listData(recyclerView: RecyclerView,items: List<RankingResultQuery.Ranking>?){
        val adapter = recyclerView.adapter as MainListAdapter
        Log.d(TAG, "listData: ${items?.get(0)?.contributions}")
        adapter.submitList(items?.toMutableList())
    }

}