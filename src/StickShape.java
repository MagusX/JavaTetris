public class StickShape extends Tetro {
    private static int status;
    public StickShape() {
        id = 1;
        body = new int[][]{{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
        status = 0;
        color = new Color(3,255,253);
        size = 4;
    }

    //Way faster than matrix rotate algorithm :D
    public void rotateRight() {
        switch (status) {
            case 0:
                body = new int[][]{{0,0,0,0},{1,1,1,1},{0,0,0,0},{0,0,0,0}};
                break;
            case 1:
                body = new int[][]{{0,0,1,0},{0,0,1,0},{0,0,1,0},{0,0,1,0}};
                break;
            case 2:
                body = new int[][]{{0,0,0,0},{0,0,0,0},{1,1,1,1},{0,0,0,0}};
                break;
            case 3:
                body = new int[][]{{0,1,0,0},{0,1,0,0},{0,1,0,0},{0,1,0,0}};
                break;
        }
        status++;
        if (status == 4) status = 0;
    }
}
