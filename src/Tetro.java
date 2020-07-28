public class Tetro {
    protected static int[][] body;
    protected PairVal pos = new PairVal(4, 0);
    protected PairVal grid;
    protected Color color;
    protected int size = 3;
    protected int id = 0;
    public int collideSide = 2;
    public int collideBottom = 2;

    public void fall() {
        if (collideBottom == 2)
            pos.setY(pos.getY() + 1);
    }

    public void moveLeft() {
        if (collideBottom == 2 && collideSide != -1)
            pos.setX(pos.getX() - 1);
    }

    public void moveRight() {
        if (collideBottom == 2 && collideSide != 1)
            pos.setX(pos.getX() + 1);
    }

    protected void rotateRight() {
        int t = body[0][0];
        body[0][0] = body[0][2];
        body[0][2] = body[2][2];
        body[2][2] = body[2][0];
        body[2][0] = t;

        t = body[1][0];
        body[1][0] = body[0][1];
        body[0][1] = body[1][2];
        body[1][2] = body[2][1];
        body[2][1] = t;
    }

    protected void drawShape() {
        Graphic.proc.fill(color.r,color.g,color.b);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (body[i][j] != 0) {
                    grid = new PairVal(pos.getX() + j, pos.getY() + i);
                    PairVal pixel = Board.gridToPix(grid);
                    Graphic.proc.rect(pixel.getX(), pixel.getY(), Board.sqrSize, Board.sqrSize);
                }
            }
        }
    }

    //-1 left, 0 bottom, 1 right, 2 false
    public int collidedLeft() {
        int posX = pos.getX();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (body[i][j] != 0) {
                    if (posX + j < 0) {
                        moveRight();
                        return -1;
                    }
                    if (posX + j == 0) {
                        return -1;
                    }
                }
            }
        }
        return 2;
    }

    public int collidedRight() {
        int posX = pos.getX();
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j >= 1; j--) {
                if (body[i][j] != 0) {
                    if (posX + j > Board.rightEdge) {
                        moveLeft();
                        return 1;
                    }
                    if (posX + j == Board.rightEdge)
                        return 1;
                }
            }
        }
        return 2;
    }

    public int collidedBottom() {
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (body[i][j] != 0 && pos.getY() + i >= Board.bottomEdge)
                    return 0;
            }
        }
        return 2;
    }

    public void detectCollision() {
        //int left = collidedLeft();
        //collideSide = (left == 2) ? right : left;
        collideSide = collidedRight();
        collideBottom = collidedBottom();
    }

    public void saveTetro() {
        int posY;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (body[i][j] != 0) {
                    posY = pos.getY();
                    Board.grid[posY + i][pos.getX() + j] = id;
                    if (posY < Board.highestTetroPos) Board.highestTetroPos = posY;
                }
            }
        }
    }
}
