package cs112_recursion_eval_assignment;
public class Catalan {
    public static long c(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be nonnegative.");
        }
        if (n > 35) {
            throw new ArithmeticException("The Catalan number is too large for a long.");
        }
        if (n == 0) {
            return 1;
        }

        long previous = c(n - 1);
        long numerator = 4L * n - 2;
        long denominator = n + 1;
        long divisor = gcd(numerator, denominator);

        numerator /= divisor;
        denominator /= divisor;
        return (previous / denominator) * numerator;
    }

    public static long gcd(long a, long b) {
        while (b != 0) {
            long remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }
}
