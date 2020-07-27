public class Tetro {
    protected static int[][] body;
    protected PairVal pos = new PairVal(4, 0);
    protected PairVal grid;
    protected Color color;
    protected int size = 3;
    public int collide = 2;

    public void fall() {
        if (collide != 0)
            pos.setY(pos.getY() + 1);
    }

    public void moveLeft() {
        if (collide != 0 && collide != -1)
            pos.setX(pos.getX() - 1);
    }

    public void moveRight() {
        if (collide != 0 && collide != 1)
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
    public int collidedEdge() {
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
                if (body[i][j] != 0 && pos.getY() + i == Board.bottomEdge)
                    return 0;
            }
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

    public void detectCollision() {
        collide = collidedEdge();
    }

//    public void saveTetro() {
//        for (int i = 0; i < size; i++) {
//            for (int j = 0; j < size; j++) {
//
//            }
//        }
//    }
}
