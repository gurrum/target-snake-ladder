package org.snakeladder.service;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.ContextAware;
import org.snakeladder.domain.Player;
import org.snakeladder.domain.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * Created by ngurum on 9/1/17.
 */
@Service
public class ContextAwareService {

    @Autowired
    private ContextAware contextAware;

    Scanner scanner = new Scanner(System.in);


    public void initiatePlayBoard() throws Exception{

        int boardSize = scanner.nextInt();

        double sqrt = Math.sqrt(boardSize);
        int x = (int) sqrt;
        if(Math.pow(sqrt,2) != Math.pow(x,2))
            throw new Exception("Square board cannot be built");
        contextAware.buildBoard(boardSize);

        int noOfPlayers = scanner.nextInt();
        contextAware.buildPlayers(noOfPlayers);

        boolean done = false;

        while (!done) {

            switch (ActionType.valueOf(scanner.next())) {

                case S:
                    int origPosition = scanner.nextInt();
                    int dropPosition = scanner.nextInt();
                    int hungerLevel = scanner.nextInt();
                    contextAware.addMoveStrategy(new Snake(origPosition, dropPosition, hungerLevel));
                    break;

                case L:
                    int lowerPosition = scanner.nextInt();
                    int upperPosition = scanner.nextInt();

                    contextAware.addMoveStrategy(new Ladder(lowerPosition, upperPosition));
                    break;

                case ME:
                    int memorySquare = scanner.nextInt();

                    contextAware.addMoveStrategy(new Memory(memorySquare));
                    break;

                case MA:

                    int magicSquare = scanner.nextInt();
                    contextAware.addMoveStrategy(new Magic(magicSquare));
                    break;

                case T:

                    int trampolinePosition = scanner.nextInt();
                    contextAware.addMoveStrategy(new Trampoline(trampolinePosition));
                    break;

                case E:

                    int elevatorPosition = scanner.nextInt();
                    contextAware.addMoveStrategy(new Elevator(elevatorPosition));
                    break;

                case P:

                    int pitstopPosition = scanner.nextInt();
                    contextAware.addMoveStrategy(new PitStop(pitstopPosition, scanner.nextInt(pitstopPosition)));
                    printPlayerStates();
                    return;


            }


        }
    }


    public boolean play() {

        System.out.println("Its player " + contextAware.getCurrentPlayerNo() + "'s turn");

        if (contextAware.play(scanner.nextInt()).isGameOver()) {
            System.out.print("[" + contextAware.getCurrentPlayerNo() + ".W]");

            for (Player player : contextAware.getPlayers()) {
                if (player.getPlayerNumber() != contextAware.getCurrentPlayerNo())
                    System.out.print("[" + player.getPlayerNumber() + ":" + player.getPath().peek() + ":" + player.getEnergyLevel() + "]");
            }

            System.out.println();
            return false;
        } else {
            printPlayerStates();
            return true;
        }


    }

    private void printPlayerStates() {
        for (Player player : contextAware.getPlayers()) {
            if (player == null) continue;
            System.out.print("[" + player.getPlayerNumber() + ":" + player.getPath().peek() + ":" + player.getEnergyLevel() + "]");
        }
        System.out.println();
    }


}
