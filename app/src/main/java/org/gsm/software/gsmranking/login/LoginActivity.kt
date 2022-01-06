package org.gsm.software.gsmranking.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import org.gsm.software.gsmranking.R
import org.gsm.software.gsmranking.databinding.ActivityLoginBinding
import org.gsm.software.gsmranking.model.data.User
import org.gsm.software.gsmranking.preference.SharedManager

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private var doubleBackToExit = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.activity = this
    }

    override fun onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed()
            overridePendingTransition(R.anim.right_slide_enter, R.anim.right_slide_exit)
        } else {
            Toast.makeText(this, "작성을 종료 하시려면 한번 더 눌러주세요", Toast.LENGTH_SHORT).show()
            runDelayed(1500L) {
                doubleBackToExit = true
            }
        }
    }

    private fun runDelayed(millis: Long, function: () -> Unit) {
        Handler(Looper.getMainLooper()).postDelayed(function, millis)
    }

    fun login() = with(binding) {
        val intent = Intent(this@LoginActivity, LoginCheckActivity::class.java)
        val id = githubId.text.toString()
        if(id.isNotEmpty()){
            intent.putExtra("id", githubId.text.toString())
            startActivity(intent)

        }else{
            Toast.makeText(this@LoginActivity, "아이디를 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
    }

}