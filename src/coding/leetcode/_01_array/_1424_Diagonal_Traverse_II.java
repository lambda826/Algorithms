package coding.leetcode._01_array;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class _1424_Diagonal_Traverse_II {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        List<ArrayDeque<Integer>> temp = new ArrayList<>();
        int size = 0;
        for (int i = 0; i < nums.size(); ++i) {
            size += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); ++j) {
                if (i + j == temp.size()) {
                    temp.add(new ArrayDeque<>());
                }
                temp.get(i + j).offerFirst(nums.get(i).get(j));
            }
        }

        int[] res = new int[size];
        int i = 0;
        for (ArrayDeque<Integer> deque : temp) {
            for (int num : deque) {
                res[i++] = num;
            }
        }
        return res;
    }
}
