package com.example.model;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicReference;

public class Plane {

    @Getter
    Integer planeRoute;
    @Getter
    Integer maxPassengers;
    @Getter
    Integer day;
    PlaneHistory planeHistory;

    public Plane(Integer day, Integer maxPassengers, Integer planeRoute) {
        this.maxPassengers = maxPassengers;
        this.planeRoute = planeRoute;
        this.day = day;
        planeHistory = new PlaneHistory();
    }

    private Plane(Plane other) {
        this.planeRoute = other.planeRoute;
        this.day = other.day;
        this.maxPassengers = other.maxPassengers;
        planeHistory = null;
    }

    public void changeMaxPassengersAndDay(Integer maxPassengers, Integer day) {
        planeHistory.addHistory(new Plane(this));
        this.day = day;
        this.maxPassengers = maxPassengers;
    }

    public Integer getAvailableSpace(Integer day) {
        Integer result = day - this.day;
        if (result <= 0) {
            return 0;
        }
        result *= maxPassengers;
        if (planeHistory == null) {
            return result;
        }
        AtomicReference<Integer> currentPlaneDay = new AtomicReference<>(this.day);
        result += planeHistory.history.stream()
                .sorted((p1, p2) -> Integer.compare(p2.day, p1.day))
                .filter(plane -> currentPlaneDay.get() <= day)
                .mapToInt(plane -> {
                    Integer availableSpace = getAvailableSpace(plane.day, currentPlaneDay.get(), plane.maxPassengers);
                    currentPlaneDay.set(plane.day);
                    return availableSpace;
                })
                .sum();
        return result;
    }

    private Integer getAvailableSpace(Integer startingDay, Integer endingDay, Integer maxPassengers){
        return maxPassengers * (endingDay - startingDay);
    }
}
