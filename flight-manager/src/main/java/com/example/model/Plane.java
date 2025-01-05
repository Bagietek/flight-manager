package com.example.model;

import com.example.exception.PlaneException;
import lombok.Getter;

import java.util.HashMap;

public class Plane {

    HashMap<Integer,Integer> passengersAtDay; // day , passengerAmount
    @Getter
    Integer planeRoute;

    public Plane(HashMap<Integer, Integer> passengersAtDay, Integer planeRoute) {
        this.passengersAtDay = passengersAtDay;
        this.planeRoute = planeRoute;
    }

    public void removePlane(Integer day){
        if(passengersAtDay.containsKey(day)){
            passengersAtDay.remove(day);
            return;
        }
        throw new PlaneException("Plane is not coursing that day");
    }

    public void addPlane(Integer day, Integer passengerAmount){
        if(!passengersAtDay.containsKey(day)){
            passengersAtDay.put(day,passengerAmount);
            return;
        }
        throw new PlaneException("Plane is already used this day");
    }

    public void changeMaxPassengers(Integer day, Integer passengerAmount){
        if(passengersAtDay.containsKey(day)){
            passengersAtDay.replace(day,passengerAmount);
            return;
        }
        throw new PlaneException("Plane is not coursing that day");
    }

    public Integer getAvailableSpace(Integer day){
        return passengersAtDay.get(day);
    }

}
