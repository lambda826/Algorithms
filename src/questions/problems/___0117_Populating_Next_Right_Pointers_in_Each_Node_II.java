/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package questions.problems;

import common.TreeLinkNode;

/*

Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }

Populate each next pointer to point to its next right node. 
If there is no next right node, the next pointer should be set to NULL.
Initially, all next pointers are set to NULL.


Example:
    Given the following binary tree,
         1
       /  \
      2    3
     / \    \
    4   5    7
    
    After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL


Note:
You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.

*/

public class ___0117_Populating_Next_Right_Pointers_in_Each_Node_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void connect(TreeLinkNode root) {
        TreeLinkNode pre;
        TreeLinkNode curr = root;
        TreeLinkNode nextRoot;
        while (root != null) {
            pre = null;
            curr = root;
            nextRoot = null;
            while (curr != null) {
                if (curr.left != null) {
                    if (pre != null) {
                        pre.next = curr.left;
                    }
                    pre = curr.left;
                }
                if (nextRoot == null) {
                    nextRoot = curr.left;
                }
                if (curr.right != null) {
                    if (pre != null) {
                        pre.next = curr.right;
                    }
                    pre = curr.right;
                }
                if (nextRoot == null) {
                    nextRoot = curr.right;
                }
                curr = curr.next;
            }
            root = nextRoot;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void connect2(TreeLinkNode root) {
        TreeLinkNode first;
        TreeLinkNode second;
        TreeLinkNode nextRoot;
        while (root != null) {
            nextRoot = null;
            first = null;
            second = null;
            do {
                if (root.left != null) {
                    if (first == null) {
                        first = root.left;
                        if (nextRoot == null) {
                            nextRoot = first;
                        }
                    } else if (second == null) {
                        second = root.left;
                    }
                }
                if (root.right != null) {
                    if (first == null) {
                        first = root.right;
                        if (nextRoot == null) {
                            nextRoot = first;
                        }
                    } else if (second == null) {
                        second = root.right;
                    }
                }
                if (first != null && second != null) {
                    first.next = second;
                    first = second;
                    second = null;
                }
                root = root.next;
            } while (first != null && second != null && root != null);
            root = nextRoot;
        }
    }

    public static void main(String[] args) {
        TreeLinkNode t1 = new TreeLinkNode(1);
        TreeLinkNode t2 = new TreeLinkNode(2);
        TreeLinkNode t3 = new TreeLinkNode(3);
        TreeLinkNode t4 = new TreeLinkNode(4);
        TreeLinkNode t5 = new TreeLinkNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        new ___0117_Populating_Next_Right_Pointers_in_Each_Node_II().connect(t1);
    }
}
