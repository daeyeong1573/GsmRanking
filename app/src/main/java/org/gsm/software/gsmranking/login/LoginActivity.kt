package org.gsm.software.gsmranking.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.gsm.software.gsmranking.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.right_slide_enter,R.anim.right_slide_exit)
    }
}