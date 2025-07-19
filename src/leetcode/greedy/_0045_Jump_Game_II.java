package leetcode.greedy;

public class _0045_Jump_Game_II {

    class Solution {
        public int jump(int[] nums) {
            int lastIdx = nums.length - 1;
            int step = 0;
            while (lastIdx != 0) {
                for (int i = 0; i < lastIdx; ++i) {
                    if (nums[i] + i >= lastIdx) {
                        lastIdx = i;
                        break;
                    }
                }
                step++;
            }
            return step;
        }
    }
}
