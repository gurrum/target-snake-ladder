package org.snakeladder.domain.strategy;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.Player;

/**
 * Created by ngurum on 9/1/17.
 */
public class PitStop extends MoveStrategy {

    int energyUnit;

    public PitStop(int position, int energyUnit) {
        super(position, ActionType.P);
        this.energyUnit = energyUnit;
    }

    @Override
    public boolean terminalAction(Player player) {

        player.setEnergyLevel(player.getEnergyLevel() + this.energyUnit);
        return false;
    }
}
