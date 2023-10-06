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

    private ClockTime time;
    private ClockDate date;
    private boolean timeHasBeenSet = false;
    private boolean dateHasBeenSet = false;
    private UserInterface ui;
    private StateManager stateManager;
    private ActionHandler actionHandler;


    public Clock(UserInterface ui, StateManager stateManager, ClockTime time, ClockDate date) {
        this.ui = ui;
        this.stateManager = stateManager;
        this.time = time;
        this.date = date;
        this.actionHandler = new ActionHandler(ui, stateManager, this);
    }

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
                actionHandler.handleDisplayTimeActions(input);
                break;
            case ChangeTime:
                actionHandler.handleChangeTimeActions(input);
                break;
            case DisplayDate:
                actionHandler.handleDisplayDateActions(input);
                break;
            case ChangeDate:
                actionHandler.handleChangeDateActions(input);
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

            case ChangeTime, ChangeDate:
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
