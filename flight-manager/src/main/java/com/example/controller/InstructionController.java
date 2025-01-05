package com.example.controller;


import com.example.exception.InstructionException;
import com.example.model.InitialInstruction;

import java.util.List;

public class InstructionController {
    Integer requestCounter;
    PlaneController planeController;

    public InstructionController(PlaneController planeController) {
        this.requestCounter = 0;
    }

    public String inputRead(List<String> input){
        InitialInstruction instruction = new InitialInstruction(input.getFirst(),input.size());
        input.removeFirst();
        secondInstruction(input.getFirst());
        input.removeFirst();



        /*char firstChar = input.charAt(0);
        switch (firstChar) {
            case 'Q':
                break;
            case 'C':
                break;
            case 'A':
                break;
            case 'P':
                break;
            default:
                requestCounter++;
                if (requestCounter.equals(1)){
                    initialInstruction(input);
                    break;
                }
                if (requestCounter.equals(2)) {
                    secondInstruction(input);
                    break;
                }
                throw new InstructionException("Unknown instruction");
        }*/

        return "Completed";
    }

    private void initialInstruction(String input, Integer inputQueriesAmount){

    }

    private void secondInstruction(String input){

    }



}
