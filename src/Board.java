public class Board {
    private int n;
    private int[] board;
    private int[][] tiles;
    private Board goalBoard;

    public Board(int[][] blocks) {
        tiles = blocks;
        n = blocks.length;
        board = new int[n * n];
        int i = 0;
        for (int[] block : blocks) {
            for (int node : block) {
                board[i++] = node;
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

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    public boolean equals(Object y) {
        Board otherBoard = (Board) y;
        if (board.length != otherBoard.board.length) return false;
        for (int i = 0; i < board.length; i++){
            if (board[i] != otherBoard.board[i]) return false;
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return null;
    }

    // string representation of this board (in the output format specified below)
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