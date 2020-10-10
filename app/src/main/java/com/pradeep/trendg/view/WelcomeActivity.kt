package com.pradeep.trendg.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.pradeep.trendg.R
import com.pradeep.trendg.constants.SPLASH_DISPLAY_LENGTH

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler(Looper.myLooper()!!).postDelayed({
            val mainIntent = Intent(this@WelcomeActivity, HomeActivity::class.java)
            this@WelcomeActivity.startActivity(mainIntent)
            this@WelcomeActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}