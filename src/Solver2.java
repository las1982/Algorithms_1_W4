import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver2 {
    private Comparator<Board> boardComparator = new BoardComparator();
    private ArrayList<Board> solutions = new ArrayList<Board>();
    private MinPQ<Board> pq = new MinPQ<Board>(boardComparator);
    private boolean isSolvable = true;
    private int moves = 0;
    private Iterable<Board> neighbors;
    private Board bestBoard;
    private Board initBoard;

    public Solver2(Board initial) {

        if (initial == null) throw new NullPointerException();
        initBoard = initial;
        bestBoard = initBoard;
        pq.insert(bestBoard);
        solutions.add(bestBoard);
        bestBoard = pq.delMin();
        neighbors = bestBoard.neighbors();
        while (!bestBoard.isGoal()) {
            for (Board neighbor : neighbors) {
                if (moves == 0) pq.insert(neighbor);
                else if (!neighbor.equals(solutions.get(moves - 1))) {
//                    System.out.println(neighbor.toString() + solutions.get(moves - 1).toString());
                    pq.insert(neighbor);
                }
            }
            bestBoard = pq.delMin();
            moves++;
            neighbors = bestBoard.neighbors();
            solutions.add(bestBoard);
        }
    }

    private class BoardComparator implements Comparable<Board> {

        @Override
        public int compareTo(Board board2) {
            Board board1 = this.;
            int cost1 = 0, cost2 = 0;
            cost1 = board1.manhattan();
            cost2 = board2.manhattan();
            cost1 = cost1 + moves;
            cost2 = cost2 + moves;
//            cost1 = cost1 + board1.hamming();
//            cost2 = cost2 + board2.hamming();
            if (cost1 < cost2) return -1;
            else if (cost1 == cost2) return 0;
            else if (cost1 > cost2) return 1;
            else return 0;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable()) return -1;
        return moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return solutions;
    }

    public static void main(String[] args) {

//        int[][] a = {{1, 2, 3},{4, 5, 6}};
//        int[][] b = {{1, 2, 3},{4, 5, 6}};
//        int[] c = {1, 4, 3, 4, 5};
//        System.out.println(Arrays.deepEquals(a, b));
//        System.out.println();

        // for each command-line argument
        for (String filename : args) {

            // read in the board specified in the filename
            In in = new In(filename);
            int n = in.readInt();
            int[][] tiles = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tiles[i][j] = in.readInt();
                }
            }

            // solve the slider puzzle
//            int[][] tiles = new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
            Board initial = new Board(tiles);
            StdOut.println("initial: " + initial.toString());
//            StdOut.println(initial.dimension());
//            StdOut.println(initial.hamming());
//            StdOut.println(initial.manhattan());
//            StdOut.println(initial.isGoal());
//            StdOut.println(initial.twin());
//            for (Board naighbor : initial.neighbors()) {
//                StdOut.println(naighbor.toString());
//            }

            Solver2 solver = new Solver2(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}