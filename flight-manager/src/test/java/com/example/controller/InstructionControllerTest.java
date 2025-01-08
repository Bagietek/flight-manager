package com.example.controller;

import com.example.exception.InstructionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class InstructionControllerTest {
    InstructionController instructionController;
    PlaneController planeController;

    @BeforeEach
    public void getMockedController() {
        planeController = mock(PlaneController.class);
        instructionController = new InstructionController(planeController);
    }

    @Test
    public void unhappyPath_queryParse_shouldThrowInstructionException() {
        // given
        final List<String> input = getInvalidInstructions();
        // when && then
        assertThrows(InstructionException.class, () -> instructionController.instructionHandler(input));
    }

    @Test
    public void happyPath_queryParse_shouldReturnAvailableSpace() {
        // given
        final Integer availableSpace = 10;
        final List<String> input = getValidInstructions();
        when(planeController.getAvailableSpace(1, 5, 2)).thenReturn(availableSpace);
        when(planeController.getAvailableSpace(2, 3, 2)).thenReturn(availableSpace);
        when(planeController.getAvailableSpace(2, 4, 4)).thenReturn(availableSpace);
        when(planeController.getAvailableSpace(1, 5, 8)).thenReturn(availableSpace);
        // when
        String output = instructionController.instructionHandler(input);
        // then
        verifyOutputData(output, availableSpace.toString());
    }

    @Test
    public void happyPath_queryParse_shouldInvokeCancelPlane() {
        // given
        final List<String> input = getValidInstructions();
        // when
        instructionController.instructionHandler(input);
        // then
        verify(planeController, times(1)).cancelPlane(anyInt(), anyInt());
    }

    @Test
    public void happyPath_queryParse_shouldInvokeAddPlane() {
        // given
        final List<String> input = getValidInstructions();
        // when
        instructionController.instructionHandler(input);
        // then
        // planes in command and initialization
        verify(planeController, times(6)).addPlane(anyInt(), anyInt(), anyInt());
    }

    @Test
    public void happyPath_queryParse_shouldInvokeChangePassengersAndDay() {
        // given
        final List<String> input = getValidInstructions();
        // when
        instructionController.instructionHandler(input);
        // then
        verify(planeController, times(1)).changePassengerAmountAndDay(anyInt(), anyInt(), anyInt());
    }

    private List<String> getInvalidInstructions() {
        List<String> data = new ArrayList<>();
        data.add("test");
        data.add("1 2 3 2 4");
        data.add("Q 1 5 2");
        return data;
    }

    private List<String> getValidInstructions() {
        List<String> data = new ArrayList<>();
        data.add("5 7");
        data.add("1 2 3 2 4");
        data.add("Q 1 5 2");
        data.add("Q 2 3 2");
        data.add("C 2 3");
        data.add("P 3 5 3");
        data.add("Q 2 4 4");
        data.add("A 2 5 6");
        data.add("Q 1 5 8");
        return data;
    }

    private void verifyOutputData(String output, String availableSpace) {
        String correctOutput = availableSpace + "\n";
        correctOutput += correctOutput;
        correctOutput += correctOutput;
        assertEquals(output, correctOutput);
    }
}