/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 07:28
 */

package coding.leetcode.temp;

/*

Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. 
If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.

*/

public class ___0686_Repeated_String_Match {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int repeatedStringMatch(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return -1;
        }

        int aLength = a.length();
        int bLength = b.length();
        String augmentedA = a + a;
        int index = augmentedA.indexOf(b.substring(0, Math.min(aLength, bLength)));
        if (index == -1) {
            return -1;
        }
        int count = 1;
        int bIndex = 0;
        while (bIndex < bLength) {
            int aCount = 0;
            int aIndex = index;
            while (aCount < aLength && bIndex < bLength) {
                if (a.charAt(aIndex) != b.charAt(bIndex)) {
                    return -1;
                }
                aCount++;
                bIndex++;
                aIndex = (aIndex + 1) % aLength;
                if (aIndex == 0 && bIndex < bLength) {
                    count++;
                }
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 最后一次repeat之后，如果A的length大于B的length即可停止loop
    public int repeatedStringMatch2(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        int count = 1;
        while (sb.length() < B.length()) {
            count++;
            sb.append(A);
        }
        if (sb.indexOf(B) >= 0) {
            return count;
        }
        sb.append(A);
        if (sb.indexOf(B) >= 0) {
            return count + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new ___0686_Repeated_String_Match().repeatedStringMatch("abababaaba", "aabaaba"));
    }
}
