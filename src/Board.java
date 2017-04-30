import java.util.Arrays;

public class Board {
    private int n;
    private int[] tilesTo1D;
    private int[][] tiles;
    private Board goalBoard;

    public Board(int[][] blocks) {
        tiles = blocks;
        n = blocks.length;
        tilesTo1D = new int[n * n];
        int i = 0;
        for (int[] block : blocks) {
            for (int node : block) {
                tilesTo1D[i++] = node;
            }
        }
        createGoal();
    }

    private void createGoal() {
        int[][] goalTiles = new int[n][n];
        for (int i = 0; i < n * n - 1; i++) {
            goalTiles[i / n][i - n * i / n] = i + 1;
        }
        goalTiles[n - 1][n - 1] = 0;
        goalBoard = new Board(goalTiles);
    }

    public int dimension() {
        return n;
    }

    private int getCost(String what) {
        int row, col, goalRow, goalCol, goalI, manhattan = 0, hamming = 0;
        for (int i = 0; i < n * n; i++) {
            row = i / n;
            col = i - n * i / n;
            goalI = tiles[row][col] - 1;
            goalRow = goalI / n;
            goalCol = goalI - n * i / n;
            if (row != goalRow || col != goalCol) {
                manhattan = manhattan + Math.abs(row - goalRow + col - goalCol);
                hamming++;
            }
        }
        return (what.equals("manhattan") ? manhattan : hamming);
    }

    public int hamming() {
        return getCost("hamming");
    }

    public int manhattan() {
        return getCost("manhattan");
    }

    public boolean isGoal() {
        return Arrays.deepEquals(tiles, goalBoard.tiles);
    }

    public Board twin() {
        if (tiles.length == 1) return this;
        int[][] twinTiles = tiles;
        int i = 0, item1, item2, row, col;
        while (i < n * n - 1) {
            row = i / n;
            col = i - n * i / n;
            if (i < n - 1) {
                item1 = twinTiles[row][col];
                item2 = twinTiles[row][col + 1];
                if (item1 != 0 && item2 != 0) {
                    twinTiles[row][col] = item2;
                    twinTiles[row][col + 1] = item1;
                    return new Board(twinTiles);
                }
            }
            i++;
        }
        return null;
    }

    public Iterable<Board> neighbors() {
        Board[] neighbors;
        int i = 0, item1, item2, row, col, blankRow, blankCol;
        while (i < n * n - 1) {
            row = i / n;
            col = i - n * i / n;
            if (tiles[row][col] == 0){
                blankRow = row;
                blankCol = col;
            }
        }
        i = 0;
        while (i < n * n - 1) {
            row = i / n;
            col = i - n * i / n;

        }

        return null;
    }

    // string representation of this tilesTo1D (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}

























