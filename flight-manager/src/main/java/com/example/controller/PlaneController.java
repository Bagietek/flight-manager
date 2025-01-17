package com.example.controller;

import com.example.repository.PlaneRepository;

public class PlaneController {
    private final PlaneRepository planeRepository;

    public PlaneController(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public void addPlane(Integer planeRoute, Integer day, Integer passengerAmount) {
        planeRepository.addPlane(planeRoute, day, passengerAmount);
    }

    public void cancelPlane(Integer planeRoute, Integer day) {
        planeRepository.cancelPlane(planeRoute, day);
    }

    public Integer getAvailableSpace(final Integer firstRoute, final Integer lastRoute, final Integer toDay) {
        return planeRepository.getAvailableSpace(firstRoute, lastRoute, toDay);
    }

    public void changePassengerAmountAndDay(Integer passengerAmount, Integer day, Integer routeNumber) {
        planeRepository.changePassengerAmountAndDay(routeNumber, day, passengerAmount);
    }
}
