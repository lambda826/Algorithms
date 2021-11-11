/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions._10_tree.bst;

import java.util.ArrayDeque;
import java.util.Deque;

/*

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree: 
     5
    / \
   2   6
  / \
 1   3
 

Example 1:
    Input: [5,2,6,1,3]
    Output: false

Example 2:
    Input: [5,2,1,3,6]
    Output: true


Follow up:
    Could you do it using only constant space complexity?

*/

public class _0255_Verify_Preorder_Sequence_in_Binary_Search_Tree {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DESC is walking left, ASC is walking right
    // If DESC, push the element into the stack, so the stack is ASC in term of pop sequence
    // If ASC, update the min, which is the element whose next element in the stack is greater than the curr element.
    //    This element is the parent of the current element, and also the low bound of the consequent elements
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) {
                return false;
            }
            // num > stack.peek, indicates walking right, find the last element, which is its root, update it to be min
            while (!stack.isEmpty() && num > stack.peekFirst()) {
                min = stack.pollFirst();
            }
            stack.offerFirst(num);
        }
        return true;
    }

    public static void main(String[] args) {
        new _0255_Verify_Preorder_Sequence_in_Binary_Search_Tree().verifyPreorder2(new int[] { 10, 7, 5, 2, 6, 8, 12 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean verifyPreorder2(int[] preorder) {
        int i = -1;
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) {
                return false;
            }
            while (i > -1 && num > preorder[i]) {
                min = preorder[i--];
            }
            preorder[++i] = num;
        }
        return true;
    }

}
