import java.awt.*;

/**
 * This class is blueprint for every type of structure
 */
public class BlockStructure {

    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1;
    public boolean active = true;
    public boolean deactivating;
    int deactivateCounter = 0;
    boolean leftCollision, rightCollision, bottomCollision;


    /**
     * Assign color to every block
     * @param color
     */
    public void create(Color color) {
        for (int i = 0; i < 4; i++) {
            b[i] = new Block(color);
        }
        for (int i = 0; i < 4; i++) {
            tempB[i] = new Block(color);
        }
    }
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}

    /**
     * this method check collision of the playing field
     */
    public void checkMovementCollsion(){

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision();

        for (int i = 0; i < b.length; i++) {
            if (b[i].x == PlayManager.left_x) {
                leftCollision = true;
            }

        }
        for (int i = 0; i < b.length; i++) {
            if (b[i].x  + Block.SIZE == PlayManager.right_x) {
                rightCollision = true;
            }

        }
        for (int i = 0; i < b.length; i++) {
            if (b[i].y + Block.SIZE == PlayManager.bottom_y) {
                bottomCollision = true;
            }

        }

    }

    /**
     * this method check if blockStructure can rotate
     */
    public void checkRotationCollision() {

        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;

        checkStaticBlockCollision();
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x < PlayManager.left_x) {
                leftCollision = true;
            }

        }
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].x + Block.SIZE > PlayManager.right_x) {
                rightCollision = true;
            }

        }
        for (int i = 0; i < b.length; i++) {
            if (tempB[i].y + Block.SIZE > PlayManager.bottom_y) {
                bottomCollision = true;
            }

        }
    }

    /**
     * this method check collision between block
     */

    private void checkStaticBlockCollision(){

        for (int i =0; i < PlayManager.staticBlocks.size(); i++) {

            int targetX = PlayManager.staticBlocks.get(i).x;
            int targetY = PlayManager.staticBlocks.get(i).y;

            for (int ii = 0; ii < b.length; ii++) {
                if (b[ii].y + Block.SIZE == targetY && b[ii].x == targetX) {
                    bottomCollision = true;
                }
            }
            for (int ii = 0; ii < b.length; ii++) {
                if (b[ii].x - Block.SIZE == targetX && b[ii].y == targetY) {
                    leftCollision = true;
                }
            }
            for (int ii = 0; ii < b.length; ii++) {
                if (b[ii].x + Block.SIZE == targetX && b[ii].y == targetY) {
                    rightCollision = true;
                }
            }
        }
    }
    public void setXY(int x, int y) {

    }

    /**
     * this method update positions x,y of the blocks
     * @param direction
     */
    public void updateXY(int direction){

        checkRotationCollision();

        if (!leftCollision && !rightCollision && !bottomCollision) {
            this.direction = direction;

            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }

    }

    /**
     * this method updates Block positions
     */
    public void update(){

        if (deactivating) {
            deactivating();
        }
        if (KeyHandler.upPressed) {

            switch (direction) {
                case 1: getDirection2();
                        break;
                case 2: getDirection3();
                        break;
                case 3: getDirection4();
                        break;
                case 4: getDirection1();
                        break;
            }
            KeyHandler.upPressed = false;
        }
        checkMovementCollsion();

        if (KeyHandler.downPressed) {
            if (!bottomCollision ) {
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;

                autoDropCounter = 0;
            }
            KeyHandler.downPressed = false;
        }
        if (KeyHandler.leftPressed) {

            if (!leftCollision) {
                b[0].x -= Block.SIZE;
                b[1].x -= Block.SIZE;
                b[2].x -= Block.SIZE;
                b[3].x -= Block.SIZE;

            }

            KeyHandler.leftPressed = false;
        }
        if (KeyHandler.rightPressed) {

            if (!rightCollision) {
                b[0].x += Block.SIZE;
                b[1].x += Block.SIZE;
                b[2].x += Block.SIZE;
                b[3].x += Block.SIZE;
            }


            KeyHandler.rightPressed = false;
        }


        if (bottomCollision ) {
            deactivating = true;
        } else {
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {

                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
                autoDropCounter = 0;
            }
        }

    }

    /**
     * this method stops the game
     */
    private void deactivating(){
        deactivateCounter++;

        if (deactivateCounter == 45) {

            deactivateCounter = 0;
            checkMovementCollsion();

            if (bottomCollision) {
                active = false;
            }
        }

    }

    /**
     * draw all the block
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D) {

        int smaller = 2;
        graphics2D.setColor(b[0].c);
        graphics2D.fillRect(b[0].x + smaller, b[0].y + smaller, Block.SIZE -(smaller*2), Block.SIZE -(smaller*2));
        graphics2D.fillRect(b[1].x + smaller, b[1].y + smaller, Block.SIZE -(smaller*2), Block.SIZE-(smaller*2));
        graphics2D.fillRect(b[2].x + smaller, b[2].y + smaller, Block.SIZE -(smaller*2), Block.SIZE-(smaller*2));
        graphics2D.fillRect(b[3].x + smaller, b[3].y + smaller, Block.SIZE -(smaller*2), Block.SIZE-(smaller*2));
    }


}
