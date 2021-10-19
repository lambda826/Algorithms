/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-12
 */

package coding.problems;

public class Budget_Shopping {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static int budgetShopping_DP(int n, int[] bundleQuantities, int[] bundleCosts) {
        int[] dp = new int[n + 1];
        for (int m = 1; m <= n; m++) {
            for (int i = 0; i < bundleQuantities.length; i++) {
                if (bundleCosts[i] <= m) {
                    dp[m] = Math.max(dp[m], dp[m - bundleCosts[i]] + bundleQuantities[i]);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        int[] bundleQuantities1 = { 10 };
        int[] bundleCosts1 = { 2, };
        System.out.println(budgetShopping_DP(n, bundleQuantities1, bundleCosts1));
    }

}
