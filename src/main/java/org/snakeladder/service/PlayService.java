package org.snakeladder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayService {

    @Autowired
    ContextAwareService contextAwareService;

    public boolean loadSnakeAndLadderBoard() {

        try{
            contextAwareService.initiatePlayBoard();
            return true;
        }catch(Exception ex){
            System.out.println("Data entry error " + ex.getMessage());
            return false;
        }

    }

    public void startGame() {

        while (contextAwareService.play()) ;
    }
}
