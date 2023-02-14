import java.util.Random;

public class Mower {
    private static final String GRASS_CUT = "__";
    private static final String GRASS = "||";

    private String sign;
    String[][] tiles;
    int start;
    int maxRow;
    int maxCol;
    Lawn lawn;

    public Mower() {
    }


    public Mower(String sign, Lawn lawn) {
        this.sign = sign;
        this.lawn = lawn;
        maxRow = lawn.getRow();
        maxCol = lawn.getCol();
        tiles = lawn.getTiles();
    }


    public int[] dropAtRandom(Lawn lawn) throws InterruptedException {

        Random ran = new Random();
        int row, col;

            row = ran.nextInt(maxRow);
            col = ran.nextInt(maxCol);

        int[] baseCoord = new int[]{col, row};

        tiles[col][row] = sign;

        lawn.draw();
        Thread.sleep(2000);

        return baseCoord;
    }



    public void process(Lawn lawn) throws InterruptedException {

        moveToSide(lawn);
        moveToTopOrBottom(lawn);
        cut(lawn);
    }

    public void moveToSide(Lawn lawn) throws InterruptedException {

        // FIND THE MOWER
        int col = -1;
        int row = -1;
        while (row != 0 && row != maxRow - 1) {
            for (int i = 0; i < maxCol; i++) {
                for (int j = 0; j < maxRow; j++) {
                    if (sign.equals(tiles[i][j])) {
                        col = i;
                        row = j;
                    }
                }
            }
            // IF MIDDLE OR NEAR TO LEFT, GO LEFT
            if (row != 0 && row != maxRow - 1) {
                for (int j = 0; j < maxCol; j++) {
                    if (row <= maxRow / 2 && row != 0) {
                        tiles[col][row - 1] = sign;
                        tiles[col][row] = GRASS;
                        break;

            // IF NEAR TO RIGHT, GO RIGHT
                    } else if (row > maxRow / 2 && row != maxRow - 1) {
                        tiles[col][row + 1] = sign;
                        tiles[col][row] = GRASS;
                        break;
                    }
                }
                lawn.draw();
            }
        }
    }


    public void moveToTopOrBottom(Lawn lawn) throws InterruptedException {

        // FIND THE MOWER
        int col = -1;
        int row = -1;
        while (col != 0 && col != maxCol - 1) {
            for (int i = 0; i < maxCol; i++) {
                for (int j = 0; j < maxRow; j++) {

                    if (sign.equals(tiles[i][j])) {
                        col = i;
                        row = j;
                    }
                }
            }
            // IF MIDDLE OR NEAR TO TOP, GO TOP
            if (col != 0 && col != maxCol - 1) {
                for (int i = 0; i < maxCol; i++) {
                    for (int j = 0; j < maxRow; j++) {
                        if (col <= maxCol / 2 && col != 0) {
                            tiles[col - 1][row] = sign;
                            tiles[col][row] = GRASS;
                            break;

            // IF NEAR TO BOTTOM, GO BOTTOM
                        } else if (col > maxCol / 2 && col != 0 && col != maxCol - 1) {
                            tiles[col + 1][row] = sign;
                            tiles[col][row] = GRASS;
                            break;
                        }
                    }
                }
                lawn.draw();
            }
        }
    }

    public void cut(Lawn lawn) throws InterruptedException {

        start = 0;

        // CUT FROM TOP LEFT
        if (sign.equals(tiles[start][start])) {
            cutFrom00(lawn);

        // CUT FROM TOP RIGHT
        } else if (sign.equals(tiles[start][maxRow - 1])) {
            cutFrom0MaxRow(lawn);

        // CUT FROM BOTTOM LEFT
        } else if (sign.equals(tiles[maxCol - 1][start])) {
            cutFromMaxCol0(lawn);

        // CUT FROM BOTTOM RIGHT
        } else if (sign.equals(tiles[maxCol - 1][maxRow - 1])) {
            cutFromMaxColMaxRow(lawn);
        }
    }


