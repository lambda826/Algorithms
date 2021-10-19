/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package algorithms.graph;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String args[]) throws Exception {
        System.out.print(combinationK(new int[] { 1, 8, 5, 3, 2 }, 2));
    }

    public static List<List<Integer>> combinationK(int[] arr, int k) {
        List<List<Integer>> list = new ArrayList<>();
        if (arr.length < k) {
            return list;
        }
        DFS(arr, k, 0, new ArrayList<>(), list);
        return list;
    }

    private static void DFS(int[] arr, int k, int start, List<Integer> temp, List<List<Integer>> list) {
        if (temp.size() == k) {
            list.add(new ArrayList<>(temp));
        } else if (start == arr.length) {
            return;
        } else if (temp.size() < k) {
            // We choose the element
            temp.add(arr[start]);
            DFS(arr, k, start + 1, temp, list);
            temp.remove(temp.size() - 1);

            // We skip the elemnt
            DFS(arr, k, start + 1, temp, list);
        }

    }
}
