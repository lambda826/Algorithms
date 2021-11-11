/**
 *  @author: Yunxiang He
 *  @date  : 2018-11-11
 */

package questions.problems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Final_Discounted_Price {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Notice: 
    // the return type is void
    // sum might overflow
    public static void finalPrice(List<Integer> prices) {
        long sum = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        int curr;
        for (int i = 0; i < prices.size(); i++) {
            curr = prices.get(i);
            while (!deque.isEmpty() && prices.get(deque.getFirst()) >= curr) {
                sum += prices.get(deque.removeFirst()) - curr;
            }
            deque.addFirst(i);
        }

        int nextIndex;
        String res = "";
        while (!deque.isEmpty()) {
            nextIndex = deque.remove();
            sum += prices.get(nextIndex);
            res = nextIndex + " " + res;
        }
        System.out.println(sum);
        System.out.println(res);

    }

    public static void main(String[] args) {
        finalPrice(Arrays.asList(new Integer[] { 2, 3, 1, 2, 4, 2, 2_147_483_647 }));
        System.out.println();
        finalPrice(Arrays.asList(new Integer[] { 5, 1, 3, 4, 6, 2, }));
        System.out.println();
        finalPrice(Arrays.asList(new Integer[] { 1, 3, 3, 2, 5, }));
        System.out.println();
        finalPrice(Arrays.asList(new Integer[] { 1 }));

    }
}
