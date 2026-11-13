package cs112_recursion_eval_assignment;
public class ToadsAndFrogSolver {
    public static char getWinner(char[] board, char currentPlayer) {
        if (board == null) {
            throw new IllegalArgumentException("board must not be null.");
        }
        if (currentPlayer != 'T' && currentPlayer != 'F') {
            throw new IllegalArgumentException("currentPlayer must be T or F.");
        }
        for (char position : board) {
            if (position != 'T' && position != 'F' && position != '-') {
                throw new IllegalArgumentException("board may contain only T, F, and -.");
            }
        }

        return findWinner(board, currentPlayer);
    }

    public static char findWinner(char[] board, char currentPlayer) {
        int direction = 1;
        char opponent = 'F';
        if (currentPlayer == 'F') {
            direction = -1;
            opponent = 'T';
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i] != currentPlayer) {
                continue;
            }

            int destination = i + direction;
            if (destination < 0 || destination >= board.length) {
                continue;
            }
            if (board[destination] == opponent) {
                destination += direction;
                if (destination < 0 || destination >= board.length) {
                    continue;
                }
            }
            if (board[destination] != '-') {
                continue;
            }

            char[] nextBoard = board.clone();
            nextBoard[i] = '-';
            nextBoard[destination] = currentPlayer;

            if (findWinner(nextBoard, opponent) == currentPlayer) {
                return currentPlayer;
            }
        }

        return opponent;
    }
}
