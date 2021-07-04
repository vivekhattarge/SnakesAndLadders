package com.snakesAndLadders.service;

import com.snakesAndLadders.dto.Board;
import com.snakesAndLadders.dto.Ladder;
import com.snakesAndLadders.dto.Player;
import com.snakesAndLadders.dto.Snake;

import java.util.*;

public class SnakesAndLaddersService {

    private static final int DEFAULT_BOARD_SIZE = 100;
    private Board board;
    private int initialNumberOfPlayers;
    private boolean isDiceCrooked;
    private Queue<Player> players;

    public SnakesAndLaddersService(int boardSize) {
        this.board = new Board(boardSize);
        this.players = new LinkedList<>();
    }

    public SnakesAndLaddersService() {
        this(SnakesAndLaddersService.DEFAULT_BOARD_SIZE);
    }

    public void setDiceCrooked(boolean isDiceCrooked) {
        this.isDiceCrooked = isDiceCrooked;
    }

    public void setPlayers(List<Player> players) {
        this.players = new LinkedList<>();
        this.initialNumberOfPlayers = players.size();
        Map<String, Integer> playerPositions = new HashMap<>();
        for (Player player : players) {
            this.players.add(player);
            // every player will be at 0 position at first
            playerPositions.put(player.getId(), 0);
        }
        board.setPlayerPositions(playerPositions);
    }

    public void setSnakes(List<Snake> snakes) {
        board.setSnakes(snakes);
    }

    public void setLadders(List<Ladder> ladders) {
        board.setLadders(ladders);
    }

    private boolean isGameCompleted() {
        // Can use shouldGameContinueTillLastPlayer to change the logic of determining if game is completed (Optional requirements)
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < initialNumberOfPlayers;
    }

    private int getTotalValueAfterDiceRolls() {
        return DiceService.roll(this.isDiceCrooked ? true : false);
    }


    private boolean hasPlayerWon(Player player) {
        int playerPosition = board.getPlayerPositions().get(player.getId());
        int winningPosition = board.getSize();
        return playerPosition == winningPosition;
    }

    public void initiateAndStartRollingGame() {
        while (!isGameCompleted()) {
            int totalDiceValue = getTotalValueAfterDiceRolls();
            Player currentPlayer = players.poll();
            this.board.movePlayer(currentPlayer, totalDiceValue);
            if (hasPlayerWon(currentPlayer)) {
                System.out.println(currentPlayer.getName() + " won the game");
                board.getPlayerPositions().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }


}
