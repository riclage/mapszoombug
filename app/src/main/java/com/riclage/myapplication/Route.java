package com.riclage.myapplication;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

class Route {

    private final List<LatLng> points;

    Route(String encodedPolyline) {
        this.points = decodePoly(encodedPolyline);
    }

    List<LatLng> points() {
        return points;
    }

    private static List<LatLng> decodePoly(String encodedPoly) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encodedPoly.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encodedPoly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encodedPoly.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((double) lat / 1E5, (double) lng / 1E5);
            poly.add(p);
        }
        return poly;
    }
}
