/**
 * @author Yunxiang He
 * @date 12/11/2017
 */

package questions.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Design and implement a TwoSum class. It should support the following operations: add and find.
    add - Add the number to an internal data structure.
    find - Find if there exists any pair of numbers which sum is equal to the value.


Example 1:
    add(1); add(3); add(5);
    find(4) -> true
    find(7) -> false

Example 2:
    add(3); add(1); add(2);
    find(3) -> true
    find(6) -> false

*/

public class _0170_Two_Sum_III_Data_structure_design {

    public static void main(String[] args) {
        _0170_Two_Sum_III_Data_structure_design test = new _0170_Two_Sum_III_Data_structure_design();
        test.add(1);
        test.add(3);
        test.add(5);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    List<Integer> nums;
    Map<Integer, Integer> map;

    public _0170_Two_Sum_III_Data_structure_design() {
        this.nums = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public void add(int number) {
        nums.add(number);
        map.put(number, nums.size() - 1);
    }

    public boolean find(int value) {
        for (int i = 0; i < nums.size() - 1; i++) {
            if (map.containsKey(value - nums.get(i)) && i != map.get(value - nums.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean find2(int value) {
        Collections.sort(nums);
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int sum = nums.get(left) + nums.get(right);
            if (sum < value) {
                ++left;
            } else if (sum > value) {
                --right;
            } else {
                return true;
            }
        }
        return false;
    }

}
