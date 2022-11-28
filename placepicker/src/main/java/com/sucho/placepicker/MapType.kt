package com.sucho.placepicker

import java.io.Serializable

/**
 * NORMAL: Typical road map. Shows roads, some features built by humans, and important natural features like rivers. Road and feature labels are also visible.
 * HYBRID: SATELLITE photograph data with road maps added. Road and feature labels are also visible.
 * SATELLITE: SATELLITE photograph data. Road and feature labels are not visible.
 * TERRAIN: Topographic data. The map includes colors, contour lines and labels, and perspective shading. Some roads and labels are also visible.
 * NONE: No tiles. The map will be rendered as an empty grid with no tiles loaded.
 */
enum class MapType : Serializable {
  NORMAL,
  HYBRID,
  SATELLITE,
  TERRAIN,
  NONE
}
