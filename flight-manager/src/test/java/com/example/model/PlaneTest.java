package com.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void happyPath_getAvailableSpace() {
        // given
        Plane plane = new Plane(0,1,1);
        plane.changeMaxPassengersAndDay(10,5);
        // when
        Integer result = plane.getAvailableSpace(10);
        //then
        assertEquals(55,result);
    }

    @Test
    void happyPath_largeHistoryGetAvailableSpace() {
        // given
        Plane plane = new Plane(0,5,1);
        plane.changeMaxPassengersAndDay(10,3);
        plane.changeMaxPassengersAndDay(5,5);
        // when
        Integer result = plane.getAvailableSpace(10);
        //then
        assertEquals(60,result);
    }
}