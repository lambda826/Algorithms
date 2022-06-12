package questions._18_hash;

import java.util.ArrayList;
import java.util.List;

/*

Design a HashSet without using any built-in hash table libraries.
Implement MyHashSet class:
    void add(key) Inserts the value key into the HashSet.
    bool contains(key) Returns whether the value key exists in the HashSet or not.
    void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.


Example 1:
    Input
        ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
        [[], [1], [2], [1], [3], [2], [2], [2], [2]]
    Output
        [null, null, null, true, false, null, true, null, false]
    Explanation
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);      // set = [1]
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(1); // return True
        myHashSet.contains(3); // return False, (not found)
        myHashSet.add(2);      // set = [1, 2]
        myHashSet.contains(2); // return True
        myHashSet.remove(2);   // set = [1]
        myHashSet.contains(2); // return False, (already removed)


Constraints:
    0 <= key <= 10^6
    At most 10^4 calls will be made to add, remove, and contains.

*/

public class _0705_Design_HashSet {

    class MyHashSet {

        private int mod = 997;
        private List<Integer>[] keys;

        public MyHashSet() {
            keys = new List[mod];
        }

        public void add(int key) {
            int bucket = key % mod;
            if (keys[bucket] == null) {
                keys[bucket] = new ArrayList<>();
            }
            for (int i = 0; i < keys[bucket].size(); ++i) {
                if (keys[bucket].get(i) == key) {
                    return;
                }
            }
            keys[bucket].add(key);
        }

        public void remove(int key) {
            int bucket = key % mod;
            if (keys[bucket] != null) {
                for (int i = 0; i < keys[bucket].size(); ++i) {
                    if (keys[bucket].get(i) == key) {
                        keys[bucket].remove(i);
                        return;
                    }
                }
            }
        }

        public boolean contains(int key) {
            int bucket = key % mod;
            if (keys[bucket] != null) {
                for (int i = 0; i < keys[bucket].size(); ++i) {
                    if (keys[bucket].get(i) == key) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
