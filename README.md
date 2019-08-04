 [ ![Download](https://api.bintray.com/packages/suchox/Android/com.sucho.placepicker/images/download.svg) ](https://bintray.com/suchox/Android/com.sucho.placepicker/_latestVersion)

# PlacePicker
Place Picker for Google Maps has been deprecated in Android and we are told to move to paid APIs. Autocomplete, Nearby and Places photos APIs are chargeable after a number of loads. [Check Pricing here](https://cloud.google.com/maps-platform/pricing/sheet/)

<p align="center"><img src="https://github.com/suchoX/PlacePicker/blob/master/screens/place_picker_deprecated.png"></p>

Thankfully, Static and Dynamic Maps on Mobile and Geocoder is still free. PlacePicker is a Place Picker alternative library that allows you to pick a point in the map and get its coordinates and Address using Geocoder instead of Google APIs

<p align="center"><img src="https://github.com/suchoX/PlacePicker/blob/master/screens/demo.gif"></p>

## Adding PlacePicker to your project

Include the following dependencies in your app's build.gradle :

```
dependencies {
  implementation 'com.google.android.gms:play-services-maps:16.1.0'
  implementation 'com.sucho:placepicker:0.1.7'
  
  implementation 'com.google.android.material:material:1.1.0-alpha01'
}
```
PlacePicker Uses **AndroidX** artifacts, thus to use it without issues, make sure your application has been migrated to AndroidX as well. If you havent done it already, [Here's How](https://developer.android.com/jetpack/androidx/migrate)

## How to use

1. You need a Maps API key and add it to your app. [Here's How](https://developers.google.com/maps/documentation/android-sdk/signup)
2. To start The `PlacePickerActivity`:

``` java
val intent = PlacePicker.IntentBuilder()
                .setLatLong(40.748672, -73.985628)  // Initial Latitude and Longitude the Map will load into
                .showLatLong(true)  // Show Coordinates in the Activity
                .setMapZoom(12.0f)  // Map Zoom Level. Default: 14.0
                .setAddressRequired(true) // Set If return only Coordinates if cannot fetch Address for the coordinates. Default: True
                .hideMarkerShadow(true) // Hides the shadow under the map marker. Default: False
                .setMarkerDrawable(R.drawable.marker) // Change the default Marker Image
                .setMarkerImageImageColor(R.color.colorPrimary)
                .setFabColor(R.color.fabColor)
                .setPrimaryTextColor(R.color.primaryTextColor) // Change text color of Shortened Address
                .setSecondaryTextColor(R.color.secondaryTextColor) // Change text color of full Address
                .setMapRawResourceStyle(R.raw.map_style)  //Set Map Style
                .setMapType(MapType.NORMAL)
                .build(this)
            startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST)
```
3. Then get the data `onActivityResult`
```java
override fun onActivityResult(requestCode: Int,resultCode: Int,data: Intent?) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
            val addressData = data?.getParcelableExtra<AddressData>(Constants.ADDRESS_INTENT)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
```


If you are using Java instead of Kotlin:
```java
Intent intent = new PlacePicker.IntentBuilder()
                ...
                
                startActivityForResult(intent, Constants.PLACE_PICKER_REQUEST);
                
                ...

@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Constants.PLACE_PICKER_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
              AddressData addressData = data.getParcelableExtra(Constants.ADDRESS_INTENT);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
```

## Changelog

### [0.1.7]
- Option of Map Type (Normal, Satellite, Terrain, Hybrid, None)
- Map Styling


**Note:** This is inspired from Mapbox [Android Place Picker plugin](https://docs.mapbox.com/android/plugins/examples/place-picker/). Code and UI has been reused from the open source library hosted on [Github](https://github.com/mapbox/mapbox-plugins-android). Their copyright license has been added [here](https://github.com/suchoX/PlacePicker/blob/master/LICENSE)
