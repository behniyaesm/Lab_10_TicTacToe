import java.util.Scanner;
public class TicTacToe
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X";

    public static void main(String[] args)
    {
        clearBoard();
        Scanner in = new Scanner(System.in);

        while (true) {
            display();
            System.out.println("Player " + currentPlayer + "'s turn");
            int row, col;
            do {
                System.out.print("Enter row [1-3]: ");
                row = in.nextInt() - 1;
                System.out.print("Enter col [1-3]: ");
                col = in.nextInt() - 1;
            } while (!isValidMove(row, col));

            board[row][col] = currentPlayer;

            if (isWin(currentPlayer)) {
                display();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isTie()) {
                display();
                System.out.println("It's a tie!");
                break;
            }
            currentPlayer = (currentPlayer.equals("X") ? "O" : "X");
        }
        in.close();
    }

    private static void display() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                System.out.print(board[r][c].isEmpty() ? " " : board[r][c]);
                if (c < COL - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (r < ROW - 1) {
                System.out.println("---------");
            }
        }
    }

    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL || !board[row][col].equals(" ")) {
            System.out.println("Invalid move, Please try again.");
            return false;
        }
        return true;
    }

    private static void clearBoard() {
        for(int r = 0; r < ROW; r++) {
            for(int c = 0; c < COL; c++) {
                board[r][c] = " ";
            }
        }
    }

    private static boolean isRowWin(String player) {
        for(int r = 0; r < ROW; r++) {
            if (board[r][0].equals(player) && board[r][1].equals(player) && board[r][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isColWin(String player) {
        for(int c = 0; c < COL; c++) {
            if (board[0][c].equals(player) && board[1][c].equals(player) && board[2][c].equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                if (board[r][c].equals(" ")) {
                    return false; //not tie
                }
            }
        }
        return true; //tie
    }

    //check to see if there is a win on the board
    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

}
