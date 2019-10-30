/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-10
 */

package coding.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Smart_Sale {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int deleteProducts(int m, int[] ids) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int id : ids) {
            map.put(id, map.getOrDefault(id, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int id : map.keySet()) {
            pq.add(map.get(id));
        }

        int count = pq.size();
        while (!pq.isEmpty()) {
            m -= pq.remove();
            if (m >= 0) {
                count--;
            } else {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new Smart_Sale().deleteProducts(1, new int[] { 1 }));
        System.out.println(new Smart_Sale().deleteProducts(2, new int[] { 1, 1, 1, 2, 2, 3 }));
        System.out.println(new Smart_Sale().deleteProducts(1, new int[] { 1, 1, 1, 4, 4, 4, 2, 2, 3, 5 }));
    }

}
