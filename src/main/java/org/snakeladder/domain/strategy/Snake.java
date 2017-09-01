package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

import java.util.Objects;

/**
 * Created by ngurum on 9/1/17.
 */
public class Snake extends MoveStrategy {

    private int origPosition;
    private int dropPosition;
    private int hungerLevel;

    public Snake(int origPosition, int dropPosition, int hungerLevel) {
        super(origPosition, ActionType.S);
        this.origPosition = origPosition;
        this.dropPosition = dropPosition;
        this.hungerLevel = hungerLevel;
    }

    @Override
    public boolean terminalAction(Player player) {

        if (hungerLevel > 0) {
            player.getPath().push(new Integer(this.dropPosition));
            hungerLevel--;
        }

        return false;
    }

    public int getOrigPosition() {
        return origPosition;
    }

    public void setOrigPosition(int origPosition) {
        this.origPosition = origPosition;
    }

    public int getDropPosition() {
        return dropPosition;
    }

    public void setDropPosition(int dropPosition) {
        this.dropPosition = dropPosition;
    }

    public int getHungerLevel() {
        return hungerLevel;
    }

    public void setHungerLevel(int hungerLevel) {
        this.hungerLevel = hungerLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Snake)) return false;
        Snake snake = (Snake) o;
        return origPosition == snake.origPosition &&
                dropPosition == snake.dropPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(origPosition, dropPosition);
    }
}
