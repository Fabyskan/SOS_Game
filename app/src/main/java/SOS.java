import java.util.InputMismatchException;
import java.util.Scanner;

public class SOS {

    public static void main(String[] args) {
        printBoard(getExample());

        Entry[][] newBoard = new Entry[3][3];
        while (!boardFull(newBoard)) {
            printBoard(newBoard);
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Bitte geben Sie eine Reihe ein: ");
                int rowNum = scanner.nextInt();
                if (rowNum > newBoard.length - 1 || rowNum < 0) {
                    throw new ArrayIndexOutOfBoundsException("Out of Bounds. Board has only range of 0 - " + (newBoard.length - 1) + " & 0 - " + (newBoard.length - 1));
                }
                System.out.println("Bitte geben Sie eine Spalte ein: ");
                int colNum = scanner.nextInt();
                if (colNum > newBoard.length - 1 || colNum < 0) {
                    throw new ArrayIndexOutOfBoundsException("Out of Bounds. Board has only range of 0 - " + (newBoard.length - 1) + " & 0 - " + (newBoard.length - 1));
                }
                System.out.println("Bitte geben Sie einen Spielstein an ('S' oder 'O') : ");
                char entryChar = scanner.next().toUpperCase().charAt(0);
                if (entryChar != 'S' && entryChar != 'O') {
                    throw new InputMismatchException("Entry must be 'O' or 'S'");
                }
                System.out.println("Eingabe erfolgreich");
                newBoard[rowNum][colNum] = Entry.fromDisplay(entryChar);
            } catch (InputMismatchException e) {
                System.out.println("Wrong Entry!");
                System.out.println("_*_*_*_*_*_*_*_*_*_*_*_*_");
                scanner.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
        }
    }

