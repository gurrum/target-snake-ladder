package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

import java.util.Objects;

/**
 * Created by ngurum on 9/1/17.
 */
public abstract class MoveStrategy {

    private int position;

    private ActionType actionType;

    public abstract boolean terminalAction(Player player);


    public MoveStrategy(int position, ActionType actionType) {
        this.position = position;
        this.actionType = actionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoveStrategy)) return false;
        MoveStrategy that = (MoveStrategy) o;
        return position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    public int getPosition() {
        return position;
    }

    public ActionType getActionType() {
        return actionType;
    }
}
