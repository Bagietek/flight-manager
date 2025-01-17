package com.example.controller;

import com.example.repository.PlaneRepository;
import com.example.repository.PlaneRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class PlaneControllerTest {
    PlaneController planeController;
    PlaneRepository planeRepository;

    @BeforeEach
    public void generatePlaneController() {
        planeRepository = mock(PlaneRepositoryImpl.class);
        planeController = new PlaneController(planeRepository);
    }

    @Test
    public void happyPath_shouldTryToAddPlane() {
        // given
        Integer planeRoute = 1;
        Integer day = 1;
        Integer maxPassengers = 10;
        // when
        planeController.addPlane(planeRoute, day, maxPassengers);
        // then
        verify(planeRepository, times(1)).addPlane(planeRoute, day, maxPassengers);
    }

}