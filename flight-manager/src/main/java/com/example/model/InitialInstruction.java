package com.example.model;

import com.example.exception.InstructionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitialInstruction{
    private final Integer MAX_QUERY_AMOUNT = 10000000;
    private final Integer MAX_ROUTE_AMOUNT = 10000000;
    Integer queryAmount;
    Integer routeAmount;

    public InitialInstruction(String userInput, Integer inputQueriesAmount) {
        inputValidation(userInput, inputQueriesAmount);
    }

    private void inputValidation(String userInput, Integer inputQueriesAmount){
        List<String> parts = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        Integer queryAmount = changeToInt(parts.getLast());
        Integer routeAmount = changeToInt(parts.getFirst());

        if(queryAmount <= 0 || queryAmount > MAX_QUERY_AMOUNT){
            throw new InstructionException("Invalid instruction, invalid query amount");
        }

        if(routeAmount <= 0 || routeAmount > MAX_ROUTE_AMOUNT){
            throw new InstructionException("Invalid instruction, invalid route amount");
        }

        if(!inputQueriesAmount.equals(queryAmount-2)){
            throw new InstructionException("Invalid instructions, query amount doesnt match");
        }
        this.queryAmount = queryAmount;
        this.routeAmount = routeAmount;
    }

    private Integer changeToInt(String str){
        try{
            return Integer.parseInt(str);
        }catch (NumberFormatException e){
            throw new InstructionException("Invalid instruction " + e.getMessage());
        }
    }
}
