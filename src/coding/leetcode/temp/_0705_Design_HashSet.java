/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-30 00:51
 */

package coding.leetcode.temp;

import java.util.LinkedList;

/*

Design a HashSet without using any built-in hash table libraries.

To be specific, your design should include these functions:

add(value): Insert a value into the HashSet. 
contains(value) : Return whether the value exists in the HashSet or not.
remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

Example:
MyHashSet hashSet = new MyHashSet();
hashSet.add(1);         
hashSet.add(2);         
hashSet.contains(1);    // returns true
hashSet.contains(3);    // returns false (not found)
hashSet.add(2);          
hashSet.contains(2);    // returns true
hashSet.remove(2);          
hashSet.contains(2);    // returns false (already removed)

Note:
All values will be in the range of [0, 10^400].
The number of operations will be in the range of [1, 10^4].
Please do not use the built-in HashSet library.

*/

public class _0705_Design_HashSet {

    private static final int SIZE = 997;
    private LinkedList<Integer>[] buckets;

    public _0705_Design_HashSet() {
        buckets = new LinkedList[SIZE];
    }

    public void add(int key) {
        int index = key % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }
        boolean found = false;
        for (int i : buckets[index]) {
            if (i == key) {
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(key);
        }
    }

    public void remove(int key) {
        int index = key % SIZE;
        if (buckets[index] == null) {
            return;
        }
        for (int i : buckets[index]) {
            if (i == key) {
                buckets[index].remove((Object) (key));
                break;
            }
        }
    }

    public boolean contains(int key) {
        int index = key % SIZE;
        boolean found = false;
        if (buckets[index] == null) {
            return found;
        }
        for (int i : buckets[index]) {
            if (i == key) {
                found = true;
                break;
            }
        }
        return found;
    }
}
