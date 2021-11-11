package questions.lintcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*

There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, find the average of 5 highest scores for each person.


Example
Example 1:
    Input: 
    [[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
    Output:
    1: 72.40
    2: 97.40

Example 2:
    Input:
    [[1,90],[1,90],[1,90],[1,90],[1,90],[1,90]]
    Output: 
    1: 90.00

*/

public class __0613_High_Five {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (Record record : results) {
            map.putIfAbsent(record.id, new PriorityQueue<>());
            map.get(record.id).offer(record.score);
            if (map.get(record.id).size() == 6) {
                map.get(record.id).poll();
            }
        }
        Map<Integer, Double> res = new HashMap<>();
        for (int key : map.keySet()) {
            double avg = 0;
            for (int i = 0; i < 5; ++i) {
                avg += ((double) map.get(key).poll() / 5);
            }
            res.put(key, avg);
        }
        return res;
    }

    class Record {
        public int id;
        public int score;

        public Record(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }
}
