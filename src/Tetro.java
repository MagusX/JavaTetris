public class Tetro {
    protected static int[][] body;
    protected PairVal pos = new PairVal(4, 0);
    protected PairVal grid;
    protected Color color;
    protected int size = 3;
    protected int id = 0;
    public int collideLeft = 2;
    public int collideRight = 2;
    public int collideBottom = 2;

    public void fall() {
        if (collideBottom == 2)
            pos.setY(pos.getY() + 1);
    }

    public void moveLeft() {
        if (collideBottom == 2 && collideLeft != -1)
            pos.setX(pos.getX() - 1);
    }

    public void moveRight() {
        if (collideBottom == 2 && collideRight != 1)
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
    private int collidedLeft() {
        int posX = pos.getX();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                if (body[i][j] != 0) {
                    if (posX + j < 0) {
                        moveRight();
                        return -1;
                    }
                    if (posX + j == 0) {
                        return -1;
                    }
                    if (Board.grid[pos.getY() + i][posX + j] != 0) {
                        moveRight();
                        return -1;
                    }
                    if (Board.grid[pos.getY() + i][posX + j - 1] != 0) {
                        return -1;
                    }
                }
            }
        }
        return 2;
    }

    private int collidedRight() {
        int posX = pos.getX();
        int maxRight = 0;
        int posY = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++) {
                if (body[i][j] != 0 && j > maxRight) {
                    maxRight = j;
                    posY = i;
                }

            }
        }
        int right = posX + maxRight;
        if (Board.grid[pos.getY() + posY][right] != 0) {
            moveLeft();
            return 1;
        }
        if (Board.grid[pos.getY() + posY][right + 1] != 0) {
            return 1;
        }
        if (right > Board.rightEdge) {
            moveLeft();
            return 1;
        }
        if (right == Board.rightEdge)
            return 1;
        return 2;
    }

    private int collidedBottom() {
        int posY = pos.getY();
        if (posY + size < Board.highestTetroPos) return 2;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (body[i][j] != 0) {
                    if (posY + i >= Board.bottomEdge)
                        return 0;
                    if (Board.grid[posY + i + 1][pos.getX() + j] != 0)
                        return 0;
                }
            }
        }
        return 2;
    }

    public void detectCollision() {
        collideLeft = collidedLeft();
        collideRight = collidedRight();
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
