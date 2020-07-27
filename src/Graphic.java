import processing.core.PApplet;
import java.util.Random;

public class Graphic extends PApplet {
    public static PApplet proc;
    private static Board board;
    private static char pressedKey;
    private Tetro tetro = new JShape();

    public void run() {
        PApplet.main("Graphic");
    }

    public void settings() {
        size(300 * 3, 30 * 24);
    }

    public void setup() {
        proc = this;
        frameRate(60);
        board = new Board();
    }

    private void refresh() {
        background(20);
        board.drawBorder();
    }

    public void keyReleased() {
        if (pressedKey == 'w') tetro.rotateRight();
        else if (pressedKey == 'a') tetro.moveLeft();
        else if (pressedKey == 'd') tetro.moveRight();
        else if (pressedKey == 's') tetro.fall();
        if (pressedKey != 'x')
            refresh();
        pressedKey = 'x';
    }

    private void keyBoardCtrl() {
        tetro.detectCollision();
        if (keyPressed && tetro.collide != 0) {
            if (key == 'w') pressedKey = 'w';
            else if (key == 'a') pressedKey = 'a';
            else if (key == 'd') pressedKey = 'd';
            else if (key == 's') pressedKey = 's';
        }
    }

    private void generateTetro() {
        Random rand = new Random();
        int index = rand.nextInt(7);
        switch (index) {
            case 0:
                tetro = new StickShape();
                break;
            case 1:
                tetro = new JShape();
                break;
            case 2:
                tetro = new LShape();
                break;
            case 3:
                tetro = new SqrShape();
                break;
            case 4:
                tetro = new SShape();
                break;
            case 5:
                tetro = new TShape();
                break;
            case 6:
                tetro = new ZShape();
                break;
        }
    }

    public void draw() {
        if (tetro.collide == 0)
            generateTetro();
        keyBoardCtrl();
        tetro.drawShape();
        if (frameCount % 60 == 0) {
            refresh();
            tetro.fall();
        }
    }
}
