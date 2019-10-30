/**
 *  @author Yunxiang He
 *  @date Jan 17, 2018 5:04:04 AM
 */

package coding.temp;

/*

Implement int sqrt(int x).

Compute and return the square root of x.

x is guaranteed to be a non-negative integer.


Example 1:
Input: 4
Output: 2

Example 2:
Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since we want to return an integer, the decimal part will be truncated.


 */

public class _0069_SqrtX {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int mySqrt_BinraySearch(int x) {
        if (46340 * 46340 <= x) {
            return 46340;
        }
        int lo = 0;
        int hi = 46340;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int y = mid * mid;
            if (y == x) {
                return mid;
            } else if (y < x) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo - 1;
    }

    public static void main(String[] args) {
        _0069_SqrtX test = new _0069_SqrtX();
        System.out.println(test.mySqrt_BinraySearch(7));
    }
}
