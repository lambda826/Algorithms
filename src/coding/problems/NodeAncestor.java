/**
 *  @author Yunxiang He
 *  @date 2018-10-14 14:51
 */

package coding.problems;

import java.util.Arrays;

public class NodeAncestor {

    public int[] getAncestor(Integer[] arr, int d) {
        int[] ans = new int[arr.length];
        outter:
        for (int i = 0; i < arr.length; i++) {
            // ancestor
            int node = arr[i];
            int temp = 1;
            while (temp < d) {
                if (node == -1) {
                    ans[i] = -1;
                    continue outter;
                }
                //                else if (node == 0 && temp == d) {
                //                    ans[i] = 0;
                //                    continue outter;
                //                }
                node = arr[node];
                temp++;
            }
            ans[i] = node;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new NodeAncestor().getAncestor(new Integer[] { -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, }, 2)));
    }
}
