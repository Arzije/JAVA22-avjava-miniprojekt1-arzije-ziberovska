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
//        clock.set();
        switch (input) {
//            case "1":
//                clock.set();
//                break;
            case "1":
                stateManager.changeState(STATE.ChangeTime);
                clock.readyToSet();
                break;
            case "2":
                clock.changeMode();
                break;
                ////
            case "3":

                if (stateManager.getCurrentState() == STATE.DisplayTime){
                    ui.showError("Invalid choice");
                    clock.showOptions();
                }
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }

//    public void handleChangeTimeActions(String input) {
//        switch (input) {
//            case "1":
//                clock.readyToSet();
//                break;
//            case "2":
//                stateManager.changeState(STATE.DisplayTime);
//                break;
//                //
//            case "3":
//                if (stateManager.getCurrentState() == STATE.ChangeTime) {
//                    ui.showError("Invalid choice");
//                    clock.showOptions();
//                }
//                //
//            case "4":
//                if (stateManager.getCurrentState() == STATE.ChangeTime) {
//                    ui.showError("Invalid choice");
//                    clock.showOptions();
//                }
//            case "q":
//                System.exit(0);
//                break;
//            default:
//                ui.showError("Invalid choice.");
//                break;
//        }
//    }

    public void handleDisplayDateActions(String input) {
        switch (input) {
//            case "1":
//                clock.set();
//                break;
            case "1":
                stateManager.changeState(STATE.ChangeDate);
                clock.readyToSet();
                break;
            case "2":
                clock.changeMode();
                break;
                //
            case "3":
                if (stateManager.getCurrentState() == STATE.DisplayDate) {
                    ui.showError("Invalid choice");
                    clock.showOptions();
                }
            case "q":
                System.exit(0);
                break;
            default:
                ui.showError("Invalid choice.");
                break;
        }
    }

//    public void handleChangeDateActions(String input) {
//        switch (input) {
//            case "1":
//                clock.readyToSet();
//                break;
//            case "2":
//                stateManager.changeState(STATE.DisplayDate);
//                break;
//                //
//            case "3":
//                if (stateManager.getCurrentState() == STATE.ChangeTime) {
//                    ui.showError("Invalid choice");
//                    clock.showOptions();
//                }
//            case "q":
//                System.exit(0);
//                break;
//            default:
//                ui.showError("Invalid choice.");
//                break;
//        }
//    }
}
