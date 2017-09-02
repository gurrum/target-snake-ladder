package org.snakeladder.domain;

import org.snakeladder.constants.ActionType;
import org.snakeladder.domain.strategy.Ladder;
import org.snakeladder.domain.strategy.MoveStrategy;
import org.snakeladder.domain.strategy.Snake;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ngurum on 9/1/17.
 */

@Component
public class ContextAware {

    private Map<Integer, MoveStrategy> moveStrategies;
    private Player[] players;
    private int currentPlayerNo = 1;
    private boolean gameOver = false;
    private int boardSize;


    public ContextAware buildBoard(int size) {
        this.boardSize = size;
        return this;
    }

    public ContextAware buildPlayers(int numberOfPlayers) {

        if (players == null) {
            players = new Player[numberOfPlayers + 1];

            for (int i = 1; i <= numberOfPlayers; i++) {
                players[i] = new Player(i, 1, boardSize);
            }
        }

        return this;
    }


    public ContextAware addMoveStrategy(MoveStrategy moveStrategy) {

        if (moveStrategies == null) {
            moveStrategies = new HashMap<>();
        }

        moveStrategies.put(moveStrategy.getPosition(), moveStrategy);
        return this;

    }

    public ContextAware play(int dice) {

        Player currentPlayer = players[currentPlayerNo];

        applyMagic(currentPlayer);
        currentPlayer.setEnergyLevel(currentPlayer.getEnergyLevel() - 1);

        if (currentPlayer.getEnergyLevel() <= 0) {
            currentPlayer.getPath().push(new Integer(1));
            this.currentPlayerNo = this.currentPlayerNo + 1 > players.length + 1 ? 1 : this.currentPlayerNo + 1;

            return this;

        }

        currentPlayer.setNormalPosition(currentPlayer.getPath().peek() + dice);

        MoveStrategy moveStrategy = moveStrategies.get(currentPlayer.getNormalPosition());


        if (moveStrategy == null) {

            currentPlayer.getPath().push(new Integer(currentPlayer.getNormalPosition()));
            applyMagic(currentPlayer);
            this.currentPlayerNo = this.currentPlayerNo + 1 > players.length + 1 ? 1 : this.currentPlayerNo + 1;
            return this;
        }
        currentPlayer.setMoveStrategy(moveStrategy);
        currentPlayer.getMoveStrategy().terminalAction(currentPlayer);

        System.out.println(currentPlayer.getPreviousPosition() + "->" + currentPlayer.getNormalPosition() + "->" +
                currentPlayer.getMoveStrategy().getActionType().name() + currentPlayer.getPath().peek());
        if (currentPlayer.getPath().peek() >= boardSize) {
            this.gameOver = true;
            return this;
        }

        applyMagic(currentPlayer);
        this.currentPlayerNo = this.currentPlayerNo + 1 >= players.length + 1 ? 1 : this.currentPlayerNo + 1;

        return this;

    }

    private void applyMagic(Player currentPlayer) {
        if(currentPlayer.isMagic()){
            for(Map.Entry<Integer,MoveStrategy> moveStrategyEntry:moveStrategies.entrySet()){

                if(moveStrategyEntry.getValue().getActionType().equals(ActionType.S)){

                    Snake snake = (Snake)moveStrategyEntry.getValue();
                    swap(snake);



                }

                if(moveStrategyEntry.getValue().getActionType().equals(ActionType.L)){
                    Ladder ladder = (Ladder)moveStrategyEntry.getValue();
                    swap(ladder);
                }

            }
        }
    }

    private void swap(Ladder ladder) {
        int temp = ladder.getElevatePosition();
        ladder.setElevatePosition(ladder.getOrigPosition());
        ladder.setOrigPosition(temp);
    }

    private void swap(Snake snake) {
        int temp = snake.getDropPosition();
        snake.setDropPosition(snake.getOrigPosition());
        snake.setOrigPosition(temp);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getCurrentPlayerNo() {
        return currentPlayerNo;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public Map<Integer, MoveStrategy> getMoveStrategies() {
        return moveStrategies;
    }

}
