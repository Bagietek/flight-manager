package com.example.repository;

import com.example.exception.PlaneException;
import com.example.model.Plane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneRepositoryImplTest {
    PlaneRepository planeRepository;
    @BeforeEach
    public void initialize(){
        planeRepository = new PlaneRepositoryImpl();
    }

    @Test
    public void happyPath_shouldAddPlane(){
        // given
        Integer planeRoute = 1, day = 1, maxPassengers = 10;
        // when
        planeRepository.addPlane(planeRoute,day,maxPassengers);
        // then
        Plane plane = planeRepository.getPlane(planeRoute);
        assertEquals(maxPassengers, plane.getMaxPassengers());
        assertEquals(day,plane.getDay());
        assertEquals(planeRoute,plane.getPlaneRoute());
    }

    @Test
    public void happyPath_shouldCancelPlane(){
        // given
        Integer planeRoute = 1, day = 1, maxPassengers = 10;
        planeRepository.addPlane(planeRoute,day,maxPassengers);
        // when
        planeRepository.cancelPlane(planeRoute,day);
        // then
        assertNull(planeRepository.getPlane(planeRoute));
    }

    @Test
    public void happyPath_shouldChangePassengersAndDay(){
        // given
        Integer planeRoute = 1, day = 1, maxPassengers = 10;
        Integer newMaxPassengers = 5, newDay = 5;
        planeRepository.addPlane(planeRoute,day,maxPassengers);
        // when
        planeRepository.changePassengerAmountAndDay(planeRoute,newDay,newMaxPassengers);
        // then
        Plane plane = planeRepository.getPlane(planeRoute);
        assertEquals(newMaxPassengers, plane.getMaxPassengers());
        assertEquals(newDay, plane.getDay());
    }

    @Test
    public void unhappyPath_shouldThrowPlaneException(){
        // given
        Integer planeRoute = 1, day = 1, maxPassengers = 10;
        planeRepository.addPlane(planeRoute, day, maxPassengers);
        // when && then
        assertThrows(PlaneException.class, () -> planeRepository.cancelPlane(2,10));
        assertThrows(PlaneException.class, () -> planeRepository.changePassengerAmountAndDay(5,10,20));
        assertThrows(PlaneException.class, () -> planeRepository.addPlane(1,10, 15));
    }

}