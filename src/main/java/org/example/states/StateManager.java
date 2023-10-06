package org.example.states;

public class StateManager {
    private STATE currentState = STATE.DisplayTime;

    public STATE getCurrentState() {
        return currentState;
    }

    public void changeState(STATE newState) {
        currentState = newState;
    }
}
