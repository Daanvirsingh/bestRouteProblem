package com.utils;

import com.pojo.GeoLocation;

import java.util.ArrayList;
import java.util.List;

public class utils {
    public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
    public final static double AVERAGE_SPEED = 20;// 20km/hr

    /*
        To Fetch distance between 2 geo locations in km
     */
    public static Double haversineDistance(GeoLocation g1,GeoLocation g2){
        double latDistance = Math.toRadians(g1.getLatitude() - g2.getLatitude());
        double lngDistance = Math.toRadians(g1.getLongitude() - g2.getLongitude());

        //SImplified Haverstine Formala
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(g1.getLatitude())) * Math.cos(Math.toRadians(g2.getLatitude()))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return AVERAGE_RADIUS_OF_EARTH_KM * c;
    }

    public static Double travelTime(GeoLocation g1,GeoLocation g2){
        return haversineDistance(g1,g2)/AVERAGE_SPEED;
    }

    public static boolean isVisitedByName(List<GeoLocation> locations, String name){
        for(GeoLocation loc : locations){
            if(loc.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

}
