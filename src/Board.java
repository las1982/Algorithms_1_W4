import java.util.Arrays;
import java.util.Iterator;

public class Board {
    private int n;
    int[][] tiles;
    private Board[] neighbors;
    private int[][] goalTiles;

    public Board(int[][] blocks) {
        tiles = blocks;
        n = blocks.length;
        goalTiles = new int[n][n];
        for (int i = 0; i < n * n - 1; i++) {
            int row = i / n;
            int col = i - n * row;
            goalTiles[row][col] = i + 1;
        }
        goalTiles[n - 1][n - 1] = 0;
    }

    public int dimension() {
        return n;
    }

    private int getCost(String what) {
        int row, col, goalRow, goalCol, goalI, manhattan = 0, hamming = 0;
        for (int i = 0; i < n * n; i++) {
            row = i / n;
            col = i - n * row;
            if (tiles[row][col] == 0) continue;
            goalI = tiles[row][col] - 1;
            goalRow = goalI / n;
            goalCol = goalI - n * goalRow;
            if (row != goalRow || col != goalCol) {
                manhattan = manhattan + Math.abs(row - goalRow) + Math.abs(col - goalCol);
                hamming++;
            }
        }
        if (what.equals("manhattan")) return manhattan;
        if (what.equals("hamming")) return hamming;
        return 0;
    }

    public int hamming() {
        return getCost("hamming");
    }

    public int manhattan() {
        return getCost("manhattan");
    }

    public boolean isGoal() {
//        return Arrays.deepEquals(tiles, goalTiles);
        return this.equals(new Board(goalTiles));
    }

    public Board twin() {
        if (tiles.length == 1) return this;
        int i = 0, item1 = 0, item2 = 0, row, col;
        int[][] twinTiles = new int [n][n];
        for (i = 0; i < n * n; i++){
            row = i / n;
            col = i - n * row;
            twinTiles[row][col] = tiles[row][col];
        }
        i = 0;
        while (i < n * n - 1) {
            row = i / n;
            col = i - n * row;
            if (col < n - 1) {
                item1 = twinTiles[row][col];
                item2 = twinTiles[row][col + 1];
//System.out.println("i=" + i + " row=" + row + " col=" + col + " item1=" + item1 + " item2=" + item2);
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
        int[][] neighborsTiles, neighborsIndex = new int[4][2];
        int i = 0, row = 0, col = 0, blankRow = 0, blankCol = 0, neighborsLength = 4;
        while (i < n * n) {
            row = i / n;
            col = i - n * row;
            if (tiles[row][col] == 0) {
//System.out.println("i=" + i + " elem:" + tiles[row][col]);
                blankRow = row;
                blankCol = col;
                if (row == 0 || row == n - 1) neighborsLength--;
                if (col == 0 || col == n - 1) neighborsLength--;
                neighborsIndex = new int[][]{{row + 1, col}, {row - 1, col}, {row, col - 1}, {row, col + 1}};
            }
            i++;
        }
//System.out.println("tiles: " + Arrays.toString(tiles[0]) + Arrays.toString(tiles[1]) + Arrays.toString(tiles[2]));
//System.out.println(blankRow + " " + blankCol + " ");
        neighbors = new Board[neighborsLength];
        for (int[] index : neighborsIndex) {
            if (index[0] >= 0 && index[0] < n && index[1] >= 0 && index[1] < n) {
                neighborsTiles = new int[tiles.length][];
                for (i = 0; i < tiles.length; i++) {
                    neighborsTiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
                }
                neighborsTiles[blankRow][blankCol] = tiles[index[0]][index[1]];
                neighborsTiles[index[0]][index[1]] = 0;
                neighbors[neighborsLength-- - 1] = new Board(neighborsTiles);
            }
        }
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new Iterator<Board>() {
                    private int position;
                    public boolean hasNext() {
                        return position != neighbors.length;
                    }
                    public Board next() {
                        if (!hasNext()) throw new java.util.NoSuchElementException();
                        return neighbors[position++];
                    }
                    public void remove() {
                        throw new UnsupportedOperationException("remove operation is forbidden");
                    }
                };
            }
        };
    }

    public boolean equals(Object other) {
        if (other == this) return true;
//        System.out.println("*********" + other.toString());
//        System.out.println("---------" + this.toString());
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return (Arrays.deepEquals(tiles, that.tiles));
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
//        s.append("size: " + n + " manhatten: " + this.manhattan() + " hamming: " + this.hamming() + "\n");
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Board b = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        Board bg = new Board(new int[][]{{0, 3, 1}, {4, 2, 5}, {7, 8, 6}});
//        Board bg1 = new Board(new int[][] {{5, 0, 4}, {2, 3, 8}, {7, 1, 6}});
        System.out.println(b.toString());
        for (Board br : b.neighbors()) {
            System.out.println(br.toString());
        }
        System.out.println(Arrays.toString(bg.tiles[0]) + Arrays.toString(bg.tiles[1]) + Arrays.toString(bg.tiles[2]));
        System.out.println(Arrays.toString(bg.goalTiles[0]) + Arrays.toString(bg.goalTiles[1]) + Arrays.toString(bg.goalTiles[2]));
        System.out.println(bg.isGoal());
        System.out.println(b.equals(bg));

    }
}

























