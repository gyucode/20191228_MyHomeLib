package com.example.a20191228_myhomelib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

    }

    override fun setValues() {
        openProperActivity()
    }

    private fun openProperActivity(){
        Handler().postDelayed({
            val intent = Intent(mContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        },1500)
    }

}
