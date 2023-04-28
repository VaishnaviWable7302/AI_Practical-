public class NQueens {
    private int[][] board;
    private int n;

    public NQueens(int n) {
        this.board = new int[n][n];
        this.n = n;
    }

    public static void main(String[] args) {
        int n = 4;
        NQueens nQueens = new NQueens(n);
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
        return false;
    }

    public void printSolution() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
