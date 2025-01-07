package com.example.model;

import com.example.exception.PlaneException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Objects;

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
        if (planeHistory == null) {
            return result * maxPassengers;
        }
        return result * maxPassengers + planeHistory.getAvailableHistoricSpace(day, this.day);
    }
}
