package com.sucho.placepicker

import android.app.Activity
import android.content.Intent

class PlacePicker {

    class IntentBuilder {

      private lateinit var activity: Activity
      private var showLatLong: Boolean = false
      private var latitude: Double = Constants.DEFAULT_LATITUDE
      private var longitude: Double = Constants.DEFAULT_LONGITUDE
      private var zoom: Float = Constants.DEFAULT_ZOOM
      private var addressRequired: Boolean = true
      private var hideMarkerShadow: Boolean = false

      fun showLatLong(showLatLong: Boolean) = apply { this.showLatLong = showLatLong }

      fun setLatLong(
        latitude: Double,
        longitude: Double
      ) = apply {
        if(latitude == -1.0 || longitude == -1.0) {
          this.latitude = Constants.DEFAULT_LATITUDE
          this.longitude = Constants.DEFAULT_LONGITUDE
        } else {
          this.latitude = latitude
          this.longitude = longitude
        }
      }

      fun setMapZoom(zoom: Float) = apply { this.zoom = zoom }

      fun setAddressRequired(addressRequired: Boolean) = apply { this.addressRequired = addressRequired }

      fun hideMarkerShadow(hideMarkerShadow: Boolean) = apply { this.hideMarkerShadow = hideMarkerShadow }

      fun build(activity: Activity): Intent {
        this.activity = activity
        val intent = Intent(activity, PlacePickerActivity::class.java)
        intent.putExtra(Constants.SHOW_LAT_LONG_INTENT, showLatLong)
        intent.putExtra(Constants.INITIAL_LATITUDE_INTENT, latitude)
        intent.putExtra(Constants.INITIAL_LONGITUDE_INTENT, longitude)
        intent.putExtra(Constants.INITIAL_ZOOM_INTENT, zoom)
        intent.putExtra(Constants.HIDE_MARKER_SHADOW_INTENT, hideMarkerShadow)
        return intent
      }
    }

  }