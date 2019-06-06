package com.sucho.placepicker

import android.location.Address
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AddressData(
  var latitude: Double,
  var longitude: Double,
  var addressList: List<Address>? = null
): Parcelable {
  override fun toString(): String {
    return latitude.toString()+"\n" +
            longitude.toString()+"\n"+
            getAddressString()
  }
  private fun getAddressString():String {
    addressList?.let { if(it.isNotEmpty()) return it[0].getAddressLine(0)}
    return ""
  }
}