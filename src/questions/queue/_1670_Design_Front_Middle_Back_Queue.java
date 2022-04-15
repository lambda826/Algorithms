package questions.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Design a queue that supports push and pop operations in the front, middle, and back.

Implement the FrontMiddleBack class:
    FrontMiddleBack() Initializes the queue.
    void pushFront(int val) Adds val to the front of the queue.
    void pushMiddle(int val) Adds val to the middle of the queue.
    void pushBack(int val) Adds val to the back of the queue.
    int popFront() Removes the front element of the queue and returns it. If the queue is empty, return -1.
    int popMiddle() Removes the middle element of the queue and returns it. If the queue is empty, return -1.
    int popBack() Removes the back element of the queue and returns it. If the queue is empty, return -1.
Notice that when there are two middle position choices, the operation is performed on the front-most middle position choice. For example:
    Pushing 6 into the middle of [1, 2, 3, 4, 5] results in [1, 2, 6, 3, 4, 5].
    Popping the middle from [1, 2, 3, 4, 5, 6] returns 3 and results in [1, 2, 4, 5, 6].


Example 1:
    Input:
        ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
        [[], [1], [2], [3], [4], [], [], [], [], []]
    Output:
        [null, null, null, null, null, 1, 3, 4, 2, -1]

Explanation:
    FrontMiddleBackQueue q = new FrontMiddleBackQueue();
    q.pushFront(1);   // [1]
    q.pushBack(2);    // [1, 2]
    q.pushMiddle(3);  // [1, 3, 2]
    q.pushMiddle(4);  // [1, 4, 3, 2]
    q.popFront();     // return 1 -> [4, 3, 2]
    q.popMiddle();    // return 3 -> [4, 2]
    q.popMiddle();    // return 4 -> [2]
    q.popBack();      // return 2 -> []
    q.popFront();     // return -1 -> [] (The queue is empty)


Constraints:
    1 <= val <= 10^9
    At most 1000 calls will be made to pushFront, pushMiddle, pushBack, popFront, popMiddle, and popBack.

*/
public class _1670_Design_Front_Middle_Back_Queue {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class FrontMiddleBackQueue {

        private Deque<Integer> deque1;
        private Deque<Integer> deque2;

        public FrontMiddleBackQueue() {
            deque1 = new ArrayDeque<>();
            deque2 = new ArrayDeque<>();
        }

        public void pushFront(int val) {
            deque1.offerFirst(val);
            adjust();
        }

        public void pushMiddle(int val) {
            if (deque1.size() == deque2.size()) {
                deque2.offerFirst(val);
            } else {
                deque1.offerLast(val);
            }
        }

        public void pushBack(int val) {
            deque2.offerLast(val);
            adjust();
        }

        public int popFront() {
            if (deque1.isEmpty() && deque2.isEmpty()) {
                return -1;
            } else if (deque1.isEmpty()) {
                return deque2.poll();
            } else {
                int res = deque1.pollFirst();
                adjust();
                return res;
            }
        }

        public int popMiddle() {
            if (deque1.isEmpty() && deque2.isEmpty()) {
                return -1;
            } else if (deque1.size() == deque2.size()) {
                return deque1.pollLast();
            } else {
                return deque2.pollFirst();
            }
        }

        public int popBack() {
            if (deque2.isEmpty()) {
                return -1;
            } else {
                int res = deque2.pollLast();
                adjust();
                return res;
            }
        }

        private void adjust() {
            if (deque1.size() > deque2.size()) {
                deque2.offerFirst(deque1.pollLast());
            } else if (deque1.size() < deque2.size() - 1) {
                deque1.offerLast(deque2.pollFirst());
            }
        }
    }

}
