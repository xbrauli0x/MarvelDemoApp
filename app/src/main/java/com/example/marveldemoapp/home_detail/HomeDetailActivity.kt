package com.example.marveldemoapp.home_detail

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marveldemoapp.R

class HomeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (deviceNeedsToBeOnPortraitMode()) {
            forceToPortraitMode()
        }
        setContentView(R.layout.activity_main)
    }

    private fun deviceNeedsToBeOnPortraitMode(): Boolean {
        return resources.getBoolean(R.bool.portrait_only)
    }

    private fun forceToPortraitMode() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}