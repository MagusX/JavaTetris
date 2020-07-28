public class SqrShape extends Tetro {
    public SqrShape() {
        id = 4;
        body = new int[][]{{4,4},{4,4}};
        color = new Color(248,229,1);
        size = 2;
    }

    //Overwrite empty
    public void rotateRight() {}
}
