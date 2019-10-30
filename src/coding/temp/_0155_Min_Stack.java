/**
 *  @author Yunxiang He
 *  @date 02/20/2017
 */

package coding.temp;

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

/**
 * The only operation that might change the min value is push and pop
 * The idea is to record the previous min value when update the min value
 * When push an element into the stack, if the element is less than or equal to the min value,
 *     Then update the min value, record the previous min value adjacent to the min vlaue
 * When pop an element from the stack, if the element is the min value
 *     Then pop twice to get the previous min value 
 */
public class _0155_Min_Stack {

    Deque<Integer> data = new ArrayDeque<>();
    Deque<Integer> min = new ArrayDeque<>();

    /** initialize your data structure here. */
    public _0155_Min_Stack() {
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
