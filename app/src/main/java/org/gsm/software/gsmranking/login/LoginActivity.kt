package org.gsm.software.gsmranking.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.ActivityLoginBinding
import org.gsm.software.gsmranking.model.data.User
import org.gsm.software.gsmranking.preference.SharedManager

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val sharedManager : SharedManager by lazy { SharedManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.right_slide_enter,R.anim.right_slide_exit)
    }

    fun login()= with(binding){
        val createUser = User().apply {
          id =  githubId.text.toString().trim()
        }
        sharedManager.saveUser(createUser)
    }

}