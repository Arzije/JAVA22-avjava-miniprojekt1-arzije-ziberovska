package org.example.logic;

import org.example.view.UserInterface;
import org.example.model.ClockDate;
import org.example.model.ClockTime;
import org.example.interfaces.StateActions;
import org.example.states.STATE;
import org.example.states.StateManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Clock implements StateActions {

    private ClockTime time = new ClockTime();
    private ClockDate date = new ClockDate();
    private boolean timeHasBeenSet = false;
    private boolean dateHasBeenSet = false;

    private UserInterface ui = new UserInterface();
    private StateManager stateManager = new StateManager();

    @Override
    public void changeMode() {
        STATE currentState = stateManager.getCurrentState();
        if (currentState == STATE.DisplayTime) {
            stateManager.changeState(STATE.DisplayDate);
        } else if (currentState == STATE.DisplayDate) {
            stateManager.changeState(STATE.DisplayTime);
        }
    }

    public void showOptions() {
        STATE currentState = stateManager.getCurrentState();
        ui.displayMenu(currentState);

        String input = ui.getUserInput();

        switch (currentState) {
            case DisplayTime:
                handleDisplayTimeActions(input);
                break;
            case ChangeTime:
                handleChangeTimeActions(input);
                break;
            case DisplayDate:
                handleDisplayDateActions(input);
                break;
            case ChangeDate:
                handleChangeDateActions(input);
                break;
        }
    }


    private void handleDisplayTimeActions(String input) {
        switch (input) {
            case "1":
                set();
                break;
            case "2":
                stateManager.changeState(STATE.ChangeTime);
                break;
            case "3":
                stateManager.changeState(STATE.DisplayDate);
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }

    private void handleChangeTimeActions(String input) {
        switch (input) {
            case "1":
                readyToSet();
                break;
            case "2":
                stateManager.changeState(STATE.DisplayTime);
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }


    private void handleDisplayDateActions(String input) {
        switch (input) {
            case "1":
                set();
                break;
            case "2":
                stateManager.changeState(STATE.ChangeDate);
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }

    private void handleChangeDateActions(String input) {
        switch (input) {
            case "1":
                readyToSet();
                break;
            case "2":
                stateManager.changeState(STATE.DisplayDate);
                set();
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }

    @Override
    public void set() {
        STATE currentState = stateManager.getCurrentState();

        switch (currentState) {
            case DisplayTime:
                if (!timeHasBeenSet) {
                    time.setTime(LocalTime.now().truncatedTo(ChronoUnit.MINUTES));
                }
                ui.showTime(time.getTime());
                break;

            case DisplayDate:
                if (!dateHasBeenSet) {
                    date.setDate(LocalDate.now());
                }
                ui.showDate(date.getDate());
                break;

            case ChangeTime:
                readyToSet();
                break;

            case ChangeDate:
                readyToSet();
                break;
        }
        showOptions();
    }


    @Override
    public void readyToSet() {
        STATE currentState = stateManager.getCurrentState();

        switch (currentState) {
            case ChangeTime:
                ui.displayPrompt("What would you like the time to be? Please write it in HH:mm format, for example (e.g. 15:30):");
                String inputTime = ui.getUserInput();

                try {
                    LocalTime newTime = LocalTime.parse(inputTime);
                    time.setTime(newTime);
                    timeHasBeenSet = true;
                    ui.displayMessage("Time successfully set!");
                    stateManager.changeState(STATE.DisplayTime);
                } catch (DateTimeParseException e) {
                    ui.displayMessage("Invalid time format. Please use HH:mm format.");
                }
                break;

            case ChangeDate:
                ui.displayPrompt("What date would you like it to be? Please write it in yyyy-MM-dd format, for example (e.g. 2023-10-04):");
                String inputDate = ui.getUserInput();

                try {
                    LocalDate newDate = LocalDate.parse(inputDate);
                    date.setDate(newDate);
                    dateHasBeenSet = true;
                    ui.displayMessage("Date successfully set!");
                    stateManager.changeState(STATE.DisplayDate);
                } catch (DateTimeParseException e) {
                    ui.displayMessage("Invalid date format. Please use yyyy-MM-dd format.");
                }
                break;
        }

        showOptions();
    }


    public void start() {
        while (true) {
            showOptions();
        }
    }


}
