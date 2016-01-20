package dez.game.tank.display;

import dez.game.tank.main.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;
import java.util.Random;

public class Window {

    public static JFrame window;
    public static Canvas windowContent;

    public static BufferedImage bufferedImage;
    public static BufferStrategy bufferStrategy;
    public static int[] bufferedData;
    public static Graphics graphics2D;

    public static boolean isCreated = false;

    public static void create() {

        if (isCreated) {
            return;
        }

        windowContent = new Canvas();
        windowContent.setSize(new Dimension(GameConfig.WIDTH, GameConfig.HEIGHT));

        window = new JFrame(GameConfig.WINDOW_TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().add(windowContent);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setIconImage(new ImageIcon(GameConfig.RESOURCE_FOLDER + GameConfig.ICON_PATH).getImage());

        bufferedImage = new BufferedImage(GameConfig.WIDTH, GameConfig.HEIGHT, BufferedImage.TYPE_INT_ARGB);
        bufferedData = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();

        graphics2D = bufferedImage.getGraphics();
        ((Graphics2D) graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        windowContent.createBufferStrategy(3);
        bufferStrategy = windowContent.getBufferStrategy();

        isCreated = true;

        float num = 0;

        while (true) {
            Arrays.fill(bufferedData, 0xff000000);

            getGraphics().setColor(Color.CYAN);

            num += 0.01;

            System.out.println(Math.sin(num));

            getGraphics().fillOval(GameConfig.WIDTH / 2 - 50 + (int)(Math.sin(num) * 50), GameConfig.HEIGHT / 2 - 50, 100, 100);

            Graphics g = bufferStrategy.getDrawGraphics();
            g.drawImage(bufferedImage, 0, 0, null);
            bufferStrategy.show();
        }



    }

    public static Graphics getGraphics() {
        return graphics2D;
    }
}
