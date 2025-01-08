package com.example.service;

import com.example.model.Plane;

import java.util.concurrent.atomic.AtomicInteger;

public class PlaneService {

    public static Integer getAvailableSpace(Plane plane, Integer endingDay) {
        Integer result = endingDay - plane.getDay();
        if (result <= 0) {
            return 0;
        }
        result *= plane.getMaxPassengers();
        if (plane.getPlaneHistory() == null) {
            return result;
        }
        AtomicInteger currentPlaneDay = new AtomicInteger(plane.getDay());
        result += plane.getPlaneHistory().getHistory().stream()
                .sorted()
                .filter(historicPlane -> currentPlaneDay.get() <= endingDay)
                .mapToInt((final Plane historicPlane) -> {
                    Integer availableSpace = historicPlane.getAvailableSpace(historicPlane.getDay(), currentPlaneDay.get(), historicPlane.getMaxPassengers());
                    currentPlaneDay.set(historicPlane.getDay());
                    return availableSpace;
                })
                .sum();
        return result;
    }
}
