package org.snakeladder.domain;

import org.snakeladder.domain.strategy.MoveStrategy;

import java.util.Objects;
import java.util.Stack;

/**
 * Created by ngurum on 9/1/17.
 */
public class Player {

    private int playerNumber;
    private int normalPosition = 1;
    private int energyLevel;
    private boolean magic;
    private Stack<Integer> path = new Stack<>();
    private MoveStrategy moveStrategy;

    public Player(int playerNumber, int position, int boardsize) {
        this.playerNumber = playerNumber;
        this.getPath().push(new Integer(position));
        this.energyLevel = boardsize * boardsize / 3;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public boolean isMagic() {
        return magic;
    }

    public void setMagic(boolean magic) {
        this.magic = magic;
    }

    public Stack<Integer> getPath() {
        return path;
    }

    public void setPath(Stack<Integer> path) {
        this.path = path;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(MoveStrategy moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public int getLastMove() {
        if (!path.empty()) {
            return getNormalPosition() - getPreviousPosition();
        }
        return 0;
    }

    public int getPreviousPosition() {

        Integer latestPostion = this.path.pop();
        int previousPosition = this.path.peek();

        this.path.push(latestPostion);
        return previousPosition;
    }

    public int getNormalPosition() {
        return normalPosition;
    }

    public void setNormalPosition(int normalPosition) {
        this.normalPosition = normalPosition;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return playerNumber == player.playerNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerNumber);
    }
}
