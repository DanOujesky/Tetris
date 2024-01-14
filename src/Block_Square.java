import java.awt.*;

/**
 * this class represents BLock_Square
 */
public class Block_Square extends BlockStructure{

    /**
     * Assign a color
     */
    public Block_Square() {
        create(Color.YELLOW);
    }
    /**
     * Assign x,y to make it look like a Block_Square
     * @param x
     * @param y
     */
    public void setXY(int x, int y) {
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.SIZE;
        b[2].x = b[0].x+ Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }

    /**
     * this method does not do anything, becose square has only one direction
     */
    public void getDirection1(){

    }
    /**
     * this method does not do anything, becose square has only one direction
     */
    public void getDirection2(){

    }
    /**
     * this method does not do anything, becose square has only one direction
     */
    public void getDirection3(){

    }
    /**
     * this method does not do anything, becose square has only one direction
     */
    public void getDirection4(){

    }
}
