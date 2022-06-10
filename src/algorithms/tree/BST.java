/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-15
 */

package algorithms.tree;

import common.TreeNode;

import java.util.Arrays;

public class BST {

    public static void main(String[] args) {
        TreeNode root = TreeNode.array2Tree(new Integer[] { 50, 17, 72, 12, 23, 54, 76, 9, 14, 19, null, null, 67, });
        System.out.println(predecessor_search_from_root(root, 76).val);
        System.out.println(Arrays.toString(TreeNode.tree2Array(root)));

        TreeNode.inorder(root);
        System.out.println();

        TreeNode.preorder(root);
        System.out.println();

        TreeNode.postorder(root);
        System.out.println();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static TreeNode search_Recursion(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        } else if (node.val < val) {
            return search_Recursion(node.left, val);
        } else {
            return search_Recursion(node.right, val);
        }
    }

    public static TreeNode search_iterative(TreeNode node, int val) {
        while (node != null && node.val != val) {
            if (node.val < val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return node;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static TreeNode findMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Insert and update
    public static TreeNode put(TreeNode root, int key) {
        if (root == null) {
            return new TreeNode(key);
        } else if (root.val > key) {
            root.left = put(root.left, key);
        } else if (root.val < key) {
            root.right = put(root.right, key);
        }
        return root;
    }

    public static TreeNode put2(TreeNode root, int key) {
        TreeNode temp = root;
        TreeNode newNode = new TreeNode(key);
        while (root != null) {
            if (key < root.val) {
                if (root.left == null) {
                    root.left = newNode;
                    break;
                }
                root = root.left;
            } else if (key > root.val) {
                if (root.right == null) {
                    root.right = newNode;
                    break;
                }
                root = root.right;
            }
        }
        return temp;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root != null) {
            if (root.val > key) {
                root.left = deleteNode(root.left, key);
            } else if (root.val < key) {
                root.right = deleteNode(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    TreeNode rightMin = findMin(root.right);
                    rightMin.left = root.left;
                    return root.right;
                }
            }
        }
        return root;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find successor
    private TreeNode pre;
    private TreeNode successor;

    public TreeNode successor_inorder(TreeNode root, TreeNode p) {
        inorder(root, p);
        return successor;
    }

    private void inorder(TreeNode curr, TreeNode target) {
        if (curr != null) {
            inorder(curr.left, target);
            if (pre == target) {
                successor = curr;
            }
            pre = curr;
            inorder(curr.right, target);
        }
    }

    // Search from root, find the small val greater than node.val
    public static TreeNode successor_search_from_root(TreeNode root, TreeNode target) {
        TreeNode curr = null;
        while (root != null) {
            if (root.val > target.val) {
                curr = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return curr;
    }

    /**
     * Smallest node which is greater node
     */
    public static TreeNode successor(TreeNode current) {
        // If the right child of node node is nonempty, then the successor of node is the leftmost node of node’s right child
        if (current.right != null) {
            return findMin(current.right);
        }
        // If the right child of node node is empty, then the successor of node is the lowest ancestor of node, whose left child is also an ancestor of node
        TreeNode next = current.parent;
        // Find the parent whose left child is the ancestor of the node
        // If the node is the right child is, then keep searching upward, because right child is greater than parent
        while (next != null && current == next.right) {
            current = next;
            next = next.parent;
        }
        return next;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Greatest < node
    public static TreeNode predecessor(TreeNode node) {
        // If the left child of node node is nonempty, then the predecessor of node is the rightmost node of node's left child
        if (node.left != null) {
            return findMax(node.left);
        }
        // If the left child of node node is empty, then the predecessor of node is the lowest ancestor of node, whose right child is also an ancestor of node
        TreeNode parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    // 从根出发,找到比node.val小的最大的一个node.val
    public static TreeNode predecessor_search_from_root(TreeNode root, TreeNode node) {
        return predecessor_search_from_root(root, node.val);
    }

    public static TreeNode predecessor_search_from_root(TreeNode root, int val) {
        TreeNode curr = null;
        TreeNode next = root;
        while (next != null) {
            if (next.val < val) {
                curr = next;
                next = next.right;
            } else {
                next = next.left;
            }
        }
        return curr;
    }

}
