package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.


Example 1:
    Input:
        nums = [1,2,3]
    Output:
        [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
    Input:
        nums = [0,1]
    Output:
        [[0,1],[1,0]]
Example 3:
    Input:
        nums = [1]
    Output:
        [[1]]


Constraints:
    1 <= nums.length <= 6
    -10 <= nums[i] <= 10
    All the integers of nums are unique.

*/

public class _0046_Permutations {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        helper(0, numList, res);
        return res;
    }

    private void helper(int start, ArrayList<Integer> numList, List<List<Integer>> res) {
        if (start == numList.size()) {
            res.add(new ArrayList<>(numList));
        } else {
            for (int i = start; i < numList.size(); ++i) {
                // We should increase start index by start + 1 instead of i + 1;
                // Because the start index in each level of the recursion tree is one greater than previous level;
                Collections.swap(numList, start, i);
                helper(start + 1, numList, res);
                Collections.swap(numList, start, i);

            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        ArrayList<Integer> numList = new ArrayList<>();
        for (int num : nums) {
            numList.add(num);
        }
        DFS(0, numList, res);
        return res;
    }

    // Top down
    private void DFS(int start, ArrayList<Integer> numList, List<List<Integer>> res) {
        if (start == numList.size()) {
            res.add(new ArrayList<>(numList));
        } else {
            for (int i = start; i < numList.size(); ++i) {
                Collections.swap(numList, start, i);
                DFS(start + 1, new ArrayList<>(numList), res);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> permute3(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(nums, new boolean[nums.length], new LinkedList<>(), res);
        return res;
    }

    private void helper(int[] nums, boolean[] visited, LinkedList<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (!visited[i]) {
                    visited[i] = true;
                    curr.addLast(nums[i]);
                    helper(nums, visited, curr, res);
                    curr.removeLast();
                    visited[i] = false;
                }
            }
        }
    }
}
