package coding.leetcode._07_dfs_backTracking.combination._2d;

import java.util.ArrayList;
import java.util.List;

public class _1239_Maximum_Length_of_a_Concatenated_String_with_Unique_Characters {


    public int maxLength(List<String> arr) {
        // { 0: converted string, 1: length of the string }
        List<int[]> converted = new ArrayList<>();
        for (String str : arr) {
            // Convert the string into digit representation
            int res = convert(str);
            if (res != -1) {
                converted.add(new int[] { res, str.length() });
            }
        }
        return dfs(converted, 0, 0, 0);
    }

    private int dfs(List<int[]> converted, int index, int currentCombination, int count) {
        int temp = count;
        if (index < converted.size()) {
            // index: forward search only for combinations
            for (int i = index; i < converted.size(); ++i) {
                if ((converted.get(i)[0] & currentCombination) == 0) {
                    temp = Math.max(temp, dfs(converted, i + 1, currentCombination | converted.get(i)[0], count + converted.get(i)[1]));
                }
            }
        }
        return temp;
    }

    private int convert(String str) {
        int converted = 0;
        for (char ch : str.toCharArray()) {
            int temp = 1 << (ch - 'a');
            if ((temp & converted) == 0) { // Check whether there exists duplicates
                converted |= temp;
            } else {
                return -1;
            }
        }
        return converted;
    }

}
