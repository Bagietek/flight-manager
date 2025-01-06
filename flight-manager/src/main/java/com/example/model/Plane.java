package com.example.model;

import com.example.exception.PlaneException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Objects;

public class Plane {

    @Getter
    Integer planeRoute;
    Integer maxPassengers;
    Integer day;
    PlaneHistory planeHistory;

    public Plane(Integer day, Integer maxPassengers, Integer planeRoute) {
        this.maxPassengers = maxPassengers;
        this.planeRoute = planeRoute;
        this.day = day;
        planeHistory = new PlaneHistory();
    }

    private Plane(Plane other){
        this.planeRoute = other.planeRoute;
        this.day = other.day;
        this.maxPassengers = other.maxPassengers;
        planeHistory = null;
    }

    public void changeMaxPassengersAndDay(Integer maxPassengers, Integer day){
        planeHistory.addHistory(new Plane(this));
        this.day = day;
        this.maxPassengers = maxPassengers;
    }

    public Integer getAvailableSpace(Integer day){
        Integer result = day - this.day;
        if(result <= 0 ){
            return  0;
        }
        // todo: debug delete later
        //Integer historySpace = getAvailableHistoricSpace(day);
        if(planeHistory == null){
            return result * maxPassengers;
        }
        Integer historySpace = planeHistory.getAvailableHistoricSpace(day,this.day);

        return result * maxPassengers + historySpace;
    }

    private Integer getAvailableHistoricSpace(Integer day){
        if(planeHistory == null || planeHistory.getHistory().isEmpty()){
            return 0;
        }
        return planeHistory.getHistory().stream()
                .mapToInt(p -> p.getAvailableSpace(day - 1)) // -1, counting that plane is used by the new
                .sum();
    }

}
