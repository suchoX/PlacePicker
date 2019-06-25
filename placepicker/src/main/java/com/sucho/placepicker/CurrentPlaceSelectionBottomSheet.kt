package com.sucho.placepicker

import android.content.Context
import android.location.Location
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN

class CurrentPlaceSelectionBottomSheet @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyleAttr: Int = -1
) : CoordinatorLayout(context, attrs, defStyleAttr) {

  private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
  private lateinit var rootView: CoordinatorLayout
  private lateinit var placeNameTextView: TextView
  private lateinit var placeAddressTextView: TextView
  private lateinit var placeCoordinatesTextView: TextView
  private lateinit var placeProgressBar: ProgressBar

  val isShowing: Boolean
    get() = bottomSheetBehavior!!.state != STATE_HIDDEN

  init {
    initialize(context)
  }

  private fun initialize(context: Context) {
    rootView = inflate(context, R.layout.bottom_sheet_view, this) as CoordinatorLayout
    bottomSheetBehavior = BottomSheetBehavior.from<View>(rootView!!.findViewById<View>(R.id.root_bottom_sheet))
    bottomSheetBehavior!!.isHideable = true
    bottomSheetBehavior!!.state = STATE_HIDDEN
    bindViews()
  }

  private fun bindViews() {
    placeNameTextView = findViewById(R.id.text_view_place_name)
    placeAddressTextView = findViewById(R.id.text_view_place_address)
    placeCoordinatesTextView = findViewById(R.id.text_view_place_coordinates)
    placeProgressBar = findViewById(R.id.progress_bar_place)
  }

  fun showCoordinatesTextView(show: Boolean) {
    placeCoordinatesTextView.visibility = if (show) View.VISIBLE else View.GONE
  }

  fun setPrimaryTextColor(color: Int) {
    placeNameTextView.setTextColor(color)
  }

  fun setSecondaryTextColor(color: Int) {
    placeAddressTextView.setTextColor(color)
  }

  fun setPlaceDetails(
    latitude: Double,
    longitude: Double,
    shortAddress: String,
    fullAddress: String
  ) {

    if (latitude == -1.0 || longitude == -1.0) {
      placeNameTextView.text = ""
      placeAddressTextView.text = ""
      placeProgressBar.visibility = View.VISIBLE
      return
    }
    placeProgressBar.visibility = View.INVISIBLE

    placeNameTextView.text = if (shortAddress.isEmpty()) "Dropped Pin" else shortAddress
    placeAddressTextView.text = fullAddress
    placeCoordinatesTextView.text = Location.convert(latitude, Location.FORMAT_DEGREES) + ", " + Location.convert(longitude, Location.FORMAT_DEGREES)
  }

  fun showLoadingBottomDetails() {
    if (!isShowing) {
      toggleBottomSheet()
    }
    placeNameTextView.text = ""
    placeAddressTextView.text = ""
    placeCoordinatesTextView.text = ""
    placeProgressBar.visibility = View.VISIBLE
  }

  fun dismissPlaceDetails() {
    toggleBottomSheet()
  }

  private fun toggleBottomSheet() {
    bottomSheetBehavior!!.peekHeight = rootView.findViewById<View>(R.id.bottom_sheet_header)
        .height
    bottomSheetBehavior!!.state = if (isShowing) STATE_HIDDEN else STATE_COLLAPSED
  }

}