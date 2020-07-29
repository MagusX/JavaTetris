import processing.core.PApplet;
import java.util.Random;

public class Graphic extends PApplet {
    public static PApplet proc;
    private static Board board;
    private static char pressedKey;
    private Tetro tetro;

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
        generateTetro();
    }

    private void refresh() {
        background(20);
        board.drawBorder();
        board.drawSavedTetros();
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
        if (keyPressed && tetro.collideBottom != 0) {
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

    private void getBoard() {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(Board.grid[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("--");

    }

    public void draw() {
        if (tetro.collideBottom == 0) {
            tetro.saveTetro();
            generateTetro();
        }
        keyBoardCtrl();
        tetro.drawShape();
        if (frameCount % 120 == 0) {
            //getBoard();
            refresh();
            tetro.fall();
        }
    }
}