    public void cutFrom00(Lawn lawn) throws InterruptedException {

        //CUT 1. ROW FORWARD, THEN NEXT ROW BACKWARD AND REPEAT FOR MAXCOL/2 IF MAXCOL IS ODD, CUT +1 ROW

        start = 0;

        for (int j = 0; j < maxCol / 2; j++) {
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start + 1][i] = sign;
                }
                lawn.draw();
            }
            start++;
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start + 1][i] = sign;
                }
                lawn.draw();
            }
            start++;
        }
        if (maxCol % 2 != 0) {
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start][i] = "_M";
                }
                lawn.draw();
            }
        }
    }

    public void cutFrom0MaxRow(Lawn lawn) throws InterruptedException {

        start = 0;

        for (int j = 0; j < maxCol / 2; j++) {
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start + 1][i] = sign;
                }
                lawn.draw();
            }
            start++;
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start + 1][i] = sign;
                }
                lawn.draw();
            }
            start++;
        }
        if (maxCol % 2 != 0) {
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start][i] = "M_";
                }
                lawn.draw();
            }
        }
    }

    public void cutFromMaxCol0(Lawn lawn) throws InterruptedException {

        start = maxCol - 1;

        for (int j = 0; j < maxCol / 2; j++) {
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start - 1][i] = sign;
                }
                lawn.draw();
            }
            start--;
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start - 1][i] = sign;
                }
                lawn.draw();
            }
            start--;
        }
        if (maxCol % 2 != 0) {
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start][i] = "_M";
                }
                lawn.draw();
            }
        }
    }

    public void cutFromMaxColMaxRow(Lawn lawn) throws InterruptedException {

        start = maxCol - 1;

        for (int j = 0; j < maxCol / 2; j++) {
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start - 1][i] = sign;
                }
                lawn.draw();
            }
            start--;
            for (int i = 0; i < maxRow; i++) {
                tiles[start][i] = GRASS_CUT;
                if (i < maxRow - 1) {
                    tiles[start][i + 1] = sign;
                }
                if (i == maxRow - 1) {
                    tiles[start - 1][i] = sign;
                }
                lawn.draw();
            }
            start--;
        }
        if (maxCol % 2 != 0) {
            for (int i = maxRow - 1; i >= 0; i--) {
                tiles[start][i] = GRASS_CUT;
                if (i > 0) {
                    tiles[start][i - 1] = sign;
                }
                if (i == 0) {
                    tiles[start][i] = "M_";
                }
                lawn.draw();
            }
        }
    }

    public void returnToBase(Lawn lawn, int[] baseCoord) throws InterruptedException {

        // FROM END POINT GO BACK TO BASE COL, THE TO BASE ROW

        Thread.sleep(1000);
        System.out.println("Lawnmower is now returning to base (to droppoint).");
        Thread.sleep(3000);

        tiles[baseCoord[0]][baseCoord[1]] = "BS";
        start = 0;

        if ("M_".equals(tiles[maxCol - 1][start])) {

            for (int j = 0; j < baseCoord[1]; j++) {

                tiles[maxCol - 1][j + 1] = sign;
                tiles[maxCol - 1][j] = GRASS_CUT;
                lawn.draw();
            }
            for (int i = maxCol - 1; i > baseCoord[0]; i--) {

                tiles[i - 1][baseCoord[1]] = sign;
                tiles[i][baseCoord[1]] = GRASS_CUT;
                lawn.draw();
            }
        } else if ("M_".equals(tiles[start][start])) {
            for (int j = 0; j < baseCoord[1]; j++) {

                tiles[start][j + 1] = sign;
                tiles[start][j] = GRASS_CUT;
                lawn.draw();
            }
            for (int i = 0; i < baseCoord[0]; i++) {

                tiles[i + 1][baseCoord[1]] = sign;
                tiles[i][baseCoord[1]] = GRASS_CUT;
                lawn.draw();
            }
        } else if ("_M".equals(tiles[start][maxRow - 1])) {
            for (int j = maxRow - 1; j > baseCoord[1]; j--) {

                tiles[start][j - 1] = sign;
                tiles[start][j] = GRASS_CUT;
                lawn.draw();
            }
            for (int i = 0; i < baseCoord[0]; i++) {

                tiles[i + 1][baseCoord[1]] = sign;
                tiles[i][baseCoord[1]] = GRASS_CUT;
                lawn.draw();
            }

        } else if ("_M".equals(tiles[maxCol - 1][maxRow - 1])) {
            for (int j = maxRow - 1; j > baseCoord[1]; j--) {

                tiles[maxCol - 1][j - 1] = sign;
                tiles[maxCol - 1][j] = GRASS_CUT;
                lawn.draw();
            }
            for (int i = maxCol - 1; i > baseCoord[0]; i--) {

                tiles[i - 1][baseCoord[1]] = sign;
                tiles[i][baseCoord[1]] = GRASS_CUT;
                lawn.draw();
            }
        }
        Thread.sleep(2000);
        System.out.println("Lawnmower is now recharging.");
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
