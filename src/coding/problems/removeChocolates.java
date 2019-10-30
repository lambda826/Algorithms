package coding.problems;

public class removeChocolates {
    public static int numberOfWays(int n) {
        long[] dp = new long[n];
        dp[0] = new Long(1);
        if (n == 1) {
            return (int) dp[0];
        }
        dp[1] = new Long(1);
        if (n == 2) {
            return (int) dp[1];
        }
        dp[2] = new Long(2);
        if (n == 3) {
            return (int) dp[2];
        }
        for (int i = 4; i <= n; i++) {
            dp[i - 1] = dp[i - 2] + dp[i - 4];
        }
        if (dp[n - 1] < 1000000007) {
            return (int) dp[n - 1];
        } else {
            while (dp[n - 1] >= 1000000007) {
                dp[n - 1] -= 1000000007;
            }
            return (int) dp[n - 1];
        }
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println(numberOfWays(7));
        System.out.println(numberOfWays(1));
    }
}
