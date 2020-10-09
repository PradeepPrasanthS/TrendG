package com.pradeep.trendg

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.pradeep.trendg.constants.SPLASH_DISPLAY_LENGTH

class WelcomeActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed({
            val mainIntent = Intent(this@WelcomeActivity, HomeActivity::class.java)
            this@WelcomeActivity.startActivity(mainIntent)
            this@WelcomeActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}