/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package questions.temp;

/*



 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _0621_Task_Scheduler {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> count = new HashMap<>();
        Map<Character, Integer> nextPos = new HashMap<>();
        for (char ch : tasks) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
            nextPos.put(ch, 0);
        }
        PriorityQueue<Character> heap = new PriorityQueue((c1, c2) -> {
            if (nextPos.get(c1) == nextPos.get(c2)) {
                return count.get(c2) - count.get(c1);
            } else {
                return nextPos.get(c1) - nextPos.get(c2);
            }
        });
        return 0;
    }
}
