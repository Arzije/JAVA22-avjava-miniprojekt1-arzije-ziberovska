package org.example;

import org.example.logic.Clock;
import org.example.model.ClockDate;
import org.example.model.ClockTime;
import org.example.states.StateManager;
import org.example.view.UserInterface;

public class Main {
    public static void main(String[] args) {

        UserInterface ui = new UserInterface();
        StateManager stateManager = new StateManager();
        ClockTime time = new ClockTime();
        ClockDate date = new ClockDate();
        Clock clock = new Clock(ui, stateManager, time, date);
        clock.start();

    }
}