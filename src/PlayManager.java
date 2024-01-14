import java.awt.*;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Random;

/**
 * Playmanager
 */
public class PlayManager {

    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    BlockStructure currentBLockStructure;
    final int BLOCKSTRUCTURE_START_X;
    final int BLOCKSTRUCTURE_START_Y;
    BlockStructure nextBlockStructure;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();

    boolean effectCounterOn;
    int effectCounter;
    ArrayList<Integer> effextY = new ArrayList<>();
    public static int dropInterval = 60;
    boolean gameOver;
    int level = 1;
    int score;
    int lines;

    /**
     * Playmanager
     */
    public PlayManager(){
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        BLOCKSTRUCTURE_START_X = left_x + (WIDTH/2) - Block.SIZE;
        BLOCKSTRUCTURE_START_Y = top_y + Block.SIZE;

        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;
        currentBLockStructure = pickBlock();
        currentBLockStructure.setXY(BLOCKSTRUCTURE_START_X, BLOCKSTRUCTURE_START_Y);
        nextBlockStructure =pickBlock();
        nextBlockStructure.setXY(NEXTMINO_X, NEXTMINO_Y);

    }

    /**
     * this method will randomly pick one Block
     * @return
     */
    private BlockStructure pickBlock(){
        BlockStructure blockStructure = null;
        int i = new Random().nextInt(7);

        switch (i){
            case 0: blockStructure = new Block_L1();
                    break;
            case 1: blockStructure = new BLock_L2();
                break;
            case 2: blockStructure = new Block_Square();
                break;
            case 3: blockStructure = new BLock_Bar();
                break;
            case 4: blockStructure = new BLock_T();
                break;
            case 5: blockStructure = new Block_Z1();
                break;
            case 6: blockStructure = new BLock_Z2();
                break;
        }
        return blockStructure;
    }

    /**
     * this method updates a game
     */
    public void update(){

        if (currentBLockStructure.active == false) {

            staticBlocks.add(currentBLockStructure.b[0]);
            staticBlocks.add(currentBLockStructure.b[1]);
            staticBlocks.add(currentBLockStructure.b[2]);
            staticBlocks.add(currentBLockStructure.b[3]);

            if (currentBLockStructure.b[0].x == BLOCKSTRUCTURE_START_X && currentBLockStructure.b[0].y == BLOCKSTRUCTURE_START_Y) {
                gameOver = true;
            }
            currentBLockStructure.deactivating = false;

            currentBLockStructure = nextBlockStructure;
            currentBLockStructure.setXY(BLOCKSTRUCTURE_START_X, BLOCKSTRUCTURE_START_Y);
            nextBlockStructure = pickBlock();
            nextBlockStructure.setXY(NEXTMINO_X, NEXTMINO_Y);

            checkDelete();
        } else {
            currentBLockStructure.update();
        }

    }

    /**
     * this method remove line if the line is full
     */
    private void checkDelete(){

        int x = left_x;
        int y = top_y;
        int blockCount = 0;
        int lineCount = 0;

        while (x < right_x && y < bottom_y) {

            for (int i =0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y) {
                    blockCount++;
                }
            }
            x += Block.SIZE;

            if (x == right_x) {

                if (blockCount == 12) {

                    effectCounterOn = true;
                    effextY.add(y);
                    for (int i =staticBlocks.size()-1; i > -1; i--) {
                        if (staticBlocks.get(i).y == y) {
                            staticBlocks.remove(i);
                        }
                    }
                    lineCount++;
                    lines++;
                    if (lines % 2 == 0 && dropInterval > 1) {
                        level++;
                        if (dropInterval > 10) {
                            dropInterval -= 10;
                        } else {
                            dropInterval -= 1;
                        }
                    }
                    for (int i =0; i < staticBlocks.size(); i++) {
                        if (staticBlocks.get(i).y < y) {
                            staticBlocks.get(i).y += Block.SIZE;
                        }
                    }
                }
                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
            if (lineCount > 0) {
                int singleLineScore = 10 * level;
                score += singleLineScore * lineCount;
            }
        }
    }

    /**
     * draw everything on the screen
     * @param graphics2D
     */
    public void draw(Graphics2D graphics2D) {

        graphics2D.setColor(Color.WHITE);
        graphics2D.setStroke(new BasicStroke(4f));
        graphics2D.drawRect(left_x - 4, top_y, WIDTH+8, HEIGHT+8);

        int x = right_x + 100;
        int y = bottom_y - 200;
        graphics2D.drawRect(x, y, 200, 200);

        graphics2D.setFont(new Font("Arial", Font.PLAIN, 30));
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString("Next", x+60, y+60);

        graphics2D.drawRect(x, top_y, 300, 300);
        x+= 40;
        y = top_y + 90;
        graphics2D.drawString("LEVEL: " + level, x ,y ); y += 70;
        graphics2D.drawString("LINES: " + lines, x, y); y += 70;
        graphics2D.drawString("SCORES: " + score, x, y);
        if (currentBLockStructure != null) {
            currentBLockStructure.draw(graphics2D);
        }
        nextBlockStructure.draw(graphics2D);

        for (int i = 0; i < staticBlocks.size(); i++) {
            staticBlocks.get(i).draw(graphics2D);
        }

        if (effectCounterOn) {
            effectCounter++;
            graphics2D.setColor(Color.RED);
            for (int i = 0; i < effextY.size(); i++) {
                graphics2D.fillRect(left_x, effextY.get(i), WIDTH, Block.SIZE);
            }
            if (effectCounter == 10) {
                effectCounterOn = false;
                effectCounter = 0;
                effextY.clear();
            }
        }
        graphics2D.setColor(Color.YELLOW);
        graphics2D.setFont(graphics2D.getFont().deriveFont(50f));
        if (gameOver) {
            x = left_x + 25;
            y = top_y + 320;
            graphics2D.drawString("GAME OVER", x, y);
        }
        else if (KeyHandler.pausePressed) {
            x = left_x + 70;
            y = top_y + 320;
            graphics2D.drawString("PAUSED", x,y);
        }
        x = 35;
        y = top_y + 320;
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Times New Roman", Font.ITALIC, 60));
        graphics2D.drawString("Tetris", x + 125,y);
    }

}
