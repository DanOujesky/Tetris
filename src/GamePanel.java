import javax.swing.*;
import java.awt.*;

/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManager playmanager;

    /**
     * Assign information about GamePanel
     */
    public GamePanel() {

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);

        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        playmanager = new PlayManager();
    }

    /**
     * this method will lanche the game
     */
    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * this method will run the game
     */
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread !=  null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    /**
     * This method updates the game
     */
    private void update(){

        if (KeyHandler.pausePressed == false && playmanager.gameOver == false) {
            playmanager.update();
        }
    }

    /**
     * this method will draw everything on screen
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        playmanager.draw(graphics2D);
    }
}
