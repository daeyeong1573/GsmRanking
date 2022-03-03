package org.gsm.software.gsmranking.adapter

import android.content.ContentValues.TAG
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import org.gsm.software.gsmranking.R

object LoginAdapter {
    @BindingAdapter("loginCheck")
    @JvmStatic
    fun loginCheck(view : TextView, type : Boolean){
        if (type) view.visibility = View.VISIBLE
        else view.visibility = View.GONE

    }

    @BindingAdapter("changeProfile","checkLogin")
    @JvmStatic
    fun changeProfile(view : CircleImageView, url : String,check : Boolean){
        if (check) {
            view.setImageDrawable(R.drawable.ic_baseline_person_24.toDrawable())
            Glide.with(view.context)
                .load(R.drawable.set_github)
                .into(view)
        }
        else Glide.with(view.context).load(url).into(view)
    }

}