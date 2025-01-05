package com.example.repository;

public interface PlaneRepository {

    void cancelPlane(Integer planeRoute, Integer day);
    void addPlane(Integer planeRoute, Integer day, Integer passengerAmount);
    Integer getAvailableSpace(Integer firstRoute, Integer lastRoute, Integer toDay);
}