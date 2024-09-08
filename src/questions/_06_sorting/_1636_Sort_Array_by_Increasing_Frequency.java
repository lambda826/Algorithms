package questions._06_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1636_Sort_Array_by_Increasing_Frequency {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Tips:
    // List array cannot be new with generic type:
    // Wrong form:List<Integer>[] temp = new ArrayList<>[201];
    public int[] frequencySort_countSort(int[] nums) {
        int[] counts = new int[201];
        for (int num : nums) {
            counts[num + 100]++;
        }
        List<Integer>[] temp = new ArrayList[201];
        for (int i = 200; i >= 0; --i) {
            if (counts[i] != 0) {
                if (temp[counts[i]] == null) {
                    temp[counts[i]] = new ArrayList<>();
                }
                temp[counts[i]].add(i - 100);
            }
        }
        int k = 0;
        int[] res = new int[nums.length];
        for (int i = 0; i < temp.length; ++i) {
            if (temp[i] != null) {
                for (int num : temp[i]) {
                    int j = i;
                    while (j-- > 0) {
                        res[k++] = num;
                    }
                }
            }
        }
        return res;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Tips:
    // Arrays.sort(array, comparator) cannot be used with primitive array;
    // Correct form: Arrays.sort(T[] a, Comparator<? super T> c)
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return Arrays.stream(nums).boxed().sorted(
                         (a, b) -> map.get(a) - map.get(b) == 0
                                   ? b - a
                                   : (map.get(a) - map.get(b)))
                .mapToInt(Integer::intValue).toArray();

    }
}
