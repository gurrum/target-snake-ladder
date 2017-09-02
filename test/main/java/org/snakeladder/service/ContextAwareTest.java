package org.snakeladder.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.strategy.MoveStrategy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.mockito.BDDMockito.given;

/**
 * Created by ngurum on 9/2/17.
 */

@RunWith(SpringRunner.class)
public class ContextAwareTest {

    ContextAware contextAware;
    MoveStrategy moveStrategy = Mockito.mock(MoveStrategy.class);
    int noOfStrategies = 7;

    @Before
    public void init(){

        contextAware = new ContextAware();

        contextAware.buildBoard(64);
        contextAware.buildPlayers(2);

        while(noOfStrategies-- >= 0)
            contextAware.addMoveStrategy(moveStrategy);

    }

    //Success Test
    @Test
    public void testPlayWithStrategies() {

        given(moveStrategy.terminalAction(contextAware.getPlayers()[1])).willReturn(true);
        contextAware.play(2);
        return;
    }

    @Test
    public void testPlayNoStategies(){

        for(Map.Entry<Integer,MoveStrategy> entry: contextAware.getMoveStrategies().entrySet()){
            entry.setValue(null);
        }

        given(moveStrategy.terminalAction(contextAware.getPlayers()[1])).willReturn(true);
        contextAware.play(6);
        return;
    }
}
