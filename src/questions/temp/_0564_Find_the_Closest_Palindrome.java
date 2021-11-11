/**
 *  @author Yunxiang He
 *  @date 03/03/2019
 */

package questions.temp;

/*

Given an integer n, find the closest integer (not including itself), which is a palindrome.
The 'closest' is defined as absolute difference minimized between two integers.


Example 1:
    Input: "123"
    Output: "121"


Note:
    The input n is a positive integer represented by string, whose length will not exceed 18.
    If there is a tie, return the smaller one as answer.

*/

public class _0564_Find_the_Closest_Palindrome {

    public static void main(String[] args) {
        _0564_Find_the_Closest_Palindrome test = new _0564_Find_the_Closest_Palindrome();
        System.out.println(test.nearestPalindromic("9"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String nearestPalindromic(String n) {
        if (n.equals("0")) {
            return "1";
        }
        if (n.length() == 1) {
            return Integer.toString(Integer.parseInt(n) - 1);
        }
        int mid = n.length() / 2;
        long origin = Long.parseLong(n);
        Long leftHalf = origin / (long) Math.pow(10, mid);
        StringBuilder lowerBound = new StringBuilder(Long.toString(leftHalf - 1));
        StringBuilder sameRange = new StringBuilder(Long.toString(leftHalf));
        StringBuilder upperBound = new StringBuilder(Long.toString(leftHalf + 1));
        boolean missing9 = lowerBound.length() < Long.toString(leftHalf).length() || leftHalf == 1;
        lowerBound.append(mirror(lowerBound.toString(), mid - (missing9 ? 1 : 0)));
        if (missing9) {
            lowerBound.append("9");
        }
        String l = Long.toString(Long.parseLong(lowerBound.toString()));
        String m = sameRange.append(mirror(sameRange.toString(), mid)).toString();
        String r = upperBound.append(mirror(upperBound.toString(), mid)).toString();

        long less = Math.abs(Long.parseLong(l) - origin);
        long same = Math.abs(Long.parseLong(m) - origin);
        long more = Math.abs(Long.parseLong(r) - origin);

        if (same == 0 || same >= less || same > more) {
            return less <= more ? l : r;
        } else {
            return m;
        }
    }

    private String mirror(String s, int len) {
        int cut = s.length() < len ? 0 : s.length() - len;
        return new StringBuilder(s).reverse().substring(cut, s.length());
    }
}
