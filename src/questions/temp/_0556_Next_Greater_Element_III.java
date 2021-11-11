/**
 *  @author Yunxiang He
 *  @date 02/14/2019
 */

package questions.temp;

/*

Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. 
If no such positive 32-bit integer exists, you need to return -1.


Example 1:
    Input: 12
    Output: 21

Example 2:
    Input: 21
    Output: -1

*/

public class _0556_Next_Greater_Element_III {

    public static void main(String[] args) {
        System.out.println(new _0556_Next_Greater_Element_III().nextGreaterElement(230241));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // From right to left, find the first decreasing digit
    // Swap this digit with the digit just greater than it on the right side
    // Reverse the right side to ASC
    public int nextGreaterElement(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        int i = chs.length - 1;
        while (i > 0 && chs[i - 1] >= chs[i]) {
            --i;
        }
        if (i == 0) {
            return -1;
        } else {
            int pivot = i - 1;
            int idx = smallerThanTarget(chs, i, chs.length, chs[pivot]);
            swap(chs, pivot, idx);
            reverse(chs, i, chs.length - 1);
            return chs.length == 10 && String.valueOf(chs).compareTo(String.valueOf(Integer.MAX_VALUE)) > 0 ? -1 : Integer.parseInt(String.valueOf(chs));
        }
    }

    private int smallerThanTarget(char[] chs, int left, int right, char target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (chs[mid] > target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    private void reverse(char[] chs, int i, int j) {
        while (i < j) {
            swap(chs, i++, j--);
        }
    }

    private void swap(char[] chs, int i, int j) {
        char temp = chs[i];
        chs[i] = chs[j];
        chs[j] = temp;
    }
}
