package dez.game.engine;

import java.awt.*;
import java.awt.Window;
import java.io.IOException;
import java.util.Arrays;

public class Engine implements Runnable {

    private Graphics2D graphics2D;
    private Thread gameThread;

    private boolean isRunning   = false;

    public static int THREAD_IDLE_TIME = 1;
    public static float UPDATE_RATE = 60.0f;
    public static float UPDATE_TIME = Time.ONE_NANO_SECOND / UPDATE_RATE;

    public float x = 0;

    public Game() {
        Window.create();
        graphics2D = Window.getGraphics();
    }

    public synchronized void start() {

        if(this.isRunning) {
            return;
        }

        isRunning  = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {

        if(! isRunning) {
            return;
        }

        isRunning  = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeGame();

    }

    private void updateGame() {

    }

    private void renderGame() {
        Window.clear();

        Window.updateBuffer();
    }

    public void run() {

        long lastTime           = Time.nano();
        float lostTime          = 0;
        float totalElapsedTime  = 0;
        int[] counters          = {0};

        while (isRunning) {

            long nowTime = Time.nano();
            long elapsedTime = nowTime - lastTime;

            lastTime = nowTime;
            lostTime += (elapsedTime / UPDATE_TIME);
            totalElapsedTime += elapsedTime;

            boolean hasToRender = false;

            while ( lostTime > 1 ) {
                updateGame();
                lostTime--;
                hasToRender = true;
            }

            if(hasToRender) {
                renderGame();
                counters[0]++;
            } else {
                try {
                    Thread.sleep(THREAD_IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(totalElapsedTime >= Time.ONE_NANO_SECOND) {
                Window.getWindow().setTitle(Settings.WINDOW_TITLE + " [FPS: " + counters[0] + "]");
                totalElapsedTime = 0;
                Arrays.fill(counters, 0);
            }

        }

    }

    public void closeGame() {

    }

}
