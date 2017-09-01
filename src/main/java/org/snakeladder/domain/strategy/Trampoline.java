package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ngurum on 9/1/17.
 */
public class Trampoline extends MoveStrategy {

    @Autowired
    ContextAware contextAware;


    public Trampoline(int position) {
        super(position, ActionType.T);
    }

    @Override
    public boolean terminalAction(Player player) {

        Integer nextMove = player.isMagic() ? new Integer(this.getPosition() - player.getLastMove()) : new Integer(this.getPosition() + player.getLastMove());

        if (nextMove <= contextAware.getBoardSize()) {
            player.getPath().push(nextMove);
            return true;
        }
        return false;
    }
}
