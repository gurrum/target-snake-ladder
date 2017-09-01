package org.snakeladder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

    @Autowired
    ContextAwareService contextAwareService;

    public void loadSnakeAndLadderBoard() {

        contextAwareService.initiatePlayBoard();

    }

    public void startGame() {

        while (contextAwareService.play()) ;
    }
}
