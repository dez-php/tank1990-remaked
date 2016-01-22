package dez.tank.game;

import dez.tank.graphics.Drawable;
import dez.tank.graphics.GameWindow;
import dez.tank.graphics.LayerManager;
import dez.tank.graphics.LayerType;
import dez.tank.helpers.ResourceConfig;
import dez.tank.helpers.Time;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

abstract public class Engine implements Runnable {

    public static int   WIDTH            = 800;
    public static int   HEIGHT           = 600;
    public static int   THREAD_IDLE_TIME = 1;
    public static float UPDATE_RATE      = 60.0f;
    public static float UPDATE_TIME      = Time.ONE_NANO_SECOND / UPDATE_RATE;
    public        float x                = 0;
    protected Graphics2D   graphics2D;
    protected Properties   properties;
    protected GameWindow   gameWindow;
    protected LayerManager layerManager;
    private   Thread       gameThread;
    private boolean isRunning = false;

    public Engine() throws IOException {
        properties = new ResourceConfig("resource/config.properties").loadProperties();
        gameWindow = new GameWindow(WIDTH, HEIGHT, properties.getProperty("title"));
        graphics2D = gameWindow.getGraphics();
        layerManager = new LayerManager();
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public synchronized void start() {

        if (this.isRunning) {
            return;
        }

        isRunning = true;

        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void stop() {

        if (!isRunning) {
            return;
        }

        isRunning = false;

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        closeGame();

    }

    abstract protected void update();

    abstract protected void render();

    private void updateData() {
        this.update();
    }

    private void renderData() {
        gameWindow.clear();

        this.render();

        gameWindow.updateBuffer();
    }

    public void run() {

        long  lastTime         = Time.nano();
        float lostTime         = 0;
        float totalElapsedTime = 0;
        int[] counters         = {0, 0, 0};

        while (isRunning) {

            long nowTime     = Time.nano();
            long elapsedTime = nowTime - lastTime;

            lastTime = nowTime;
            lostTime += (elapsedTime / UPDATE_TIME);
            totalElapsedTime += elapsedTime;

            boolean hasToRender = false;

            while (lostTime > 1) {
                updateData();
                lostTime--;

                counters[1]++;
                if (hasToRender) {
                    counters[2]++;
                } else {
                    hasToRender = true;
                }
            }

            if (hasToRender) {
                renderData();
                counters[0]++;
            } else {
                try {
                    Thread.sleep(THREAD_IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (totalElapsedTime >= Time.ONE_NANO_SECOND) {
                gameWindow.setTitle(" [FPS: " + counters[0] + ", UPD: " + counters[1] + ", UPD LOST: " + counters[2] + "]");
                totalElapsedTime = 0;
                Arrays.fill(counters, 0);
            }

        }

    }

    public void closeGame() {

    }

}
