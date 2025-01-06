package com.example.controller;


import com.example.exception.InstructionException;
import com.example.model.InitialInstruction;
import com.example.model.SecondInstruction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class InstructionController {
    PlaneController planeController;
    private final String EMPTY_STRING = "";

    public InstructionController(PlaneController planeController) {
        this.planeController = planeController;
    }

    public String instructionHandler(List<String> input){
        StringBuilder stringBuilder = new StringBuilder();
        InitialInstruction initialInstruction = new InitialInstruction(input.getFirst(),input.size() - 2);
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

    private void initializePlanes(SecondInstruction secondInstruction){
        IntStream.range(0, secondInstruction.getAmountOfPassengersOnRoute().size())
                .forEach(i -> planeController.addPlane(i, 0, secondInstruction.getAmountOfPassengersOnRoute().get(i)));
    }

    private String queryParseAndExecute(String query){
        List<String> queryVariables = Arrays.stream(query.split(" ")).toList();
        Integer route, startRoute, endRoute, toDay, day, passengerAmount;
        /*
            Q i j t
            P i p t
            A i p t
            C i t
         */
        switch (queryVariables.getFirst()) {
            case "Q":
                startRoute = changeToInt(queryVariables.get(1));
                endRoute = changeToInt(queryVariables.get(2));
                toDay = changeToInt(queryVariables.getLast());
                return planeController.getAvailableSpace(startRoute,endRoute,toDay).toString() + "\n";
            case "C":
                route = changeToInt(queryVariables.get(1));
                day = changeToInt(queryVariables.getLast());
                planeController.cancelPlane(route - 1,day);
                return EMPTY_STRING;
            case "A":
                route = changeToInt(queryVariables.get(1));
                passengerAmount = changeToInt(queryVariables.get(2));
                day = changeToInt(queryVariables.getLast());
                planeController.addPlane(route - 1,day,passengerAmount);
                return EMPTY_STRING;
            case "P":
                route = changeToInt(queryVariables.get(1));
                passengerAmount = changeToInt(queryVariables.get(2));
                day = changeToInt(queryVariables.getLast());
                planeController.changePassengerAmountAndDay(passengerAmount, day, route - 1);
                return EMPTY_STRING;
            default:
                throw new InstructionException("Unknown instruction");
        }
    }

    public static Integer changeToInt(String str){
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            throw new InstructionException("Invalid instruction " + e.getMessage());
        }
    }

}
