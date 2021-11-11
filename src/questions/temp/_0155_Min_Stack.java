
package questions.temp;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.


Example:
    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> Returns -3.
    minStack.pop();
    minStack.top();      --> Returns 0.
    minStack.getMin();   --> Returns -2.

*/


public class _0155_Min_Stack {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static class Solution1 {
        private Deque<Integer> data;
        private Deque<Integer> min;

        public Solution1() {
            data = new ArrayDeque<>();
            min = new ArrayDeque<>();
            min.offerFirst(Integer.MAX_VALUE);
        }

        public void push(int x) {
            data.offerFirst(x);
            min.offerFirst(Math.min(x, min.peekFirst()));
        }

        public void pop() {
            data.pollFirst();
            min.pollFirst();
        }

        public int top() {
            return data.peekFirst();
        }

        public int getMin() {
            return min.peekFirst();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Push a new number onto the min-tracker Stack if, and only if, it was less than or equal to the current minimum.
    private static class SolutionOptimized {
        private Deque<Integer> data;
        private Deque<Integer> min;

        /** initialize your data structure here. */
        public SolutionOptimized() {
            data = new ArrayDeque<>();
            min = new ArrayDeque<>();
            min.offerFirst(Integer.MAX_VALUE);
        }

        public void push(int val) {
            data.offerFirst(val);
            if (val <= min.peekFirst()) {
                min.offerFirst(val);
            }
        }

        public void pop() {
            int val = data.pollFirst();
            if (val == min.peekFirst()) {
                min.pollFirst();
            }

        }

        public int top() {
            return data.peekFirst();
        }

        public int getMin() {
            return min.peekFirst();
        }
    }

}
