package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:
    put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
    get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
    remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.


Example:
    MyHashMap hashMap = new MyHashMap();
    hashMap.put(1, 1);          
    hashMap.put(2, 2);         
    hashMap.get(1);            // returns 1
    hashMap.get(3);            // returns -1 (not found)
    hashMap.put(2, 1);          // update the existing value
    hashMap.get(2);            // returns 1 
    hashMap.remove(2);          // remove the mapping for 2
    hashMap.get(2);            // returns -1 (not found) 


Note:
    All keys and values will be in the range of [0, 10^400].
    The number of operations will be in the range of [1, 10^4].
    Please do not use the built-in HashMap library.

*/

public class _0706_Design_HashMap {

    public static void main(String[] args) {
        _0706_Design_HashMap map = new _0706_Design_HashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        System.out.println(map.get(3));
        map.put(2, 1);
        map.remove(2);
        System.out.println(map.get(2));
        System.out.println(Arrays.toString(map.keys));
        System.out.println(Arrays.toString(map.values));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final int SIZE = 997;
    public List<Integer>[] keys;
    public List<Integer>[] values;

    public _0706_Design_HashMap() {
        keys = new ArrayList[SIZE];
        values = new ArrayList[SIZE];
    }

    public int get(int key) {
        int bucket = key % SIZE;
        if (keys[bucket] == null) {
            return -1;
        }
        for (int idx = 0; idx < keys[bucket].size(); idx++) {
            if (key == keys[bucket].get(idx)) {
                return values[bucket].get(idx);
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        int bucket = key % SIZE;
        if (keys[bucket] == null) {
            keys[bucket] = new ArrayList<Integer>();
            values[bucket] = new ArrayList<Integer>();
        }
        for (int idx = 0; idx < keys[bucket].size(); idx++) {
            if (key == keys[bucket].get(idx)) {
                values[bucket].set(idx, value);
                return;
            }
        }
        keys[bucket].add(key);
        values[bucket].add(value);
    }

    public void remove(int key) {
        int bucket = key % SIZE;
        if (keys[bucket] != null) {
            for (int idx = 0; idx < keys[bucket].size(); idx++) {
                if (key == keys[bucket].get(idx)) {
                    keys[bucket].remove(idx);
                    values[bucket].remove(idx);
                }
            }
        }
    }

}
