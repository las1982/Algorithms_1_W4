public class Board {
    private int dimension;
    private int boardLen;
    private int[] board;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        dimension = blocks.length;
        boardLen = (int) Math.pow(dimension, 2);
        board = new int[boardLen];
        int i = 0;
        for (int[] block : blocks) {
            for (int node : block) {
                board[i++] = node;
            }
        }
    }

    // board dimension n
    public int dimension() {
        return dimension;
    }

    // number of blocks out of place
    public int hamming() {
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
    }

    // is this board the goal board?
    public boolean isGoal() {
        int[] goal = new int[dimension()];
        for (int i = 0; i < boardLen - 1; i++) {
            if (board[i] == i + 1) continue;
            else return false;
        }
        if (board[boardLen - 1] == 0) return true;
        else return false;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
    }

    // does this board equal y?
    public boolean equals(Object y) {
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
    }

    // unit tests (not graded)
    public static void main(String[] args) {
    }
}