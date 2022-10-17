package org.gsm.software.gsmranking.adapter

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.RankingResultQuery

object RankingAdapter {

    @BindingAdapter("setProfile")
    @JvmStatic
    fun setProfile(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }

    @BindingAdapter("foundVisible")
    @JvmStatic
    fun foundVisible(view: ImageView, type: Boolean) {
        if (type) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("notFoundVisible")
    @JvmStatic
    fun notFoundVisible(view: ImageView, type: Boolean) {
        if (type) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.GONE
        }
    }

    @BindingAdapter("notFoundMessage")
    @JvmStatic
    fun notFoundMessage(view: TextView, text: String) {
        view.text = text
    }

    @BindingAdapter("recyclerVisible")
    @JvmStatic
    fun recyclerVisible(view: RecyclerView, type: Boolean) {
        if (!type) {
            view.visibility = View.VISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("loading")
    @JvmStatic
    fun loading(view:View,type : Boolean){
        if(!type){
            view.visibility = View.GONE
        }else{
            view.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("setName", "setGeneration")
    @JvmStatic
    fun setName(view: TextView, name: String, generation: Int) {
        view.text = "$name | $generation 기"
    }

    @BindingAdapter("type","amount")
    @JvmStatic
    fun contributions(view: TextView, type : Int, amount: Int) {
        var text : String = ""
        when(type){
            1 -> text = "contribution"
            2 -> text = "star"
            3 -> text = "fork"
        }
        view.text = "$text : $amount"
    }

    @BindingAdapter("listData")
    @JvmStatic
    fun listData(recyclerView: RecyclerView, items: List<RankingResultQuery.Ranking>?) {
        val adapter = recyclerView.adapter as RankingListAdapter
        Log.d(TAG, "listData: ${items?.get(0)?.contributions}")
        adapter.submitList(items?.toMutableList())
    }


}