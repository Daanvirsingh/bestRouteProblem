package com.main;

import com.pojo.GeoLocation;
import com.utils.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Adding GeoLocations i.e Restaurants as r1 and r2; Customers as c1 and c2; Aman is at the start position
        GeoLocation start = new GeoLocation(12.9595, 77.6433,"start");
        GeoLocation r1= new GeoLocation(12.9387,77.6260,0.25,"rest1");
        GeoLocation r2= new GeoLocation(12.9724, 77.5806,0.2,"rest2");
        GeoLocation c1 = new GeoLocation(12.9595, 77.6433,"cust1");
        GeoLocation c2 = new GeoLocation(12.9584, 77.6176,"cust2");

        //Adding how the location graph is connected;
        start.setAdjacentGeoLocations(Arrays.asList(r1,r2));//Only need to go to a Restaurant first
        r1.setAdjacentGeoLocations(Arrays.asList(c1,c2,r2));
        r2.setAdjacentGeoLocations(Arrays.asList(c1,c2,r1));
        c1.setAdjacentGeoLocations(Arrays.asList(c2,r1,r2));
        c2.setAdjacentGeoLocations(Arrays.asList(c1,r1,r2));

        List<GeoLocation> result = fetchBestRoute(start);
        System.out.println("Fastest Route Would be:");
        for (GeoLocation location:result) {
            System.out.print(" ---> "+location.getName());
        }
    }

    public static double minimumTime = Double.MAX_VALUE;
    public static List<GeoLocation> fastestRoute= new ArrayList<GeoLocation>();

    public static List<GeoLocation> fetchBestRoute(GeoLocation start){
        goToLocation(start,new ArrayList<GeoLocation>(),0.0);
        return fastestRoute;
    }

    private static void goToLocation(GeoLocation currentPosition, List<GeoLocation> currentPath,Double currentTime) {

        //
        List<GeoLocation> newCurrentPath = new ArrayList<GeoLocation>(currentPath);;

        if(currentPath.size()>0){
            GeoLocation prevPosition = currentPath.get(currentPath.size()-1);

            //Adding travel time
            double travelTime= utils.travelTime(prevPosition,currentPosition);
            currentTime+=travelTime;

            //Checking if the food is still preparing or not and wait for it.
            if(currentPosition.getName().contains("rest")){
                currentTime = currentPosition.getPrepTime()>currentTime?currentPosition.getPrepTime():currentTime;
            }

        }

        //Reached the current position so adding to the path
        newCurrentPath.add(currentPosition);



        //At size 5 all location are already visited
        if(newCurrentPath.size()==5){
            if(minimumTime>currentTime){
                minimumTime = currentTime;
                fastestRoute = newCurrentPath;
            }

            return ;
        }
        for (GeoLocation nextLocation:currentPosition.getAdjacentGeoLocations()) {

            //Check if already Visited
            if(!newCurrentPath.contains(nextLocation)){

                //Only Need to go to customer after the order for that particular cutomer is taken.
                if(nextLocation.getName().contains("cust1")&&!utils.isVisitedByName(newCurrentPath,"rest1"))continue;
                if(nextLocation.getName().contains("cust2")&&!utils.isVisitedByName(newCurrentPath,"rest2"))continue;


                 goToLocation(nextLocation,newCurrentPath,currentTime);
            }

        }

        return ;
    }




}
