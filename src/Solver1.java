import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.Iterator;

public class Solver1 {
    private Board[] solutions = new Board[2];
    private boolean isSolvable = true;
    private int moves = 0;

    public Solver1(Board initial) {

        MinPQ<Board> pq = new MinPQ<Board>(new BoardComparator());
        pq.insert(initial);
        solutions[moves] = initial;
        Board bestBoard;
        boolean isGoal = initial.isGoal();
        Iterable<Board> neighbors = initial.neighbors();
        while (!isGoal) {
//            int n = 0;
            for (Board neighbor : neighbors) {
//System.out.println("neighbor: " + ++n + "\n" + neighbor.toString());
//                if (neighbor.equals(initial.twin())) {
//                    isSolvable = false;
//                    return;
//                }
                pq.insert(neighbor);
            }
            bestBoard = pq.min();
System.out.println("best board: \n" + bestBoard.toString());
            neighbors = bestBoard.neighbors();
            isGoal = bestBoard.isGoal();
//System.out.println(pq.size());
//System.out.println(bestBoard.toString());
//            if (moves == 15) break;
        }
        while (true){
            if (moves == solutions.length - 1) resizeSolutions();
            if (!pq.min().equals(initial)){
                solutions[++moves] = pq.delMin();
            } else {
                break;
            }
        }
    }

    private void resizeSolutions() {
        Board[] tempSolutions = new Board[solutions.length * 2];
        for (int i = 0; i < solutions.length; i++) {
            tempSolutions[i] = solutions[i];
        }
        solutions = tempSolutions;
    }

    private class BoardComparator implements Comparator<Board> {

        @Override
        public int compare(Board board1, Board board2) {
            int man1 = board1.manhattan() + moves;
            int man2 = board2.manhattan() + moves;
            int ham1 = board1.hamming() + moves;
            int ham2 = board2.hamming() + moves;
            if (man1 < man2) return -1;
            else if (man1 == man2 && ham1 < ham2) return -1;
            else if (man1 > man2) return 1;
            else if (man1 == man2 && ham1 > ham2) return 1;
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
        return new Iterable<Board>() {
            public Iterator<Board> iterator() {
                return new Iterator<Board>() {
                    private int position;
                    public boolean hasNext() {
                        return position != solutions.length;
                    }
                    public Board next() {
                        if (!hasNext()) throw new java.util.NoSuchElementException();
                        return solutions[position++];
                    }
                    public void remove() {
                        throw new UnsupportedOperationException("remove operation is forbidden");
                    }
                };
            }
        };

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

            Solver1 solver = new Solver1(initial);
            StdOut.println(filename + ": " + solver.moves());
        }
    }
}