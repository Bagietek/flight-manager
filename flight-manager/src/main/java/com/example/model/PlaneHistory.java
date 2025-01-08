package com.example.model;

import lombok.Getter;

import java.util.LinkedList;

public class PlaneHistory {
    @Getter
    private final LinkedList<Plane> history;

    public PlaneHistory() {
        history = new LinkedList<>();
    }

    public void addToHistory(Plane plane) {
        history.add(plane);
    }
}
