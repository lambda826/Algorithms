/**
 *  @author Yunxiang He
 *  @date 03/18/2019
 */

package coding.temp;

/*

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?


Example:
    Input: 3
    Output: 5
    Explanation:
    Given n = 3, there are a total of 5 unique BST's:
       1         3     3      2      1
        \       /     /      / \      \
         3     2     1      1   3      2
        /     /       \                 \
       2     1         2                 3

*/

public class _0096_Unique_Binary_Search_Trees {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // has nothing to do with BST, count the # of the structures of the tree
    // for i nodes, pick a root, compute the # of left subtree and # of right subtree
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; ++i) {
            for (int k = 0; k < i; ++k) {
                dp[i] += dp[k] * dp[i - k - 1];
            }
        }
        return dp[n];
    }
}
