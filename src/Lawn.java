public class Lawn {
    private String[][] tiles;
    private int col;
    private int row;

    public Lawn() {
    }

    public Lawn(int col, int row) {
        this.col = col;
        this.row = row;
        tiles = new String[col][row];

        lawnInit();
    }


    public void draw() throws InterruptedException {

        System.out.println();
        System.out.print("  +");
        for (int i = 0; i < col; i++) {

            for (int j = 0; j < row; j++) {
                System.out.print("----+");
            }
            System.out.println();
            System.out.print("  |");
            for (int j = 0; j < row; j++) {
                System.out.print(" " + this.tiles[i][j] + " |");
            }

            System.out.println();
            System.out.print("  +");


        }
        for (int j = 0; j < row; j++) {
            System.out.print("----+");

        }

        System.out.println();
        Thread.sleep(700);


    }

    public void lawnInit() {
        for (int i = 0; i < this.tiles.length; i++) {
            for (int j = 0; j < this.tiles[i].length; j++) {
                this.tiles[i][j] = "||";
            }
        }
    }


    public String[][] getTiles() {
        return tiles;
    }

    public void setTiles(String[][] tiles) {
        this.tiles = tiles;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

}




