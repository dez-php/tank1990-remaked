package dez.tank;

import dez.tank.game.Engine;
import dez.tank.game.GameEngine;

public class Main {

    public static void main(String[] args)
    {
        try {
            Engine game = new GameEngine();
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
