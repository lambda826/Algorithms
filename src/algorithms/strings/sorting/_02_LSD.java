/**
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
package algorithms.strings.sorting;

import java.util.Arrays;

/*

Proposition B. 
LSD string sort stably sorts fixed-length strings.

Proof: 
This fact depends crucially on the key-indexed counting implementation being stable, as indicated in Proposition A. 
After sorting keys on their i trailing characters (in a stable manner), 
we know that any two keys appear in proper order in the array (considering just those characters) either because the first of their i trailing characters is different, in which case the sort on that character puts them in order, 
or because the first of their ith trailing characters is the same, in which case they are in order because of stability (and by induction, for i-1).

Another way to state the proof is to think about the future: 
if the characters that have not been examined for a pair of keys are identical, 
any difference between the keys is restricted to the characters already examined, 
so the keys have been properly ordered and will remain so because of stability.

If, on the other hand, the characters that have not been examined are different, 
the characters already examined do not matter, and a later pass will correctly order the pair based on the more significant differences.

Proposition B (continued). 
LSD string sort uses ~7WN  3WR array accesses and extra space proportional to N R to sort N items whose keys are W-character strings taken from an R-character alphabet.

Proof: 
The method is W passes of key-indexed counting, except that the aux[] array is initialized just once. The total is immediate from the code and Proposition A.

For typical applications, R is far smaller than N, so Proposition B implies that the total running time is proportional to WN. 
An input array of N strings that each have W characters has a total of WN characters, so the running time of LSD string sort is linear in the size of the input.

 */

public class _02_LSD {
    private static final int BITS_PER_BYTE = 8;

    // do not instantiate
    private _02_LSD() {
    }

    /**
     * Rearranges the array of W-character strings in ascending order.
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(String[] a, int w) {
        int n = a.length;
        // extend ASCII alphabet size
        int R = 256;
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }

    /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static void sort(int[] a) {
        // each int is 32 bits 
        final int BITS = 32;
        // each bytes is between 0 and 255
        final int R = 1 << BITS_PER_BYTE;
        // 0xFF
        final int MASK = R - 1;
        // each int is 4 bytes
        final int w = BITS / BITS_PER_BYTE;

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {

            // compute frequency counts
            int[] count = new int[R + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                count[c + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            // 
            // explanation:
            // The reason for this is: since you are using int, so the most significant bit is the sign bit.
            // Therefore numbers with the most significant byte in the range 0x80-0xFF are negative numbers, 
            // so should be placed before positive numbers, which has the most significant byte in the range of 0x00-0x7F.
            //
            // If you are asking how the code block achieves it, here is a brief idea:
            // Since you understood how data is moved, so I assume you understood what does count[] do in the entire code. 
            // In the code block, R is the upper bound, which is 0xFF + 1, and R / 2 is 0x7F + 1. 
            // Therefore count[R] - count[R / 2] is the total number in the range of 0x80 to 0xFF. 
            // Therefore by adding a shift of count[R] - count[R / 2] to count[0 .. R / 2] and subtracting it from count[R / 2 .. R] will help numbers ranged in 0x00 to 0x7F have higher count value than numbers ranged in 0x80 to 0xFF, 
            // which results in 0x80-0xFF comes before 0x00-0x7F ultimately.
            if (d == w - 1) {
                int shift1 = count[R] - count[R / 2];
                int shift2 = count[R / 2];
                for (int r = 0; r < R / 2; r++) {
                    count[r] += shift1;
                }
                for (int r = R / 2; r < R; r++) {
                    count[r] -= shift2;
                }
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & MASK;
                aux[count[c]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * Reads in a sequence of fixed-length strings from standard input;
     * LSD radix sorts them; and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //        String[] a = StdIn.readAllStrings();
        //        int n = a.length;
        //
        //        // check that strings have fixed length
        //        int w = a[0].length();
        //        for (int i = 0; i < n; i++)
        //            assert a[i].length() == w : "Strings must have fixed length";
        //
        //        // sort the strings
        //        sort(a, w);
        //
        //        // print results
        //        for (int i = 0; i < n; i++)
        //            StdOut.println(a[i]);

        String[] a = { "4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750", "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723", };
        int[] a2 = { 1233, 231, 23, 1, 3232323, -211, 2333, 56756, 72546, 245767, 1231, Integer.MAX_VALUE, 52352354, 2365235 };
        int w = 7;
        sort(a, w);
        sort(a2);
    }
}
