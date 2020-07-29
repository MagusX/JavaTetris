import java.util.Arrays;

public class Board {
    private static int x = 300;
    private static int y = 0;
    public static int sqrSize = 30;
    private static int row = 24;
    private static int col = 10;
    private static int size = row * col;
    public static int width = sqrSize * col;
    public static int height = sqrSize * row;
    public static int[][] grid = new int[size][size];
    public static int rightEdge = col - 1;
    public static int bottomEdge = row - 1;
    public static int highestTetroPos = row;

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


    private Color idToColor(int id) {
        Color color;
        switch (id) {
            case 1:
                color = new Color(3,255,253);
                break;
            case 2:
                color = new Color(255,170,0);
                break;
            case 3:
                color = new Color(0,0,255);
                break;
            case 4:
                color = new Color(248,229,1);
                break;
            case 5:
                color = new Color(1,255,1);
                break;
            case 6:
                color = new Color(153,0,254);
                break;
            case 7:
                color = new Color(255,1,0);
                break;
            default:
                color = new Color(255, 255, 255);
                break;
        }
        return color;
    }

    public void drawSavedTetros() {
        PairVal pixel;
        Graphic.proc.stroke(190,190,190);

        for (int i = highestTetroPos; i <= bottomEdge; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 0) {
                    Color color = idToColor(grid[i][j]);
                    pixel = gridToPix(new PairVal(j, i));
                    Graphic.proc.fill(color.r, color.g, color.b);
                    Graphic.proc.rect(pixel.getX(), pixel.getY(), sqrSize, sqrSize);
                }
            }
        }
    }

    private void deleteRow(int row) {
        for (int i = row; i >= highestTetroPos; i--) {
            grid[i] = grid[i - 1];
        }
        grid[highestTetroPos++] = new int[10];
    }

    public void scoreRow() {
        int count;
        for (int i = bottomEdge; i >= highestTetroPos; i--) {
            count = 0;
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 0)
                    count++;
            }
            if (count == col) {
                deleteRow(i);
                i++;
            }
        }
    }
}
