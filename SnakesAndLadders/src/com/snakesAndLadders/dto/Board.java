package com.snakesAndLadders.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<String, Integer> playerPositions;

    public Board(int size) {
        this.size = size;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.playerPositions = new HashMap<>();
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public Map<String, Integer> getPlayerPositions() {
        return playerPositions;
    }

    public void setPlayerPositions(Map<String, Integer> playerPositions) {
        this.playerPositions = playerPositions;
    }


    public void movePlayer(Player player, int positions) {
        int oldPosition = this.getPlayerPositions().get(player.getId());
        int newPosition = oldPosition + positions;

        int boardSize = this.getSize();

        if (newPosition > boardSize) {
            newPosition = oldPosition;
        } else {
            newPosition = getNewPositionAfterGoingThroughSnakesAndLadders(newPosition);
        }

        this.getPlayerPositions().put(player.getId(), newPosition);

        System.out.println(player.getName() + " got " + positions + " on dice and moved from " + oldPosition + " to " + newPosition);
    }


    private int getNewPositionAfterGoingThroughSnakesAndLadders(int newPosition) {
        int previousPosition;
        do {
            previousPosition = newPosition;
            for (Snake snake : this.getSnakes()) {
                if (snake.getStart() == newPosition) {
                    newPosition = snake.getEnd();
                }
            }
            for (Ladder ladder : this.getLadders()) {
                if (ladder.getStart() == newPosition) {
                    newPosition = ladder.getEnd();
                }
            }
        } while (newPosition != previousPosition);
        return newPosition;
    }


}