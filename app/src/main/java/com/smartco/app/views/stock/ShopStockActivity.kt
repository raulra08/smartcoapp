package com.smartco.app.views.stock

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.smartco.app.R
import com.smartco.app.utils.Constants
import com.smartco.app.views.scan.ScanBarcodeActivity
import kotlinx.android.synthetic.main.activity_shop_stock.*

class ShopStockActivity : AppCompatActivity() {

    private var scanResponse: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_stock)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                scanResponse = if (intent != null) {
                    val barcode: Barcode = intent.getParcelableExtra(Constants.EXTRA_BARCODE_RESULT_KEY)
                    barcode.displayValue
                } else {
                    "No Barcode found"
                }

                val activityView = findViewById<View>(R.id.activity_shop_stock)
                Snackbar.make(activityView, scanResponse, Snackbar.LENGTH_LONG)
                        .show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, intent)
        }
    }

    fun startScanBarcodeActivity(view: View) {
        val i = Intent(this, ScanBarcodeActivity::class.java)
        startActivityForResult(i, 0)
    }

}
