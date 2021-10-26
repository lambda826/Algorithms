///**
// * @author: Yunxiang He
// * @date : 2018-06-27
// */
//
//package coding.leetcode.temp;
//
///*
//
//Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.
//
//Example 1:
//Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//Output: True
//Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
//Note:
//
//1 <= k <= len(nums) <= 16.
//0 < nums[i] < 10^4.
//
//
//*/
//
//import java.util.Arrays;
//
//public class _0698_Partition_to_K_Equal_Sum_Subsets {
//
//
//    public boolean canPartitionKSubsets(int[] nums, int k) {
//        int sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        if (sum % k != 0) {
//            return false;
//        } else {
//            sum /= k;
//        }
//        int[] p = new int[k];
//        int[][] memo = new int[1 << sum][1 << nums.length];
//        Arrays.sort(nums);
//
//        return DFS(nums, 0, );
//    }
//
//    private boolean DFS(int[] nums, int currentIndex, int visitedNums)
//}
