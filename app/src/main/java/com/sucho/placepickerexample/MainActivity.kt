package com.sucho.placepickerexample

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sucho.placepicker.AddressData
import com.sucho.placepicker.Constants
import com.sucho.placepicker.MapType
import com.sucho.placepicker.PlacePicker

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA)

        findViewById<Button>(R.id.open_place_picker_button).setOnClickListener {
            val intent = PlacePicker.IntentBuilder()
                .setLatLong(40.748672, -73.985628)
                .showLatLong(true)
                .setMapRawResourceStyle(R.raw.map_style)
                .setMapType(MapType.NORMAL)
                .setPlaceSearchBar(true, applicationInfo.metaData.getString("com.google.android.geo.API_KEY"))
                .build(this)
            startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST)
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    val addressData = data?.getParcelableExtra<AddressData>(Constants.ADDRESS_INTENT)
                    findViewById<TextView>(R.id.address_data_text_view).text = addressData.toString()
                } catch (e: Exception) {
                    Log.e("MainActivity", e.message)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
