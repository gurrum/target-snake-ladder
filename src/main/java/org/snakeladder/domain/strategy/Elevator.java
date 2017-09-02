package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ngurum on 9/1/17.
 */
public class Elevator extends MoveStrategy {

    @Autowired
    ContextAware contextAware;

    public Elevator(int origPosition) {
        super(origPosition, ActionType.E);
    }

    @Override
    public boolean terminalAction(Player player) {

        int rowLenght = new Double(Math.sqrt(contextAware.getBoardSize())).intValue();

        int move = rowLenght * player.getLastMove();
        Integer nextMove = player.isMagic() ? new Integer(this.getPosition() - move) : new Integer(this.getPosition() + move);

        if (nextMove <= contextAware.getBoardSize()) {
            player.getPath().push(nextMove);
            return true;
        }
        return false;
    }

    public void setContextAware(ContextAware contextAware) {
        this.contextAware = contextAware;
    }


}
