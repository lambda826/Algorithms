/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

*/

public class _0046_Permutations {

    List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        DFS(new ArrayList<>(), new boolean[nums.length], nums);
        return list;
    }

    private void DFS(List<Integer> temp, boolean[] visited, int nums[]) {
        if (temp.size() == nums.length) {
            list.add(new ArrayList<>(temp));
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (!visited[i]) {
                    visited[i] = true;
                    temp.add(nums[i]);
                    DFS(temp, visited, nums);
                    temp.remove(temp.size() - 1);
                    visited[i] = false;
                }
            }
        }
    }
}
