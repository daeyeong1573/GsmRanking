package org.gsm.software.gsmranking.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.ActivityLoginCheckBinding
import org.gsm.software.gsmranking.main.MainActivity
import org.gsm.software.gsmranking.model.data.User
import org.gsm.software.gsmranking.preference.SharedManager
import org.koin.android.ext.android.get

class LoginCheckActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginCheckBinding.inflate(layoutInflater) }
    private val sharedManager : SharedManager by lazy { SharedManager(this) }
    private lateinit var gitId : String

    override fun onResume() {
        super.onResume()
        var intent = intent
        gitId = intent.getStringExtra("id").toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this

    }



    fun applyLogin(){
        val createUser = User().apply {
            id = gitId
        }
        sharedManager.saveUser(createUser)
        startActivity(Intent(this,MainActivity::class.java))
    }

    fun denyLogin(){
        finish()
        overridePendingTransition(R.anim.right_slide_enter,R.anim.right_slide_exit)
    }

}