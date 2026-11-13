package cs112_recursion_eval_assignment;
import java.util.Arrays;

public class RecursionTest {
    public static int checks = 0;

    public static void main(String[] args) {
        testCatalan();
        testPigRollSequences();
        testToadsAndFrogs();
        System.out.println("All " + checks + " checks passed.");
    }

    public static void testCatalan() {
        int[] inputs = {0, 1, 2, 3, 4, 5, 10, 20, 33, 34, 35};
        long[] expected = {
            1L, 1L, 2L, 5L, 14L, 42L, 16796L, 6564120420L,
            212336130412243110L, 812944042149730764L, 3116285494907301262L
        };

        for (int i = 0; i < inputs.length; i++) {
            checkEquals(expected[i], Catalan.c(inputs[i]), "Catalan " + inputs[i]);
        }

        int[] invalid = {-1, Integer.MIN_VALUE, 36, Integer.MAX_VALUE};
        for (int n : invalid) {
            try {
                Catalan.c(n);
                throw new AssertionError("Catalan accepted " + n);
            } catch (IllegalArgumentException e) {
                if (n >= 0) {
                    throw new AssertionError("Wrong exception for " + n);
                }
                checkMessage(e);
            } catch (ArithmeticException e) {
                if (n <= 35) {
                    throw new AssertionError("Wrong exception for " + n);
                }
                checkMessage(e);
            }
        }
    }

    public static void testPigRollSequences() {
        int[] inputs = {0, 1, 2, 3, 4, 5, 6, 10, 20, 30, 40, 49, 50};
        int[] expected = {
            1, 0, 1, 1, 2, 3, 5, 29, 2618, 238415, 21713863,
            1259507301, 1977612004
        };

        for (int i = 0; i < inputs.length; i++) {
            checkEquals(expected[i], PigRollSequences.getNumSequences(inputs[i]),
                    "Pig total " + inputs[i]);
        }

        int[] invalid = {-1, 51, Integer.MIN_VALUE, Integer.MAX_VALUE};
        for (int total : invalid) {
            try {
                PigRollSequences.getNumSequences(total);
                throw new AssertionError("Pig accepted " + total);
            } catch (IllegalArgumentException e) {
                checkMessage(e);
            }
        }
    }

    public static void testToadsAndFrogs() {
        checkBoard("", 'T', 'F');
        checkBoard("", 'F', 'T');
        checkBoard("---", 'T', 'F');
        checkBoard("---", 'F', 'T');
        checkBoard("T", 'T', 'F');
        checkBoard("F", 'F', 'T');
        checkBoard("T-", 'T', 'T');
        checkBoard("-F", 'F', 'F');
        checkBoard("-T", 'T', 'F');
        checkBoard("F-", 'F', 'T');
        checkBoard("TF-", 'T', 'F');
        checkBoard("-TF", 'F', 'T');
        checkBoard("TT-", 'T', 'T');
        checkBoard("-FF", 'F', 'F');
        checkBoard("TF", 'T', 'F');
        checkBoard("TF", 'F', 'T');
        checkBoard("T-TF", 'T', 'T');
        checkBoard("TT---FF", 'T', 'T');
        checkBoard("FF-T-T-", 'F', 'T');
        checkBoard("--FF-TT", 'T', 'F');

        char[][] invalidBoards = {null, {'T', 'X', '-'}, {'t'}, {' '}};
        for (char[] board : invalidBoards) {
            try {
                ToadsAndFrogSolver.getWinner(board, 'T');
                throw new AssertionError("Invalid board was accepted.");
            } catch (IllegalArgumentException e) {
                checkMessage(e);
            }
        }

        char[] board = "TT---FF".toCharArray();
        char[] original = board.clone();
        try {
            ToadsAndFrogSolver.getWinner(board, 'X');
            throw new AssertionError("Invalid player was accepted.");
        } catch (IllegalArgumentException e) {
            checkMessage(e);
        }
        checkUnchanged(original, board);
    }

    public static void checkBoard(String position, char player, char expected) {
        char[] board = position.toCharArray();
        char[] original = board.clone();
        checkEquals(expected, ToadsAndFrogSolver.getWinner(board, player),
                "Board " + position + ", player " + player);
        checkUnchanged(original, board);
        checkEquals(expected, ToadsAndFrogSolver.getWinner(board, player),
                "Repeated call for " + position);
        checkUnchanged(original, board);
    }

    public static void checkEquals(long expected, long actual, String name) {
        if (expected != actual) {
            throw new AssertionError(name + ": expected " + expected + ", got " + actual);
        }
        checks++;
    }

    public static void checkUnchanged(char[] expected, char[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("The original board was changed.");
        }
        checks++;
    }

    public static void checkMessage(RuntimeException e) {
        if (e.getMessage() == null || e.getMessage().isEmpty()) {
            throw new AssertionError("The exception needs an error message.");
        }
        checks++;
    }
}
