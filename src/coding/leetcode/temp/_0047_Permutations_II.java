package coding.leetcode.temp;

/*

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.


Example 1:
    Input:
        nums = [1,1,2]
    Output:
        [[1,1,2],[1,2,1],[2,1,1]]

Example 2:
    Input:
        nums = [1,2,3]
    Output:
        [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]


Constraints:
    1 <= nums.length <= 8
    -10 <= nums[i] <= 10

*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class _0047_Permutations_II {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // The key point is how we deduplicate (swap approach):
    //      1. We need to maintain same elements adjacent before and after swap, in other words, we need to maintain the array in sorted order;
    //      2. When we do BackTracking, we view the array as two parts:
    //          2.1 first part is the visited elements which is permuted;
    //          2.2 second part is the elements which are to be permuted in the next recursion which should be in sorted order for deduplication;
    //          2.3 when iterate on the elements, we need to keep previous status of the array in order to keep the second part in sorted order for deduplication;
    //
    // For an example, start index = 0, init, the array is   { 0, 0, 1, 1, 2, 3 };
    //                      when i = 0, swap, the array is   { 0, 0, 1, 1, 2, 3 }, do backtrack for { 0 } + { 0, 1, 1, 2, 3 }
    //                      when i = 1, skip, the array is   { 0, 0, 1, 1, 2, 3 };
    //                      when i = 2, swap, the array is   { 1, 0, 0, 1, 2, 3 }, do backtrack for { 1 } + { 0, 0, 1, 2, 3 }, we can see after swapping 0 with 1, the two 0s are still adjacent;
    //                      when i = 3, skip, the array is   { 1, 0, 0, 1, 2, 3 };
    //                      when i = 4, swap, the array is   { 2, 0, 0, 1, 1, 3 }, do backtrack for { 2 } + { 0, 0, 1, 1, 3 }, we can see after swapping 1 with 2, the two 1s are still adjacent;
    //                      when i = 5, skip, the array is   { 3, 0, 0, 1, 1, 2 }, do backtrack for { 3 } + { 0, 0, 1, 1, 2 };
    // As a result, we can see we listed all possible elements for the first part, and then second part is still in sorted order, so we can do a recursion for the second part.
    //
    // We can also maintain a hashset during earch interation for deduplication, in this case we don't even need to maintain the order of the array.
    //      The tradeoff is we need to initialize a hashset for each recursion.
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        ArrayList<Integer> numList = new ArrayList<>();
        Arrays.sort(nums); // Sort the array to keep same elements adjacent;
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
                if (i == start || !numList.get(start).equals(numList.get(i))) { // Deduplicate: skip swapping the same element to the start index;
                    Collections.swap(numList, start, i);  // Swap the element with the start index element, this will maintain the rest elements in an ascending order;
                    helper(start + 1, new ArrayList<>(numList), res); // We need to keep separate references for the following recursion;
                    // Collections.swap(numList, start, i);
                    // Note: we cannot revert it back to the original status otherwise there will be duplicates:
                    //      In this case, same elements will swap with the start index element, which results in duplicated brunches;
                    //      If we check (!numList.get(i - 1).equals(numList.get(i))) instead of (!numList.get(start).equals(numList.get(i))),
                    //          it will cause another issue which is after some BackTracking, the second part of the array might not be in sorted order;
                    //          this will introduce duplicate brunches.
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        helper(nums, new boolean[nums.length], new LinkedList<>(), res);
        return res;
    }

    private void helper(int[] nums, boolean[] visited, LinkedList<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == nums.length) {
            res.add(new ArrayList<>(curr));
        } else {
            for (int i = 0; i < nums.length; ++i) {
                if (visited[i] // Skip if current index has been visited,
                    || i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) { // or if current index element equals to previous index element which has been visited;
                    continue;
                }
                curr.addLast(nums[i]);
                visited[i] = true;
                helper(nums, visited, curr, res);
                visited[i] = false;
                curr.removeLast();
            }
        }
    }
}