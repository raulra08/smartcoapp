package com.smartco.app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.smartco.app.utils.Constants.EXTRA_MESSAGE_KEY
import com.smartco.app.views.stock.ShopStockActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the Send button */
    fun startStockActivity(view: View) {
        val buttonText = findViewById<Button>(R.id.stock_button)
        val message = buttonText.text.toString()
        val intent = Intent(this, ShopStockActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE_KEY, message)
        }
        startActivity(intent)
    }
}
