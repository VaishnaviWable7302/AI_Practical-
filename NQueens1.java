import java.util.PriorityQueue;

public class NQueens1 {
    private int[][] board;
    private int n;

    public NQueens1(int n) {
        this.board = new int[n][n];
        this.n = n;
    }

    public static void main(String[] args) {
        int n = 4;
        NQueens1 nQueens = new NQueens1(n);
        nQueens.solve(n);
        nQueens.printSolution();
    }

    public boolean isSafe(int row, int col) {
        // Check if there is a queen in the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }
        // Check if there is a queen in the upper left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        // Check if there is a queen in the upper right diagonal
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 1) {
                return false;
            }
        }
        return true;
    }

    public boolean solve(int row) {
        if (row == n) {
            return true;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                if (solve(row + 1)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return true;
    }

    public void printSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.add(new Node(-1, -1, 0, null));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.row == n - 1) {
                Node sol = node;
                while (sol != null) {
                    board[sol.row][sol.col] = 1;
                    sol = sol.parent;
                }
                return;
            }
            for (int col = 0; col < n; col++) {
                if (isSafe(node.row + 1, col)) {
                    Node child = new Node(node.row + 1, col, node.level + 1, node);
                    pq.add(child);
                }
            }
        }
    }

    private class Node implements Comparable<Node> {
        int row;
        int col;
        int level;
        Node parent;

        public Node(int row, int col, int level, Node parent) {
            this.row = row;
            this.col = col;
            this.level = level;
            this.parent = parent;
        }

        public int compareTo(Node other) {
            if (this.level == other.level) {
                return Integer.compare(this.col, other.col);
            } else {
                return Integer.compare(other.level, this.level);
            }
        }
    }
}
