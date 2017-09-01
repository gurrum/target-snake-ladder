package org.snakeladder.constants;

/**
 * Created by ngurum on 9/1/17.
 */
public enum ActionType {

    S("SNAKE", 3), L("LADDER", 2), ME("MEMORY_SQUARE", 1), MA("MAMAGIC_SQUARE", 1), T("TRAMPOLINE", 1), E("ELEVATOR", 1), P("PITSTOP", 1);


    private String longForm;

    private int inputSize;

    ActionType(String shortForm, int inputSize) {
        this.longForm = shortForm;
        this.inputSize = inputSize;
    }

    public String getLongForm() {
        return longForm;
    }

    public int getInputSize() {
        return inputSize;
    }
}
