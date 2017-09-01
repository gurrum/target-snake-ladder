package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

/**
 * Created by ngurum on 9/1/17.
 */
public class Memory extends MoveStrategy {

    public Memory(int position) {
        super(position, ActionType.ME);
    }

    @Override
    public boolean terminalAction(Player player) {

        int lastMove = player.getLastMove();

        while (lastMove-- > 0 && !player.getPath().empty())
            player.getPath().pop();

        return true;
    }
}