    public static Entry[][] getExample() {
        char[][] example = {
                {' ', ' ', ' ', ' ', ' ', 'S'},
                {' ', ' ', 'S', ' ', '$', ' '},
                {' ', 'O', ' ', 'S', '0', ' '},
                {' ', 'S', ' ', ' ', '$', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'S', ' ', 'O'}
        };

        Entry[][] board = new Entry[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (example[i][j] != ' ') {
                    board[i][j] = Entry.fromDisplay(example[i][j]);
                } else
                    board[i][j] = null;
            }
        }
        return board;
    }

    /**
     * Überprüft das Board auf Richtigkeit (Mindestgröße und Symmetrie)
     *
     * @param board Das zu prüfende Board
     */
    public static void checkBoard(Entry[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Board is empty");
        }
        if (board.length < 4) { //Für den Test auf 4 ändern seufz
            throw new IllegalArgumentException("The board must be 3x3 at least!");
        }
        for (int i = 0; i < board.length; i++) {
            if (board[i] == null) {
                throw new IllegalArgumentException("Row!" + i + "is null!");
            }
            if (board.length != board[i].length) {
                throw new IllegalArgumentException("The board must be a square!");
            }
        }
    }

    public static void printBoard(Entry[][] board) {
        checkBoard(board);
        System.out.print("  ");
        for (int i = 0; i < board.length; i++) {

            System.out.print(" " + i);
        }
        System.out.println();
        plusMinus(board);
        for (int i = 0; i < board.length; i++) {
            System.out.print(i);
            System.out.print(" ");
            for (int j = 0; j < board.length; j++) {
                System.out.print("|");
                switch (board[i][j]) {
                    case null -> System.out.print(" ");
                    case S_SCORED -> System.out.print("$");
                    case S_UNSCORED -> System.out.print("S");
                    case O_SCORED -> System.out.print("0");
                    case O_UNSCORED -> System.out.print("O");
                }
            }
            System.out.println("|");
            plusMinus(board);
        }
    }

    /**
     * Generiert abwechselnd + und -
     *
     * @param board Board bei dessen Ausgabe + und - eingebaut werden soll
     */
    public static void plusMinus(Entry[][] board) {
        System.out.print("  ");
        for (int k = 0; k < 2 * board.length; k++) {
            if (k % 2 == 0) System.out.print("+");
            else System.out.print("-");
        }
        System.out.println("+");
    }

    /**
     * Prüft, ob das Board voll ist
     *
     * @param board Das zu prüfende Board
     * @return True, wenn es noch Platz gibt, False, wenn das Board voll ist
     */
    public static boolean boardFull(Entry[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            throw new IllegalArgumentException("Spitzbub! kein leeres Board!");
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == null) return false;
            }
        }
        return true;
    }


    public static int move(Entry[][] board, Entry entry, int row, int col) {
        checkBoard(board);
        if (entry != Entry.S_UNSCORED && entry != Entry.O_UNSCORED) {
            throw new IllegalArgumentException("Entry must be 'S' or 'O'");
        }
        if (row > board.length - 1 || row < 0) {
            throw new IllegalArgumentException("Fuck you");
        }
        if (col > board.length - 1 || col < 0) {
            throw new IllegalArgumentException("Fuck me");
        }
        if (board[row][col] != null) {
            throw new IllegalArgumentException("Da sitzt scho was");
        }
        board[row][col] = entry;
        int score = 0;
        if (entry == Entry.O_UNSCORED) {
            if (board[row - 1][col] == Entry.S_UNSCORED && board[row + 1][col] == Entry.S_UNSCORED) {
                score += 1;
                board[row - 1][col] = Entry.S_SCORED;
                board[row + 1][col] = Entry.S_SCORED;
                board[row][col] = Entry.O_SCORED;
            }
            if (board[row - 1][col + 1] == Entry.S_UNSCORED && board[row + 1][col - 1] == Entry.S_UNSCORED) {
                score += 1;
                board[row - 1][col + 1] = Entry.S_SCORED;
                board[row + 1][col - 1] = Entry.S_SCORED;
                board[row][col] = Entry.O_SCORED;
            }
            if (board[row - 1][col - 1] == Entry.S_UNSCORED && board[row + 1][col + 1] == Entry.S_UNSCORED) {
                score += 1;
                board[row - 1][col - 1] = Entry.S_SCORED;
                board[row + 1][col + 1] = Entry.S_SCORED;
                board[row][col] = Entry.O_SCORED;
            }
            if (board[row][col + 1] == Entry.S_UNSCORED && board[row][col - 1] == Entry.S_UNSCORED) {
                score += 1;
                board[row][col + 1] = Entry.S_SCORED;
                board[row][col - 1] = Entry.S_SCORED;
                board[row][col] = Entry.O_SCORED;
            }
        }

        if (entry == Entry.S_UNSCORED) {
            if ((safeGet(board, row - 1, col) == Entry.O_UNSCORED || safeGet(board, row - 1, col) == Entry.O_SCORED) &&
                (safeGet(board, row - 2, col) == Entry.S_UNSCORED ||  safeGet(board, row - 2, col) == Entry.S_SCORED)) {

                score += 1;
                board[row - 1][col] = Entry.O_SCORED;
                board[row - 2][col] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row + 1, col) == Entry.O_UNSCORED || safeGet(board, row + 1, col) == Entry.O_SCORED) &&
                (safeGet(board, row + 2, col) == Entry.S_UNSCORED || safeGet(board, row + 2, col) == Entry.S_SCORED)) {

                score += 1;
                board[row + 1][col] = Entry.O_SCORED;
                board[row + 2][col] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row, col - 1) == Entry.O_UNSCORED || safeGet(board, row, col - 1) == Entry.O_SCORED) &&
                (safeGet(board, row, col - 2) == Entry.S_UNSCORED || safeGet(board, row, col - 2) == Entry.S_SCORED)) {

                score += 1;
                board[row][col - 1] = Entry.O_SCORED;
                board[row][col - 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row, col + 1) == Entry.O_UNSCORED || safeGet(board, row, col + 1) == Entry.O_SCORED) &&
                (safeGet(board, row, col + 2) == Entry.S_UNSCORED ||  safeGet(board, row, col + 2) == Entry.S_SCORED)) {

                score += 1;
                board[row][col + 1] = Entry.O_SCORED;
                board[row][col + 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row + 1, col - 1) == Entry.O_UNSCORED || safeGet(board, row + 1, col - 1) == Entry.O_SCORED) &&
                (safeGet(board, row + 2, col - 2) == Entry.S_UNSCORED || safeGet(board, row + 2, col - 2) == Entry.S_SCORED)) {

                score += 1;
                board[row+1][col - 1] = Entry.O_SCORED;
                board[row+2][col - 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row + 1, col + 1) == Entry.O_UNSCORED || safeGet(board, row + 1, col + 1) == Entry.O_SCORED) &&
                (safeGet(board, row + 2, col + 2) == Entry.S_UNSCORED || safeGet(board, row + 2, col + 2) == Entry.S_SCORED)) {

                score += 1;
                board[row+1][col + 1] = Entry.O_SCORED;
                board[row+2][col + 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row - 1, col + 1) == Entry.O_UNSCORED || safeGet(board, row - 1, col + 1) == Entry.O_SCORED) &&
                (safeGet(board, row - 2, col + 2) == Entry.S_UNSCORED || safeGet(board, row - 2, col + 2) == Entry.S_SCORED)) {

                score += 1;
                board[row-1][col + 1] = Entry.O_SCORED;
                board[row-2][col + 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
            if ((safeGet(board, row - 1, col - 1) == Entry.O_UNSCORED || safeGet(board, row - 1, col - 1) == Entry.O_SCORED) &&
                (safeGet(board, row - 2, col - 2) == Entry.S_UNSCORED || safeGet(board, row - 2, col - 2) == Entry.S_SCORED)) {

                score += 1;
                board[row-1][col - 1] = Entry.O_SCORED;
                board[row-2][col - 2] = Entry.S_SCORED;
                board[row][col] = Entry.S_SCORED;
            }
        }
        return score;
    }

    private static Entry safeGet(Entry[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return null;
        }
        return board[row][col];
    }

}