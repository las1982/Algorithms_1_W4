import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class LocalRun {

    public static void main(String[] args) {

//        int[][] a = {{1, 2, 3},{4, 5, 6}};
//        int[][] b = {{1, 2, 3},{4, 5, 6}};
//        int[] c = {1, 4, 3, 4, 5};
//        System.out.println(Arrays.deepEquals(a, b));
//        System.out.println();

        // for each command-line argument
//        int[][] tiles = new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}; // 4
        int[][] tiles = new int[][]{{1, 2, 3}, {0, 7, 6}, {5, 4, 8}}; // 7
//        int[][] tiles = new int[][]{{1, 0, 2}, {7, 5, 4}, {8, 6, 3}}; // 11
//        int[][] tiles = new int[][]{{1, 0}, {2, 3}};
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
        StdOut.println("steps: " + solver.moves());
        StdOut.println(solver.solution().toString());
//        StdOut.println("solution: " + solver.solution().toString());
    }
}
