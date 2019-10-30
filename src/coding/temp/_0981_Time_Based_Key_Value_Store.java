/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*

Create a timebased key-value store class TimeMap, that supports two operations.
    1. set(string key, string value, int timestamp)
        Stores the key and value, along with the given timestamp.
    2. get(string key, int timestamp)
        Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
        If there are multiple such values, it returns the one with the largest timestamp_prev.
        If there are no values, it returns the empty string ("").
 

Example 1:
    Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
    Output: [null,null,"bar","bar",null,"bar2","bar2"]
    Explanation:   
    TimeMap kv;   
    kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
    kv.get("foo", 1);  // output "bar"   
    kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"   
    kv.set("foo", "bar2", 4);   
    kv.get("foo", 4); // output "bar2"   
    kv.get("foo", 5); //output "bar2"   

Example 2:
    Input: inputs = ["TimeMap","set","set","get","get","get","get","get"], inputs = [[],["love","high",10],["love","low",20],["love",5],["love",10],["love",15],["love",20],["love",25]]
    Output: [null,null,null,"","high","high","low","low"]


Note:
    All key/value strings are lowercase.
    All key/value strings have length in the range [1, 100]
    The timestamps for all TimeMap.set operations are strictly increasing.
    1 <= timestamp <= 10^7
    TimeMap.set and TimeMap.get functions will be called a total of 120000 times (combined) per test case.

*/

public class _0981_Time_Based_Key_Value_Store {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class TreeMapSolution {

        private Map<String, TreeMap<Integer, String>> key_time_value;

        public TreeMapSolution() {
            key_time_value = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> time_value = key_time_value.getOrDefault(key, new TreeMap<>());
            time_value.put(timestamp, value);
            key_time_value.put(key, time_value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = key_time_value.get(key);
            Integer k = treeMap.floorKey(timestamp);
            if (treeMap == null || k == null) {
                return "";
            }
            return treeMap.get(k);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class BinarySearchSolution {

        private Map<String, Map<Integer, String>> key_time_value;

        public BinarySearchSolution() {
            key_time_value = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            Map<Integer, String> time_value = key_time_value.getOrDefault(key, new LinkedHashMap<>());
            time_value.put(timestamp, value);
            key_time_value.put(key, time_value);
        }

        public String get(String key, int timestamp) {
            Map<Integer, String> time_value = key_time_value.get(key);
            if (time_value == null) {
                return "";
            }
            List<Integer> list = new ArrayList<>(time_value.keySet());
            int insertionPoint = Collections.binarySearch(list, timestamp);
            if (insertionPoint >= 0) {
                return time_value.get(list.get(insertionPoint));
            } else if (insertionPoint == -1) {
                return "";
            }
            return time_value.get(list.get(-insertionPoint - 2));
        }
    }
}
