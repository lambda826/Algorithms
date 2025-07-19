package leetcode.hashing;

import java.util.*;

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

    class RandomizedSet {

        private final List<Integer> data;
        private final Map<Integer, Integer> dataToIndex;
        private final Random random;
        private int indexToInsert;

        public RandomizedSet() {
            data = new ArrayList<>();
            dataToIndex = new HashMap<>();
            random = new Random();
            indexToInsert = 0;
        }

        public boolean insert(int val) {
            if (dataToIndex.containsKey(val)) {
                return false;
            } else {
                if (data.size() == indexToInsert) {
                    data.add(val);
                } else {
                    data.set(indexToInsert, val);
                }
                dataToIndex.put(val, indexToInsert);
                indexToInsert++;
                return true;
            }
        }

        public boolean remove(int val) {
            if (!dataToIndex.containsKey(val)) {
                return false;
            }
            indexToInsert--;

            int indexToRemove = dataToIndex.get(val);
            int lastElement = data.get(indexToInsert);
            dataToIndex.remove(val);
            if (indexToRemove != indexToInsert) {
                data.set(indexToRemove, lastElement);
                dataToIndex.put(lastElement, indexToRemove);
            }
            return true;
        }

        public int getRandom() {
            return data.get(random.nextInt(indexToInsert));
        }
    }
}
