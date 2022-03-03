package org.gsm.software.gsmranking.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.gsm.software.gsmranking.MyApplication
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.ActivityLoginCheckBinding
import org.gsm.software.gsmranking.main.MainActivity

@AndroidEntryPoint
class LoginCheckActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginCheckBinding.inflate(layoutInflater) }
    private val lvm : LoginViewModel by viewModels()
    lateinit var gitId : String

    override fun onResume() {
        super.onResume()
        var intent = intent
        gitId = intent.getStringExtra("id").toString()
        getUser(gitId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this

    }

   private fun getUser(id :String) = with(binding){
        lvm.getUser(id)

        lvm.photo_url.observe(this@LoginCheckActivity, Observer {
            Glide.with(this@LoginCheckActivity)
                .load(it)
                .into(githubProfile)
        })

       lvm.nickName.observe(this@LoginCheckActivity, Observer {
           githubNick.text = it.toString()
       })

       lvm.bio.observe(this@LoginCheckActivity, Observer {
           githubDescription.text = it.toString()
       })

    }



    fun applyLogin(){
        CoroutineScope(Dispatchers.IO).launch {
            MyApplication.getInstance().getDataStore().setUser(gitId)
        }
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun denyLogin(){
        finish()
        overridePendingTransition(R.anim.right_slide_enter,R.anim.right_slide_exit)
    }

}