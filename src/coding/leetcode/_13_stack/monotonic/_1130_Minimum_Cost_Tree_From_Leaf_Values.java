package coding.leetcode._13_stack.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.



Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4


Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

 */
public class _1130_Minimum_Cost_Tree_From_Leaf_Values {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Given { a, b, c }
    // If a < b, b < c, then sum = ab + bc;
    // If a > b, b > c, then sum = ab + bc;
    //
    // If a < b, b > c, then sum = ab + bc regardless of a > c or a < c;
    // If a > b, b < c, a < c, then sum = ab + ac;
    // If a > b, b < c, a > c, then sum = bc + ac;
    //
    // Observe from above, we always want to multiply the smallest number with the smaller number beside the smallest number.
    // In order to achieve that, we use a monotonic stack to store a decreasing array;
    // Whenever there is a valley point, we pop the number and multiply the smaller number beside it.
    public int mctFromLeafValues(int[] arr) {
        int sum = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(Integer.MAX_VALUE);
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] < queue.peekLast()) {
                queue.offerLast(arr[i]);
            } else {
                while (arr[i] > queue.peekLast()) {
                    int peek = queue.pollLast();
                    sum += peek * Math.min(queue.peekLast(), arr[i]);
                }
                queue.offerLast(arr[i]);
            }
        }
        while (queue.size() > 2) {
            sum += queue.pollLast() * queue.peekLast();
        }
        return sum;
    }
}