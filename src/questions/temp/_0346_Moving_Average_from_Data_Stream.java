/**
 *  @author Yunxiang He
 *  @date Jan 12, 2018 9:22:02 PM
 */

package questions.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

 */

public class _0346_Moving_Average_from_Data_Stream {

    private int sum;
    private int size;
    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public _0346_Moving_Average_from_Data_Stream(int size) {
        this.size = size;
        queue = new LinkedList<Integer>();
    }

    public double next(int val) {
        if (queue.size() < size) {
            queue.add(val);
            sum += val;
        } else {
            sum -= queue.remove();
            queue.add(val);
            sum += val;
        }
        return (double) sum / queue.size();
    }

    public static void main(String[] args) {
        _0346_Moving_Average_from_Data_Stream test = new _0346_Moving_Average_from_Data_Stream(3);
        System.out.println(test.next(1));
        System.out.println(test.next(10));
        System.out.println(test.next(3));
        System.out.println(test.next(5));

    }
}
