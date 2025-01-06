package com.example.model;

import com.example.controller.InstructionController;
import com.example.exception.InstructionException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SecondInstruction {
    private final Integer MAX_PASSENGER_AMOUNT = 1000;
    @Getter
    List<Integer> amountOfPassengersOnRoute;
    public SecondInstruction(String userInput, Integer routeAmount) {
        List<String> parts = new ArrayList<>(Arrays.asList(userInput.split(" ")));

        if(parts.size() != routeAmount){
            throw new InstructionException("Invalid instruction, route amount does not match");
        }
        amountOfPassengersOnRoute = parts.stream()
                .map(InstructionController::changeToInt)
                .collect(Collectors.toList());
    }

}
