package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

/**
 * Created by ngurum on 9/1/17.
 */
public class Magic extends MoveStrategy {


    public Magic(int position) {
        super(position, ActionType.MA);
    }

    @Override
    public boolean terminalAction(Player player) {

        player.setMagic(!player.isMagic());
        return true;
    }
}
