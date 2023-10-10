package org.example.view;

import org.example.states.STATE;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu(STATE state) {
        switch (state) {
            case DisplayTime:
                System.out.println("\tCurrent state: " + state);
                System.out.println("""
                            
                            What would you like to do?
                            
                            1. Change time
                            2. Display date
                            3. Change date
                            q. Quit
                        """);
                break;

//            case ChangeTime:
//                System.out.println("\tCurrent state: " + state);
//                System.out.println("""
//
//                            What would you like to do?
//
//                            1. Change time
//                            2. Go back to display time
//                            //
//                            3. Display date
//                            //
//                            4. Change date
//                            q. Quit
//                        """);
//                break;

            case DisplayDate:
                System.out.println("\tCurrent state: " + state);
                System.out.println("""
                            
                            What would you like to do?
                            
                            1. Change date
                            2. Display Time
                            3. Change time
                            q. Quit
                        """);
                break;

//            case ChangeDate:
//                System.out.println("\tCurrent state: " + state);
//                System.out.println("""
//
//                            What would you like to do?
//
//                            1. Change date
//                            2. Go back to display date
//                            //
//                            3. Display time
//                            //
//                            4. Change time
//                            q. Quit
//                        """);
//                break;

            default:
                System.out.println("Invalid state.");
        }
    }

    public String getUserInput() {
        return scanner.nextLine().trim();
    }

    public void showTime(LocalTime time) {
        System.out.println("\tThe time is : " + time);
    }

    public void showDate(LocalDate date) {
        System.out.println("\tThe date is : " + date);

    }

    public void showError(String error) {
        System.out.println("\tError : " + error);

    }

    public void displayPrompt(String message) {
        System.out.println(message);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
