package org.snakeladder.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.strategy.MoveStrategy;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ngurum on 9/2/17.
 */

@RunWith(SpringRunner.class)
public class ContextAwareTest {

    @Test
    public void testPlay() {

        MoveStrategy moveStrategy = Mockito.mock(MoveStrategy.class);
        ContextAware contextAware = new ContextAware();
        contextAware.buildBoard(64);
        contextAware.buildPlayers(2);
        contextAware.addMoveStrategy(moveStrategy);
        contextAware.addMoveStrategy(moveStrategy);
        contextAware.addMoveStrategy(moveStrategy);
        contextAware.addMoveStrategy(moveStrategy);
        contextAware.addMoveStrategy(moveStrategy);
        contextAware.play(2);
        return;
    }
}
