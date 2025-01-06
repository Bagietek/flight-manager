package com.example.repository;

import com.example.exception.PlaneException;
import com.example.model.Plane;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

public class PlaneRepositoryImpl implements PlaneRepository {
    HashMap<Integer, Plane> planes; // routeNumber , plane

    public PlaneRepositoryImpl() {
        planes = new HashMap<>();
    }

    @Override
    public void cancelPlane(Integer planeRoute, Integer day) {
        if(planes.containsKey(planeRoute)){
            planes.remove(planeRoute);
            return;
        }
        throw new PlaneException("Route does not exist");
    }

    @Override
    public void addPlane(Integer planeRoute, Integer day, Integer passengerAmount) {
        if(!planes.containsKey(planeRoute)){
            Plane plane = new Plane(day, passengerAmount, planeRoute);
            planes.put(planeRoute, plane);
            return;
        }
        throw new PlaneException("Plane already exists");
    }

    @Override
    public void changePassengerAmountAndDay(Integer planeRoute, Integer day, Integer passengerAmount) {
        if(planes.containsKey(planeRoute)){
            Plane plane = planes.get(planeRoute);
            plane.changeMaxPassengersAndDay(passengerAmount,day);
            planes.replace(planeRoute, plane);
            return;
        }
        throw new PlaneException("Route does not exist");
    }

    @Override
    public Integer getAvailableSpace(Integer firstRoute, Integer lastRoute, Integer toDay) {
        // todo: zmodyfikować tak aby zaliczało historię modyfikacji daneego samolotu, w przypadku usunięcia samolotu hisotria jest kasowana
        /*return IntStream.range(firstRoute - 1, lastRoute)
                .mapToObj(i -> planes.get(i))
                .filter(Objects::nonNull)
                .mapToInt(plane -> plane.getAvailableSpace(toDay))
                .sum();*/
        Integer availableSpace = 0;
        for (int i=firstRoute-1;i<lastRoute;i++){
            if(planes.get(i) == null){
                continue;
            }
            availableSpace += planes.get(i).getAvailableSpace(toDay);
        }

        return availableSpace;
    }
}
