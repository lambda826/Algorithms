/**
 *  @author: Yunxiang He
 *  @date  : 2018-09-23
 */

package questions.problems;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cutting_Metal_Surplus {

    public static int maxProfit(int costPercut, int salePrice, List<Integer> lengths) {
        int maxProfit = 0;
        Collections.sort(lengths);
        int maxLength = lengths.get(lengths.size() - 1);
        int[] prices = new int[maxLength + 1];
        for (int len = 1; len <= maxLength; len++) {
            for (int rodLength : lengths) {
                prices[len] += (rodLength - (rodLength % len)) * salePrice - costPercut * ((rodLength - 1) / len);
            }
            maxProfit = Math.max(maxProfit, prices[len]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(1, 10, Arrays.asList(new Integer[] { 30, 59, 110 })));
    }
}
