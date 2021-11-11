/**
 *  @author Yunxiang He
 *  @date 03/03/2019
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*

Given an array of integers with possible duplicates, randomly output the index of a given target number. 
You can assume that the given target number must exist in the array.


Example:
    int[] nums = new int[] {1,2,3,3,3};
    Solution solution = new Solution(nums);
    
    // pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
    solution.pick(3);
    
    // pick(1) should return 0. Since in the array only nums[0] is equal to 1.
    solution.pick(1);


Note:
    The array size can be very large. Solution that uses too much extra space will not pass the judge.

*/

public class _0398_Random_Pick_Index {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 三个func，一个add一个remove和一个randompick，时间都要O(1)，follow up如果有dup怎么办，如何根据time和space做tradeoff
    private Random rand;
    private Map<Integer, List<Integer>> map;

    public _0398_Random_Pick_Index(int[] nums) {
        rand = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        return map.get(target).get(rand.nextInt(map.get(target).size()));
    }

    public void remove(int target) {
        map.remove(target);
    }

    public void add(int target, int index) {
        map.putIfAbsent(target, new ArrayList<>());
        map.get(target).add(index);
    }
}
