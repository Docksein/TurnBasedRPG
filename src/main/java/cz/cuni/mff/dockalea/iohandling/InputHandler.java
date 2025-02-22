package cz.cuni.mff.dockalea.iohandling;

import java.util.Scanner;

public class InputHandler {
    private Scanner input_scanner = new Scanner(System.in);

    public int getNextChoice(int actionChoices, String prompt){
        int choice = -1;

        System.out.println(prompt);
        while (choice < 1 || choice > actionChoices){
            try {
                choice = Integer.parseInt(input_scanner.next());
            } catch (NumberFormatException e) {
                choice = - 1;
                System.out.println("Please select a choice from 1 to " + actionChoices);
            }
        }

        return choice;
    }
}
