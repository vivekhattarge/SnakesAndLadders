package com.snakesAndLadders;

import com.snakesAndLadders.dto.Ladder;
import com.snakesAndLadders.dto.Player;
import com.snakesAndLadders.dto.Snake;
import com.snakesAndLadders.service.SnakesAndLaddersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SnakesAndLaddersServiceTest {

    SnakesAndLaddersService snakesAndLaddersService;

    @BeforeEach
    public void setup() {
        snakesAndLaddersService = new SnakesAndLaddersService();
    }


    @Test
    public void shouldAddOneSnakeAndOneLadderForSinglePlayer() {

        Player player = new Player("Vivek");
        Ladder ladder = new Ladder(30, 90);
        Snake snake = new Snake(19, 10);

        List<Player> players = new ArrayList<>();
        players.add(player);

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(ladder);

        List<Snake> snakes = new ArrayList<>();
        snakes.add(snake);

        snakesAndLaddersService.setLadders(ladders);
        snakesAndLaddersService.setSnakes(snakes);
        snakesAndLaddersService.setPlayers(players);

        snakesAndLaddersService.initiateAndStartRollingGame();

    }

    @Test
    public void shouldAddOneSnakeAndOneLadderForSinglePlayerWithCrookedDice() {

        Player player = new Player("Vivek");
        Ladder ladder = new Ladder(30, 90);
        Snake snake = new Snake(19, 10);

        List<Player> players = new ArrayList<>();
        players.add(player);

        List<Ladder> ladders = new ArrayList<>();
        ladders.add(ladder);

        List<Snake> snakes = new ArrayList<>();
        snakes.add(snake);

        snakesAndLaddersService.setLadders(ladders);
        snakesAndLaddersService.setSnakes(snakes);
        snakesAndLaddersService.setPlayers(players);

        snakesAndLaddersService.setDiceCrooked(true);

        snakesAndLaddersService.initiateAndStartRollingGame();

    }


}
