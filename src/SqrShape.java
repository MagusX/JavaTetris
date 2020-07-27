public class SqrShape extends Tetro {
    public SqrShape() {
        body = new int[][]{{1,1},{1,1}};
        color = new Color(248,229,1);
        size = 2;
    }

    //Overwrite empty
    public void rotateRight() {}
}
