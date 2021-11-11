/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package questions.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*



 */

public class __0017_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        DFS(nums, 0, new ArrayList<Integer>(), results);
        return results;
    }

    // 1. 递归的定义
    // 以 subset 开头的，配上 nums 以 index 开始的数所有组合放到 results 里
    private void DFS(int[] nums, int index, List<Integer> subset, List<List<Integer>> results) {
        // 3. 递归的出口
        if (index == nums.length) {
            results.add(new ArrayList<Integer>(subset));
            return;
        }

        // 2. 递归的拆解
        // (如何进入下一层)

        // 选了 nums[index]
        subset.add(nums[index]);
        DFS(nums, index + 1, subset, results);

        // 不选 nums[index]
        subset.remove(subset.size() - 1);
        DFS(nums, index + 1, subset, results);
    }
}
