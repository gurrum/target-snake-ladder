package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

import java.util.Objects;

/**
 * Created by ngurum on 9/1/17.
 */
public class Ladder extends MoveStrategy {

    int origPosition;
    int elevatePosition;
    boolean isOccupied = false;


    public Ladder(int origPosition, int elevatePosition) {
        super(origPosition, ActionType.L);
        this.origPosition = origPosition;
        this.elevatePosition = elevatePosition;
    }

    @Override
    public boolean terminalAction(Player player) {

        if (!isOccupied) {
            player.getPath().push(new Integer(this.elevatePosition));
            isOccupied = true;
            return true;
        }

        return false;
    }

    public int getOrigPosition() {
        return origPosition;
    }

    public void setOrigPosition(int origPosition) {
        this.origPosition = origPosition;
    }

    public int getElevatePosition() {
        return elevatePosition;
    }

    public void setElevatePosition(int elevatePosition) {
        this.elevatePosition = elevatePosition;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ladder)) return false;
        Ladder ladder = (Ladder) o;
        return origPosition == ladder.origPosition &&
                elevatePosition == ladder.elevatePosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origPosition, elevatePosition);
    }
}
