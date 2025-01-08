package com.example.model;

import lombok.Getter;

public class Plane implements Comparable<Plane> {

    @Getter
    private final Integer planeRoute;

    @Getter
    private Integer maxPassengers;

    @Getter
    private Integer day;

    @Getter
    private final PlaneHistory planeHistory;

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
        planeHistory.addToHistory(new Plane(this));
        this.day = day;
        this.maxPassengers = maxPassengers;
    }

    public Integer getAvailableSpace(Integer startingDay, Integer endingDay, Integer maxPassengers) {
        return maxPassengers * (endingDay - startingDay);
    }

    @Override
    public int compareTo(Plane o) {
        return Integer.compare( o.getDay(), this.day);
    }
}
