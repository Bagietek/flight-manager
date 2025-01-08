package com.example.controller;


import com.example.exception.InstructionException;
import com.example.model.InitialInstruction;
import com.example.model.SecondInstruction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class InstructionController {
    private final PlaneController planeController;
    private static final String EMPTY_STRING = "";
    private static final String QUERY_STRING = "Q";
    private static final String CANCEL_STRING = "C";
    private static final String ADD_STRING = "A";
    private static final String CHANGE_STRING = "P";

    public InstructionController(PlaneController planeController) {
        this.planeController = planeController;
    }

    public String instructionHandler(List<String> input) {
        inputCheck(input);
        StringBuilder stringBuilder = new StringBuilder();
        InitialInstruction initialInstruction = new InitialInstruction(input.getFirst(), input.size() - 2);
        input.removeFirst();
        SecondInstruction secondInstruction = new SecondInstruction(input.getFirst(), initialInstruction.getRouteAmount());
        input.removeFirst();
        initializePlanes(secondInstruction);

        input.stream()
                .limit(initialInstruction.getQueryAmount())
                .map(this::queryParseAndExecute)
                .forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    private void inputCheck(List<String> input) {
        if (input.size() < 3) {
            throw new InstructionException("Invalid instructions");
        }
    }

    private void initializePlanes(SecondInstruction secondInstruction) {
        IntStream.range(0, secondInstruction.getAmountOfPassengersOnRoute().size())
                .forEach(i -> planeController.addPlane(i, 0, secondInstruction.getAmountOfPassengersOnRoute().get(i)));
    }

    private String queryParseAndExecute(String query) {
        List<String> queryVariables = Arrays.stream(query.split(" ")).toList();
        Integer route, startRoute, endRoute, toDay, day, passengerAmount;
        /*
            Q i j t
            P i p t
            A i p t
            C i t
         */
        switch (queryVariables.getFirst()) {
            case QUERY_STRING:
                startRoute = changeToInt(queryVariables.get(1));
                endRoute = changeToInt(queryVariables.get(2));
                toDay = changeToInt(queryVariables.getLast());
                return planeController.getAvailableSpace(startRoute, endRoute, toDay).toString() + "\n";
            case CANCEL_STRING:
                route = changeToInt(queryVariables.get(1));
                day = changeToInt(queryVariables.getLast());
                planeController.cancelPlane(route - 1, day);
                return EMPTY_STRING;
            case ADD_STRING:
                route = changeToInt(queryVariables.get(1));
                passengerAmount = changeToInt(queryVariables.get(2));
                day = changeToInt(queryVariables.getLast());
                planeController.addPlane(route - 1, day, passengerAmount);
                return EMPTY_STRING;
            case CHANGE_STRING:
                route = changeToInt(queryVariables.get(1));
                passengerAmount = changeToInt(queryVariables.get(2));
                day = changeToInt(queryVariables.getLast());
                planeController.changePassengerAmountAndDay(passengerAmount, day, route - 1);
                return EMPTY_STRING;
            default:
                throw new InstructionException("Unknown instruction");
        }
    }

    public static Integer changeToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new InstructionException("Invalid instruction\n" + e.getMessage());
        }
    }

}
