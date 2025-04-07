package questions.leetcode.greedy;

public class _0055_Jump_Game {

    class Solution {
        public boolean canJump(int[] nums) {
            int step = 1;
            boolean canJump = true;
            for (int i = nums.length - 2; i >= 0; --i) {
                if (nums[i] == 0 || nums[i] < step) {
                    canJump = false;
                    step++;
                } else {
                    canJump = true;
                    step = 1;
                }
            }
            return canJump;
        }
    }
}
