/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package questions.company.amazon.debug;

public class Occurrence {

    public static int countOccurrence(int[] arr, int value) {
        int i = 0, count = 0, len = arr.length;
        while (i < len) {
            if (arr[i] == value) {
                count += 1;
            }
            i++; ///////////////
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countOccurrence(new int[] { 1, 3, 2, 1 }, 1));
    }
}
