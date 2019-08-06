package com.smartco.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.smartco.app.databinding.ActivityMainBinding
import com.smartco.app.utils.Constants.EXTRA_MESSAGE_KEY
import com.smartco.app.views.stock.ShopStockActivity

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        (mainBinding::setViewModel)(MainViewModel())
        initAppCenter()
    }

    /** Called when the user taps the Send button */
    fun startStockActivity(view: View) {
        val intent = Intent(this, ShopStockActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE_KEY, "don't know what to send right now.")
        }
        startActivity(intent)
    }

    private fun initAppCenter() {
        AppCenter.start(application, "1dadff7b-ba5f-4dfb-ac02-b63531b8f7cd",
                Analytics::class.java, Crashes::class.java)
    }
}
