package org.example.logic;

import org.example.states.STATE;
import org.example.states.StateManager;
import org.example.view.UserInterface;

public class ActionHandler {

    private UserInterface ui;
    private StateManager stateManager;
    private Clock clock;

    public ActionHandler(UserInterface ui, StateManager stateManager, Clock clock) {
        this.ui = ui;
        this.stateManager = stateManager;
        this.clock = clock;
    }

    public void handleDisplayTimeActions(String input) {
        switch (input) {
            case "1":
                clock.set();
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

    public void handleChangeTimeActions(String input) {
        switch (input) {
            case "1":
                clock.readyToSet();
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

    public void handleDisplayDateActions(String input) {
        switch (input) {
            case "1":
                clock.set();
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

    public void handleChangeDateActions(String input) {
        switch (input) {
            case "1":
                clock.readyToSet();
                break;
            case "2":
                stateManager.changeState(STATE.DisplayDate);
                clock.set();
                break;
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }
}
