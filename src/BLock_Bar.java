import java.awt.*;

/**
 * this class represents BLock_Bar
 */
public class BLock_Bar extends BlockStructure{

    /**
     * Assign a color
     */
    public BLock_Bar() {
        create(Color.CYAN);
    }

    /**
     * Assign x,y to make it look like block_bar
     * @param x
     * @param y
     */
    public void setXY(int x, int y) {
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y ;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE*2;
        b[3].y = b[0].y;
    }

    /**
     * change position of blocks to make it look like it is rotating
     */
    public void getDirection1(){
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x -Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x +Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE*2;
        tempB[3].y = b[0].y;

        updateXY(1);
    }
    /**
     * change position of blocks to make it look like it is rotating
     */
    public void getDirection2(){
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x ;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x ;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE*2;

        updateXY(2);
    }

    /**
     * Invoke getDirection1(), becose it has only two directions
     */
    public void getDirection3(){
        getDirection1();
    }
    /**
     * Invoke getDirection2(), becose it has only two directions
     */
    public void getDirection4(){
        getDirection2();
    }
}
