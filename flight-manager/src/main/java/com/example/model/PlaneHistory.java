package com.example.model;

import lombok.Getter;

import java.util.LinkedList;

public class PlaneHistory {
    private LinkedList<Plane> history;

    public PlaneHistory() {
        history = new LinkedList<>();
    }

    public void addHistory(Plane plane){
        history.add(plane);
    }

    public Integer getAvailableHistoricSpace(Integer toDay, Integer currentPlaneDay) {
        Integer endingDay;
        if (toDay > currentPlaneDay) {
            endingDay = currentPlaneDay;
        }else {
            endingDay = toDay;
        }
        return history.stream()
                .mapToInt(p -> p.getAvailableSpace(endingDay))
                .sum();
    }
}
