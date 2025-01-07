package com.example.repository;

import com.example.model.Plane;

public interface PlaneRepository {

    void cancelPlane(Integer planeRoute, Integer day);
    void addPlane(Integer planeRoute, Integer day, Integer passengerAmount);
    void changePassengerAmountAndDay(Integer planeRoute, Integer day, Integer passengerAmount);
    Integer getAvailableSpace(Integer firstRoute, Integer lastRoute, Integer toDay);
    Plane getPlane(Integer planeRoute);
}
