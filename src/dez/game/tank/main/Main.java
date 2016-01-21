package dez.game.tank.main;

import dez.game.engine.Engine;
import dez.game.tank.game.GameEngine;

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
