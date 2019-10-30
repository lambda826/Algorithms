/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*


Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.


Example 1:
    Input: k = 3, n = 7
    Output: [[1,2,4]]

Example 2:
    Input: k = 3, n = 9
    Output: [[1,2,6], [1,3,5], [2,3,4]]


Note:
    All numbers will be positive integers.
    The solution set must not contain duplicate combinations.
    
*/

public class _0216_Combination_Sum_III {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> list = new ArrayList<>();
        DFS(1, k, new ArrayList<>(), n, list);
        return list;
    }

    private void DFS(int i, int k, List<Integer> temp, int n, List<List<Integer>> list) {
        if (n == 0 && k == 0) {
            list.add(new ArrayList<>(temp));
        } else if (n > 0 && k > 0 && i <= 9) {
            // Add
            temp.add(i);
            DFS(i + 1, k - 1, temp, n - i, list);
            temp.remove(temp.size() - 1);

            // Not add
            DFS(i + 1, k, temp, n, list);
        }
    }
}
