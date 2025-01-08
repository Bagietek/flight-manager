package com.example.model;

import com.example.controller.InstructionController;
import com.example.exception.InstructionException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialInstruction {
    private static final Integer MAX_QUERY_AMOUNT = 10000000;
    private static final Integer MAX_ROUTE_AMOUNT = 10000000;

    @Getter
    private final Integer queryAmount;

    @Getter
    private final Integer routeAmount;

    public InitialInstruction(String userInput, Integer inputQueriesAmount) {
        List<String> parts = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        Integer queryAmount = InstructionController.changeToInt(parts.getLast());
        Integer routeAmount = InstructionController.changeToInt(parts.getFirst());
        inputValidation(queryAmount, routeAmount, inputQueriesAmount);
        this.queryAmount = queryAmount;
        this.routeAmount = routeAmount;
    }

    private void inputValidation(Integer queryAmount, Integer routeAmount, Integer inputQueriesAmount) {
        if (queryAmount <= 0 || queryAmount > MAX_QUERY_AMOUNT) {
            throw new InstructionException("Invalid instruction, invalid query amount");
        }
        if (routeAmount <= 0 || routeAmount > MAX_ROUTE_AMOUNT) {
            throw new InstructionException("Invalid instruction, invalid route amount");
        }
        if (inputQueriesAmount.equals(queryAmount - 2)) {
            throw new InstructionException("Invalid instructions, query amount doesnt match");
        }
    }

}
