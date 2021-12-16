package org.gsm.software.gsmranking.model

import android.content.ContentValues.TAG
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gsm.software.gsmranking.RankingResultQuery
import org.gsm.software.gsmranking.adapter.MainListAdapter

object BindingAdapter {

    @BindingAdapter("setProfile")
    @JvmStatic
    fun setProfile(view:ImageView,url : String){
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

    @BindingAdapter("setName","setGeneration")
    @JvmStatic
    fun setName(view: TextView,name : String, generation : Int){
        view.text = "$name | $generation 기"
    }

    @BindingAdapter("contributions")
    @JvmStatic
    fun contributions(view : TextView, contributions : Int){
        view.text = "contributions : $contributions"
    }

    @BindingAdapter("listData")
    @JvmStatic
    fun listData(recyclerView: RecyclerView,items: List<RankingResultQuery.Ranking>?){
        val adapter = recyclerView.adapter as MainListAdapter
        Log.d(TAG, "listData: ${items?.get(0)?.contributions}")
        adapter.submitList(items?.toMutableList())
    }

}