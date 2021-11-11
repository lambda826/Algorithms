/**
 *  @author Yunxiang He
 *  @date 01/22/2018
 */

package questions.problems;

/*

Given an array of n positive integers. 
Write a program to find the sum of maximum sum subsequence of the given array such that the integers in the subsequence are sorted in increasing order. 


For example, 
    if input is {1, 101, 2, 3, 100, 4, 5}, then output should be 106 (1 + 2 + 3 + 100), 
    if the input array is {3, 4, 5, 10}, then output should be 22 (3 + 4 + 5 + 10) 
    if the input array is {10, 5, 4, 3}, then output should be 10

*/

public class Maximum_Sum_Increasing_Subsequence {

    public static void main(String[] args) {
        System.out.println(new Maximum_Sum_Increasing_Subsequence().maxSumIS_DP(new int[] { 1, 101, 2, 3, 100, 4, 5 }));
        System.out.println(new Maximum_Sum_Increasing_Subsequence().maxSumIS_DP(new int[] { 3, 4, 5, 10 }));
        System.out.println(new Maximum_Sum_Increasing_Subsequence().maxSumIS_DP(new int[] { 10, 5, 4, 3 }));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxSumIS_DP(int arr[]) {
        int max = 0;
        int n = arr.length;
        if (n > 0) {
            int[] dp = new int[n];
            max = arr[0];
            dp[0] = arr[0];
            for (int i = 1; i < n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (arr[i] > arr[j]) {
                        dp[i] = Math.max(dp[i], arr[i] + dp[j]);
                    }
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

}
