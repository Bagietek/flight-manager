package com.example.model;

import com.example.controller.InstructionController;
import com.example.exception.InstructionException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SecondInstruction {
    private static final Integer MAX_PASSENGER_AMOUNT = 1000;

    @Getter
    private final List<Integer> amountOfPassengersOnRoute;

    public SecondInstruction(String userInput, Integer routeAmount) {
        String[] input = userInput.split(" ");
        if (input.length != routeAmount) {
            throw new InstructionException("Invalid instruction, route amount does not match");
        }
        amountOfPassengersOnRoute = Arrays.stream(input)
                .map(InstructionController::changeToInt)
                .filter(value -> {
                    if (value > MAX_PASSENGER_AMOUNT) {
                        throw new InstructionException("Invalid instruction, max passengers exceeded");
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
