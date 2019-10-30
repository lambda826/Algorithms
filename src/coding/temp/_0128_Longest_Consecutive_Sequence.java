/**
 *  @author Yunxiang He
 *  @date 08/06/2018
 */

package coding.temp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.


Example:
	Input: [100, 4, 200, 1, 3, 2]
	Output: 4
	Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

*/

public class _0128_Longest_Consecutive_Sequence {

    public static void main(String[] args) {
        _0128_Longest_Consecutive_Sequence _0128_Longest_Consecutive_Sequence = new _0128_Longest_Consecutive_Sequence();
        _0128_Longest_Consecutive_Sequence.longestConsecutive_UF(new int[] { 1, 2, 3, -1, -2, -3, -4 });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> size = new HashMap<>();

    public int longestConsecutive_UF(int[] nums) {
        for (int num : nums) {
            map.put(num, num);
            size.put(num, 1);
        }

        // union the num with previous num if contained
        for (int num : nums) {
            if (map.containsKey(num - 1)) {
                union(num, num - 1);
            }
        }
        int max = 0;
        for (int key : size.keySet()) {
            max = Math.max(max, size.get(key));
        }
        return max;
    }

    private int find(int index) {
        if (index == map.get(index)) {
            return index;
        }
        map.put(index, find(map.get(index)));
        return map.get(index);
    }

    private void union(int index1, int index2) {
        int root1 = find(index1);
        int root2 = find(index2);
        if (root1 != root2) {
            if (size.get(root1) < size.get(root2)) {
                size.put(root2, size.get(root1) + size.get(root2));
                map.put(root1, root2);
            } else {
                size.put(root1, size.get(root1) + size.get(root2));
                map.put(root2, root1);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestConsecutive_Hashing(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : set) {
            // Trick
            // Calculate count if num is the leftmost number
            if (!set.contains(num - 1)) {
                int count = 1;
                while (set.contains(num + 1)) {
                    count++;
                    num++;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

}
