package com.smartco.app.views.scan

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.widget.Toast
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.smartco.app.R
import com.smartco.app.utils.Constants.EXTRA_BARCODE_RESULT_KEY
import java.io.IOException
import androidx.annotation.NonNull




class ScanBarcodeActivity : AppCompatActivity() {

    private lateinit var cameraPreview: SurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_barcode)

        cameraPreview = findViewById<SurfaceView>(R.id.camera_preview)

        createCameraSource()
    }

    fun createCameraSource() {
        val barcodeDetector: BarcodeDetector = BarcodeDetector.Builder(this).build()
        val cameraSource: CameraSource = CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(2220, 1080)
                .build()

        val activityView = findViewById<View>(R.id.activity_scan_barcode)
        Snackbar.make(activityView, "Surface Created", Snackbar.LENGTH_LONG)
                .show()

        cameraPreview.holder?.addCallback(object : SurfaceHolder.Callback {

            override fun surfaceCreated(holder: SurfaceHolder) {
                val activity = this@ScanBarcodeActivity
                if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA) == false) {
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CAMERA), 1242)
                        Toast.makeText(getApplicationContext(), "request permission", Toast.LENGTH_SHORT).show()
                    }
                }
                try {
                    cameraSource.start(cameraPreview.holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                Toast.makeText(getApplicationContext(), "Stopping camera", Toast.LENGTH_SHORT).show()
                cameraSource.stop()
            }
        })

        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {
            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.detectedItems
                if (barcodes.size() > 0) {
                    val i = Intent().apply {
                        putExtra(EXTRA_BARCODE_RESULT_KEY, barcodes.valueAt(0))
                    }
                    setResult(CommonStatusCodes.SUCCESS, i)
                    finish()
                }
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            1242 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            // Open Camera
                return
        }
    }
}
