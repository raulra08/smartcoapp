package com.smartco.app.views.stock

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.barcode.Barcode
import com.smartco.app.R
import com.smartco.app.views.scan.ScanBarcodeActivity
import kotlinx.android.synthetic.main.activity_shop_stock.*

class ShopStockActivity : AppCompatActivity() {

    private var scanResponse: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_stock)
        setSupportActionBar(toolbar)

        fab_add_shop_stock.setOnClickListener(clickListener)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 0) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                scanResponse = if (data != null) {
                    val barcode: Barcode = data.getParcelableExtra("barcode")
                    barcode.displayValue
                } else {
                    "No Barcode found"
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private val clickListener = View.OnClickListener { view ->
        startScanBarcodeActivity()
    }

    private fun startScanBarcodeActivity() {
        val i = Intent(this, ScanBarcodeActivity::class.java)
        startActivityForResult(i, 0)
    }

}
