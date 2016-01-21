package dez.game.tank.display;

import dez.game.tank.main.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Window {

    public static JFrame window;
    public static Canvas windowContent;

    public static BufferedImage bufferedImage;
    public static BufferStrategy bufferStrategy;
    public static int[] bufferedData;
    public static Graphics2D graphics2D;

    public static boolean isCreated = false;

    public static void create() {

        if (isCreated) {
            return;
        }

        windowContent = new Canvas();
        windowContent.setSize(new Dimension(Settings.WIDTH, Settings.HEIGHT));

        window = new JFrame(Settings.WINDOW_TITLE);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().add(windowContent);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setIconImage(new ImageIcon(Settings.RESOURCE_FOLDER + Settings.ICON_PATH).getImage());

        bufferedImage = new BufferedImage(Settings.WIDTH, Settings.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        bufferedData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();

        graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        windowContent.createBufferStrategy(3);
        bufferStrategy = windowContent.getBufferStrategy();

        isCreated = true;

    }

    public static void updateBuffer() {
        bufferStrategy.getDrawGraphics().drawImage(bufferedImage, 0, 0, null);
        bufferStrategy.show();
    }

    public static void clear() {
        Arrays.fill(bufferedData, Settings.CLEAR_COLOR);
    }

    public static Graphics2D getGraphics() {
        return graphics2D;
    }

    public static JFrame getWindow() {
        return window;
    }

    public static void close() {
        if (! isCreated) {
            return;
        }

        window.dispose();
    }

}
