package cs112_recursion_eval_assignment;
import java.util.Arrays;

public class PigRollSequences {
    public static int getNumSequences(int turnTotal) {
        if (turnTotal < 0 || turnTotal > 50) {
            throw new IllegalArgumentException("turnTotal must be between 0 and 50.");
        }

        int[] counts = new int[turnTotal + 1];
        Arrays.fill(counts, -1);
        return countSequences(turnTotal, counts);
    }

    public static int countSequences(int remaining, int[] counts) {
        if (remaining == 0) {
            return 1;
        }
        if (counts[remaining] != -1) {
            return counts[remaining];
        }

        int total = 0;
        for (int roll = 2; roll <= 6; roll++) {
            if (roll <= remaining) {
                total += countSequences(remaining - roll, counts);
            }
        }

        counts[remaining] = total;
        return total;
    }
}
