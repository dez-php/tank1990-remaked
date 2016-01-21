package dez.game.engine;

import dez.game.engine.graphics.Drawable;
import dez.game.engine.graphics.GameWindow;
import dez.game.engine.graphics.LayerType;
import dez.game.engine.helpers.ResourceConfig;
import dez.game.engine.helpers.Time;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

abstract public class Engine implements Runnable {

    private Graphics2D graphics2D;
    private Thread gameThread;
    private Properties properties;

    protected Map<LayerType, List<Drawable>> layers;

    private boolean isRunning   = false;

    public static int THREAD_IDLE_TIME = 1;
    public static float UPDATE_RATE = 60.0f;
    public static float UPDATE_TIME = Time.ONE_NANO_SECOND / UPDATE_RATE;

    public float x = 0;

    public Engine() throws IOException {
        properties = new ResourceConfig("resource/config.properties").loadProperties();
        layers = new HashMap<>();
        GameWindow.create(800, 600, properties.getProperty("title"));
        graphics2D = GameWindow.getGraphics();
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
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

    abstract protected void update();

    abstract protected void render();

    private void updateData() {
        this.update();
    }

    private void renderData() {
        GameWindow.clear();
        this.render();
        GameWindow.updateBuffer();
    }

    public void run() {

        long lastTime           = Time.nano();
        float lostTime          = 0;
        float totalElapsedTime  = 0;
        int[] counters          = {0, 0, 0};

        while (isRunning) {

            long nowTime = Time.nano();
            long elapsedTime = nowTime - lastTime;

            lastTime = nowTime;
            lostTime += (elapsedTime / UPDATE_TIME);
            totalElapsedTime += elapsedTime;

            boolean hasToRender = false;

            while ( lostTime > 1 ) {
                updateData();
                lostTime--;

                counters[1]++;
                if(hasToRender) {
                    counters[2]++;
                } else {
                    hasToRender = true;
                }
            }

            if(hasToRender) {
                renderData();
                counters[0]++;
            } else {
                try {
                    Thread.sleep(THREAD_IDLE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(totalElapsedTime >= (Time.ONE_NANO_SECOND / 2)) {
                GameWindow.setTitle(" [FPS: " + (counters[0] * 2) + ", UPD: " + (counters[1] * 2) + ", UPD LOST: " + (counters[2] * 2) + "]");
                totalElapsedTime = 0;
                Arrays.fill(counters, 0);
            }

        }

    }

    public void closeGame() {

    }

}
