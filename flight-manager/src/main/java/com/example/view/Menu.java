package com.example.view;

import com.example.controller.InstructionController;
import com.example.controller.PlaneController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Menu {

    public Menu() {
    }

    public void showMenu(){
        PlaneController planeController = new PlaneController();
        InstructionController instructionController = new InstructionController(planeController);
        List<String> inputLines = new ArrayList<>();


        while(true){
            Scanner userInput = new Scanner(System.in);
            System.out.println("Please input flight data or q to quit, ctrl + d to end");
            /*String line = userInput.nextLine();
            if (line.equals("q")) {
                System.out.println("Exiting the program...");
                System.exit(0);
            }
            System.out.println(instructionController.inputRead(line));*/
            while (userInput.hasNextLine()){
                String line = userInput.nextLine();
                if (line.equals("q")) {
                    System.out.println("Exiting the program...");
                    userInput.close();
                    System.exit(0);
                }
                inputLines.add(line);
            }
            for (String line : inputLines){
                System.out.println(line);
            }
            break;
        }
        System.out.println(instructionController.inputRead(inputLines));
    }
}
