/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-12
 */

package questions.problems;

public class Flower {

    public static int maxProfit(int[] flowers, int p, int q) {
        if (flowers == null || flowers.length <= 1) {
            return 0;
        }
        int[] dp = new int[flowers.length];
        // 10, 01 : p
        // 111 : q
        dp[1] = flowers[0] == 0 && flowers[1] == 1 || flowers[0] == 1 && flowers[1] == 0 ? p : 0;
        if (flowers.length > 2) {
            dp[2] = dp[1];
            dp[2] = flowers[1] == 0 && flowers[2] == 1 || flowers[1] == 1 && flowers[2] == 0 ? p : 0;
            if (p < q && (flowers[1] == 1 && flowers[1] == 1 && flowers[2] == 1)) {
                dp[2] = q;
            }
            for (int i = 3; i < dp.length; i++) {
                if (flowers[i] == 1) {
                    if ((flowers[i] == 1) && flowers[i - 1] == 1 && flowers[i - 2] == 1) {
                        dp[i] = Math.max(dp[i - 3] + q, dp[i - 1]);
                    }
                    if ((flowers[i] == 1) && flowers[i - 1] == 0) {
                        dp[i] = Math.max(dp[i - 2] + p, dp[i - 1]);
                    }
                } else if (flowers[i] == 0 && flowers[i - 1] == 1) {
                    dp[i] = Math.max(dp[i - 2] + p, dp[i - 1]);
                }
                dp[i] = Math.max(dp[i], dp[i - 1]);
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] { 0, 1, 1, 1, 1 };
        System.out.println(maxProfit(nums1, 3, 2));
        //        
        int[] nums2 = new int[] { 1, 1, 1, 0, 1, 1, 1 };
        System.out.println(maxProfit(nums2, 3, 2));
        System.out.println(maxProfit(nums2, 2, 3));

        int[] nums3 = new int[] { 0, 0, 1, 0, 1, 1, 1 };
        System.out.println(maxProfit(nums3, 3, 2));
        System.out.println(maxProfit(nums3, 2, 3));
    }
}
