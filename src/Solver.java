import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class Solver {
    private Comparator<Board> boardComparator = new BoardComparator();
    private MinPQ<Board> solutions = new MinPQ<Board>(boardComparator);
    private MinPQ<Board> pq = new MinPQ<Board>(boardComparator);
    private boolean isSolvable = true;
    private int moves = 0;

    public Solver(Board initial) {

        Board bestBoard;
        boolean isGoal = initial.isGoal();
        Iterable<Board> neighbors = initial.neighbors();
        while (!isGoal) {
            for (Board neighbor : neighbors) {
                if (neighbor.equals(initial.twin())) {
                    isSolvable = false;
                    return;
                }
                if (initial.equals(neighbor)) continue;
                pq.insert(neighbor);
            }
            bestBoard = pq.delMin();
            neighbors = bestBoard.neighbors();
            solutions.insert(bestBoard);
            moves++;
            isGoal = bestBoard.isGoal();
        }
    }

    private class BoardComparator implements Comparator<Board> {

        @Override
        public int compare(Board board1, Board board2) {
            int man1 = board1.manhattan();
            man1 = man1 + moves;
            int man2 = board2.manhattan();
            man2 = man2 + moves;
//            int ham1 = board1.hamming() + moves;
//            int ham2 = board2.hamming() + moves;
            if (man1 < man2) return -1;
            else if (man1 == man2) return 0;
            else if (man1 > man2) return 1;
            else return 0;
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        if (!isSolvable()) return -1;
        return solutions.size();
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        return solutions;
//        return new Iterable<Board>() {
//            public Iterator<Board> iterator() {
//                return new Iterator<Board>() {
//                    private int position;
//                    public boolean hasNext() {
//                        return position != solutions.size() && solutions.[position] != null;
//                    }
//                    public Board next() {
//                        if (!hasNext()) throw new java.util.NoSuchElementException();
//                        return solutions[position++];
//                    }
//                    public void remove() {
//                        throw new UnsupportedOperationException("remove operation is forbidden");
//                    }
//                };
//            }
//        };
//
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

            Solver solver = new Solver(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}