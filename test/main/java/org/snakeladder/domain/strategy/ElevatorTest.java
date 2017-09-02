package org.snakeladder.domain.strategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.Player;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * Created by ngurum on 9/2/17.
 */

@RunWith(SpringRunner.class)
public class ElevatorTest {

    private Elevator elevator = new Elevator(10);

    Player player;

    @MockBean
    private ContextAware contextAware;

    @Before
    public void init(){
        player = new Player(1,1,31);
        player.getPath().push(new Integer(5));
        elevator.setContextAware(contextAware);

     }

    @Test
    public void testTerminalAction(){

        given(contextAware.getBoardSize()).willReturn(new Integer(64));
        elevator.terminalAction(player);
        assertEquals(player.getPath().peek().intValue(),10);
        return;

    }
}
