/**
 *  @author Yunxiang He
 *  @date 05/23/2018
 */

package questions.temp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*

Design a data structure that supports all following operations in average O(1) time.
    insert(val): Inserts an item val to the set if not already present.
    remove(val): Removes an item val from the set if present.
    getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.


Example:
    // Init an empty set.
    RandomizedSet randomSet = new RandomizedSet();
    
    // Inserts 1 to the set. Returns true as 1 was inserted successfully.
    randomSet.insert(1);
    
    // Returns false as 2 does not exist in the set.
    randomSet.remove(2);
    
    // Inserts 2 to the set, returns true. Set now contains [1,2].
    randomSet.insert(2);
    
    // getRandom should return either 1 or 2 randomly.
    randomSet.getRandom();
    
    // Removes 1 from the set, returns true. Set now contains [2].
    randomSet.remove(1);
    
    // 2 was already in the set, so return false.
    randomSet.insert(2);
    
    // Since 2 is the only number in the set, getRandom always return 2.
    randomSet.getRandom();

*/

public class _0380_Insert_Delete_GetRandom_O1 {

    public static void main(String[] args) {
        _0380_Insert_Delete_GetRandom_O1 test = new _0380_Insert_Delete_GetRandom_O1();
        test.insert(0);
        test.remove(0);
        test.insert(0);
        System.out.println(test.getRandom());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private List<Integer> data;
    private Map<Integer, Integer> map;
    private Random rand;

    public _0380_Insert_Delete_GetRandom_O1() {
        data = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        } else {
            map.put(val, data.size());
            data.add(val);
            return true;
        }
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        } else {
            int idx = map.get(val);
            map.put(data.get(data.size() - 1), idx);
            data.set(idx, data.get(data.size() - 1));
            map.remove(val);
            data.remove(data.size() - 1);
            return true;
        }
    }

    public int getRandom() {
        return data.get(rand.nextInt(data.size()));
    }

}
