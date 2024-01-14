import java.awt.*;

/**
 * this class determine rect size
 */
public class Block extends Rectangle {

    public int x,y;
    public static final int SIZE = 30;
    public Color c;

    /**
     * Creates a block and assign color
     * @param c Color that we want to assign
     */
    public Block(Color c) {
        this.c = c;
    }

    /**
     * Draw one rect
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D) {
        int margine = 2;
        graphics2D.setColor(c);
        graphics2D.fillRect(x + margine,y + margine, SIZE - (margine * 2), SIZE - (margine*2));
    }
}
