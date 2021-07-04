package com.snakesAndLadders.service;

import java.util.Random;

public class DiceService {

    public static int roll(boolean isCrooked) {
        if (!isCrooked) {
            return new Random().nextInt(6) + 1;
        } else {
            return 10;
        }
    }


}
