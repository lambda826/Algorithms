package algorithms.divide_and_conquer;

/**
 * @author Yunxiang He
 * @date Dec 22, 2017 4:48:44 PM
 */

public class IntegerMultiplication {
    public long multiply(long x, long y) {
        int size1 = getSize(x);
        int size2 = getSize(y);

        /** Maximum of lengths of number **/
        int N = Math.max(size1, size2);

        /** for small values directly multiply **/
        if (N < 2) {
            return x * y;
        }

        /** max length divided, rounded up **/
        N = (N / 2);

        /** multiplier **/
        long m = (long) Math.pow(10, N);

        /** compute sub expressions **/
        long b = x / m;
        long a = x - (b * m);
        long d = y / m;
        long c = y - (d * m);
        /** compute sub expressions **/
        long z0 = multiply(a, c);
        long z1 = multiply(a + b, c + d);
        long z2 = multiply(b, d);

        return z0 + ((z1 - z0 - z2) * m) + (z2 * (long) (Math.pow(10, 2 * N)));
    }

    /** Function to calculate length or number of digits in a number **/
    public int getSize(long num) {
        int ctr = 0;
        while (num != 0) {
            ctr++;
            num /= 10;
        }
        return ctr;
    }

    /** Main function **/
    public static void main(String[] args) {
        IntegerMultiplication kts = new IntegerMultiplication();

        long a = 41223;
        long b = 23313;
        long product = kts.multiply(a, b);
        System.out.println("\nProduct : " + product);
        System.out.println("\nProduct : " + (long) (a * b));
    }
}
