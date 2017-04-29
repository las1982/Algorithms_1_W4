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

    private void createGoal(){
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

    // number of blocks out of place
    public int hamming() {
        return 1;
    }

    // sum of Manhattan distances between blocks and createGoal
    public int manhattan() {
        return 1;
    }

    public boolean isGoal() {
        return this.equals(goalBoard);
    }

    // a tilesTo1D that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] twinTiles = Arrays.ctiles;
        for (int i = 0; i < n * n - 1; i++) {
            goalTiles[i / n][i - n * i / n] = i + 1;
        }
        goalTiles[n - 1][n - 1] = 0;
        goalBoard = new Board(goalTiles);
        if (tiles.[0][0] != 0 && tiles.[0][1] != 0){

        }
        Board twin = new Board(twinTiles);
        return twin;
    }

    public boolean equals(Object y) {
        Board otherBoard = (Board) y;
        if (tilesTo1D.length != otherBoard.tilesTo1D.length) return false;
        for (int i = 0; i < tilesTo1D.length; i++){
            if (tilesTo1D[i] != otherBoard.tilesTo1D[i]) return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
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