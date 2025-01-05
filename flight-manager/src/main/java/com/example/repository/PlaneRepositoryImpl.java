package com.example.repository;

import com.example.exception.PlaneException;
import com.example.model.Plane;

import java.util.HashMap;

public class PlaneRepositoryImpl implements PlaneRepository {
    HashMap<Integer, Plane> planes = new HashMap<>(); // planeNumber , plane

    public PlaneRepositoryImpl() {}

    @Override
    public void cancelPlane(Integer planeRoute, Integer day) {
        if(planes.containsKey(planeRoute)){
            Plane plane = planes.get(planeRoute);
            plane.removePlane(day);
            planes.replace(planeRoute,plane);
            return;
        }
        throw new PlaneException("Route does not exist");
    }

    @Override
    public void addPlane(Integer planeRoute, Integer day, Integer passengerAmount) {
        if(planes.containsKey(planeRoute)){
            Plane plane = planes.get(planeRoute);
            plane.addPlane(day, passengerAmount);
            planes.replace(planeRoute, plane);
        }
        throw new PlaneException("Route does not exist");
    }

    @Override
    public Integer getAvailableSpace(Integer firstRoute, Integer lastRoute, Integer toDay) {
        Integer availableSpace = 0;
        for (int i=firstRoute;i<=lastRoute;i++){
            if(!planes.containsKey(i)){
                continue;
            }
            for (int j=1;j<=toDay;j++){
                availableSpace += planes.get(i).getAvailableSpace(j);
            }
        }
        return availableSpace;
    }
}
