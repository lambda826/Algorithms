/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

/*

There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.


Example:
    Input: n = 3, k = 2
    Output: 6
    Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
    
                post1  post2  post3      
     -----      -----  -----  -----       
       1         c1     c1     c2 
       2         c1     c2     c1 
       3         c1     c2     c2 
       4         c2     c1     c1  
       5         c2     c1     c2
       6         c2     c2     c1


Note:
    n and k are non-negative integers.

*/

public class _0276_Paint_Fence {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Sum = same + differ
    public int numWays(int n, int k) {
        if (n == 0) {
            return 0;
        }
        int same = 0;
        int differ = k;
        int sum = same + differ;
        for (int i = 1; i < n; i++) {
            same = differ;
            differ = sum * (k - 1);
            sum = same + differ;
        }
        return sum;
    }
}
