import java.util.Scanner;

public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final String[][] board = new String[ROWS][COLS];

    public static void main(String[] args) {
        Scanner consoleIn = new Scanner(System.in);
        clearBoard();
        board[0][0] = "O"; board[0][1] = "O"; board[0][2] = "X";
        board[1][0] = "X"; board[1][1] = "X"; board[1][2] = "O";
        board[2][0] = "O"; board[2][1] = "X"; board[2][2] = " ";
        displayBoard();
        System.out.println("win: " + isWin("X"));
        System.out.println("tie: " + isTie());
    }

    private static void clearBoard() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = " ";
            }
        }
    }

    private static void displayBoard() {
        /*
        This method utilizes the fact that board value is only single character String.
        If this were not the case, then formatting would break.
        Ideally, I would make board char[][] to enforce this fact (or use integers to represent values),
        but as board is supposed to be a String[][], I will just assume that each value is just a single character String,
        because I don't want to do a lot of String manipulation without StringBuilder.
        This function is also adapted to display a tic-tac-toe grid of any size, so it is expandable if one wanted to make
        a 4x4 tic-tac-toe game or some other variant, with a minimum size of 3x3.
        You should be able to actually play a game of N x M tic-tac-toe properly in this code's present form.
        Mostly I realize that this is overkill, but it was fun to program.
        */

        System.out.print("\n\n");

        for (int row = 0; row < ROWS; row++) {

            repeatColPatternExceptLast("       |", "       ");
            System.out.print("\n");

            //This cannot be repeatColPatternExceptLast, because the pattern is not constant
            for (int col = 0; col < COLS; col++) {
                String cellValue = board[row][col];

                if (!isLastCol(col)) {
                    System.out.print("   " + cellValue + "   |");
                } else {
                    //last column
                    System.out.print("   " + cellValue + "   ");
                }

            }
            System.out.print("\n");

            if (!isLastRow(row)) {
                repeatColPatternExceptLast("_______|", "_______");
                System.out.print("\n");
            } else {
                repeatColPatternExceptLast("       ,", "       ");
            }
        }

        System.out.print("\n\n");
    }

    private static void repeatColPatternExceptLast(String repeatText, String alternative) {
        for (int col = 0; col < COLS; col++) {
            if (!isLastCol(col)) {
                System.out.print(repeatText);
            } else {
                //last column
                System.out.print(alternative);
            }
        }
    }

    private static boolean isLastCol(int col) {
        return col == COLS - 1;
    }

    private static boolean isLastRow(int row) {
        return row == ROWS - 1;
    }

    private static boolean isValidMove(int row, int col) {
        return board[row][col].equals(" ");
    }

    private static boolean isWin(String player) {
        return isColWin(player) || isRowWin(player) || isDiagonalWin(player);
    }

    private static boolean isColWin(String player) {
        for (int col = 0; col < ROWS; col++) {
            boolean allMatch = true;
            for (int row = 0; row < COLS; row++) {
                if (!board[row][col].equals(player)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return true;
            }
        }

        //no col win found
        return false;
    }

    private static boolean isRowWin(String player) {
        for (int row = 0; row < ROWS; row++) {
            boolean allMatch = true;
            for (int col = 0; col < COLS; col++) {
                if (!board[row][col].equals(player)) {
                    allMatch = false;
                    break;
                }
            }
            if (allMatch) {
                return true;
            }
        }

        //no row win found
        return false;
    }

    private static boolean isDiagonalWin(String player) {

        //Note that this method works best if ROWS and COLS are the same
        //Otherwise, to prevent errors, a diagonal win is forced to be impossible (partly because it is impossible)
        if (ROWS != COLS) {
            return false;
        }

        boolean allMatch = true;

        //Test top-left to bottom-right
        for (int rowCol = 0; rowCol < ROWS; rowCol++) {
            if (!board[rowCol][rowCol].equals(player)) {
                allMatch = false;
                break;
            }
        }

        if (allMatch) return true;


        //Test top-right to bottom-left
        allMatch = true;
        for (int rowCol = ROWS - 1; rowCol >= 0; rowCol--) {
            if (!board[ROWS - 1 - rowCol][rowCol].equals(player)) {
                allMatch = false;
                break;
            }
        }

        if (allMatch) return true;

        //no diagonal win found
        return false;
    }

    private static boolean isTie() {
        //This method assumes the players are named X and O
        boolean hasX = false;
        boolean hasO = false;

        //Test if it is possible to win given the board configuration
        //rows
        for (int row = 0; row < ROWS; row++) {
            hasX = false;
            hasO = false;
            for (int col = 0; col < COLS; col++) {
                if (board[row][col].equals("X")) hasX = true;
                if (board[row][col].equals("O")) hasO = true;
            }
            if (!hasX || !hasO) return false;
        }

        //cols
        for (int col = 0; col < COLS; col++) {
            hasX = false;
            hasO = false;
            for (int row = 0; row < ROWS; row++) {
                if (board[row][col].equals("X")) hasX = true;
                if (board[row][col].equals("O")) hasO = true;
            }
            if (!hasX || !hasO) return false;
        }

        //test top-left to bottom-right
        hasX = false;
        hasO = false;
        for (int rowCol = 0; rowCol < ROWS; rowCol++) {
            if (!board[rowCol][rowCol].equals("X")) hasX = true;
            if (!board[rowCol][rowCol].equals("O")) hasO = true;
        }

        if (!hasX || !hasO) return false;

        //test top-right to bottom-left
        hasX = false;
        hasO = false;
        for (int rowCol = ROWS - 1; rowCol >= 0; rowCol--) {
            if (!board[ROWS - 1 - rowCol][rowCol].equals("X")) hasX = true;
            if (!board[ROWS - 1 - rowCol][rowCol].equals("O")) hasO = true;
        }

        if (!hasX || !hasO) return false;

        //There is a tie - either the board is completely filled and no more moves can be made, or it is impossible to win with any extra moves.
        return true;
    }
}