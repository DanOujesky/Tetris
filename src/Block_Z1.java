import javax.script.ScriptEngine;
import java.awt.*;

/**
 * this class represents BLock_Z1
 */
public class Block_Z1 extends BlockStructure{
    /**
     * Assign a color
     */
    public Block_Z1() {
        create(Color.red);
    }
    /**
     * Assign x,y to make it look like a Block_Z1
     * @param x
     * @param y
     */
    public void setXY(int x, int y) {
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y - Block.SIZE;
        b[2].x = b[0].x- Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x - Block.SIZE;
        b[3].y = b[0].y+ Block.SIZE;
    }
    /**
     * change position of blocks to make it look like it is rotating
     */
    public void getDirection1(){
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y -Block.SIZE;
        tempB[2].x = b[0].x  -Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y+ Block.SIZE;

        updateXY(1);
    }
    /**
     * change position of blocks to make it look like it is rotating
     */
    public void getDirection2(){
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE;
        tempB[1].y = b[0].y ;
        tempB[2].x = b[0].x ;
        tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y - Block.SIZE;

        updateXY(2);
    }
    /**
     * Invoke getDirection1(), becose it has only two directions
     */
    public void getDirection3(){
        getDirection1();
    }
    /**
     * Invoke getDirection1(), becose it has only two directions
     */
    public void getDirection4(){
        getDirection2();
    }
}
