
public class Board {
    private static int x = 300;
    private static int y = 0;
    public static int sqrSize = 30;
    private static int row = 24;
    private static int col = 10;
    private static int size = row * col;
    public static int width = sqrSize * col;
    public static int height = sqrSize * row;
    private static int[][] grid = new int[size][size];
    public static int rightEdge = col - 1;
    public static int bottomEdge = row - 1;

    public int[][] grid() {
        return grid;
    }

    public Board() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = 0;
            }
        }
    }

    public static PairVal gridToPix(PairVal grid) {
        return new PairVal(x + grid.getX() * sqrSize, grid.getY() * sqrSize);
    }

    public void drawBorder() {
        Graphic.proc.stroke(50);
        //horizontal lines
        for (int i = 0; i < row + 1; i++) {
            int pos = i * sqrSize;
            Graphic.proc.line(x, pos, x + width, pos);
        }
        //vertical lines
        for (int i = 0; i < col + 1; i++) {
            int pos = x + i * sqrSize;
            Graphic.proc.line(pos, 0, pos, height);
        }

        // draw border line
        Graphic.proc.stroke(253,255,24);
        Graphic.proc.line(x, 0, x, height);
        Graphic.proc.line(x + width, 0, x + width, height);
    }
}
