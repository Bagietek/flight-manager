package com.example.model;

import lombok.Getter;

import java.util.LinkedList;

public class PlaneHistory {
    @Getter
    LinkedList<Plane> history;
    public PlaneHistory() {
        history = new LinkedList<>();
    }

    public void addHistory(Plane plane){
        history.add(plane);
    }
}
