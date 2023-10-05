package org.example;

import org.example.interfaces.StateInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

    public class Clock implements StateInterface {

        STATE current_state = STATE.DisplayTime;
        Scanner scanner = new Scanner(System.in);
        ClockImpl clockimpl = new ClockImpl();
        String userInput = "";
        boolean isRunning = true;
        boolean timeHasBeenSet = false;
        boolean dateHasBeenSet = false;


        enum STATE{
            DisplayTime,
            ChangeTime,
            DisplayDate,
            ChangeDate
        }


        @Override
        public void changeMode() {

            if (current_state == STATE.DisplayTime) {
                System.out.println("changeMode-Metoden: Current state: " + current_state);
                System.out.println("""
			                What would you like to do?
			                1. Change time
			                2. Display date
			                3. "q" for Quit
			                """);

                String input = scanner.nextLine().trim();

                switch(input) {
                    case "1":
                        current_state = STATE.ChangeTime;
                        readyToSet();
                        break;
                    case "2":
                        current_state = STATE.DisplayDate;
                        set();
                        break;
                    case "q":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }


            } else if (current_state == STATE.ChangeTime) {
                System.out.println("""
		            What would you like to do?
		            1. Display time
		            2. "q" for Quit
		            """);

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        current_state = STATE.DisplayTime;
                        set();
                        break;
                    case "q":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } else if (current_state == STATE.DisplayDate) {
                System.out.println("changeMode-Metoden: Current state: " + current_state);
                System.out.println("""
		            What would you like to do?
		            1. Display time
		            2. Change date
		            3. "q" for Quit
		            """);

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        current_state = STATE.DisplayTime;
                        set();
                        break;
                    case "2":
                        current_state = STATE.ChangeDate;
                        readyToSet();
                        break;
                    case "q":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }

            } else if(current_state == STATE.ChangeDate) {
                System.out.println("""
		            What would you like to do?
		            1. Display date
		            2. "q" for Quit
		            """);

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1":
                        current_state = STATE.DisplayDate;
                        set();
                        break;
                    case "q":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }

        }

        @Override
        public void set() {
            if(current_state == STATE.DisplayTime) {
                System.out.println("");
                System.out.println("Set-Metoden: Current state: " + current_state);

                if (!timeHasBeenSet) {
                    clockimpl.setTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
                }

                System.out.println("");
                System.out.println("The time is: " + clockimpl.getTime());
                changeMode();

            }

            else if (current_state == STATE.DisplayDate) {
                System.out.println("");
                System.out.println("Current state: " + current_state);

                if(!dateHasBeenSet){
                    clockimpl.setDate(LocalDate.now());
                }
                System.out.println("");
                System.out.println("The date is :" + clockimpl.getDate());
                changeMode();
            }
        }

        @Override
        public void readyToSet() {
            if(current_state == STATE.ChangeTime) {
                System.out.println("");
                System.out.println("ReadyToSet-Metoden: Current state: " + current_state);
                scanner = new Scanner(System.in);

                System.out.println("");
                System.out.println("What would you like the time to be? Please write it in HH:mm format, for example (e.g. 15:30) ");
                String input = scanner.nextLine();

                try {
                    LocalTime time = LocalTime.parse(input);
                    System.out.println("");
                    System.out.println("Inmatad tid: " + time);
                    System.out.println("ReadyToSet-Metoden: Current state: " + current_state);

                    clockimpl.setTime(time);
                    timeHasBeenSet = true;

                    changeMode();

                } catch (DateTimeParseException e) {
                    System.out.println("");
                    System.out.println("Ogiltigt tidsformat!");
                }


            } else if (current_state == STATE.ChangeDate) {
                System.out.println("");
                System.out.println("What date would you like it to be? Please write it in yyyy-MM-dd format, for example (e.g. 2023-10-04):");
                String input = scanner.nextLine();

                try {
                    LocalDate date = LocalDate.parse(input);
                    System.out.println("");
                    System.out.println("Inmatat datum: " + date);

                    clockimpl.setDate(date);
                    dateHasBeenSet = true;

                    changeMode();

                } catch (DateTimeParseException e) {
                    System.out.println("");
                    System.out.println("Ogiltigt datumformat!");
                }

            }

        }


        public void mainLoop() {
            while(isRunning) {
                System.out.println("");
                System.out.println("Current state: " + current_state);

                if(current_state == STATE.DisplayTime) {
                    set();
                }

                if(!isRunning) {
                    break;
                }

                userInput = scanner.nextLine().trim();
                switch (userInput) {
                    case "changeMode":
                        changeMode();
                        break;
                    case "set":
                        set();
                        break;
                    case "readyToSet":
                        readyToSet();
                        break;
                    case "q":
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }



}
