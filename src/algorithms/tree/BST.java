/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-15
 */

package algorithms.tree;

import common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + ", ");
            inorder(root.right);
        }
    }

    // Visit after all left children
    public static List<Integer> inorder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast();
                result.add(curr.val);
                curr = curr.right;
            }
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void preorder(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + ", ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // Visit before going to children
    public static List<Integer> preorder2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                result.add(curr.val);
                stack.offerLast(curr);
                curr = curr.left;
            } else {
                curr = stack.pollLast().right;
            }
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void postorder(TreeNode root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.val + ", ");
        }
    }

    public static List<Integer> postorder2(TreeNode root) {
        LinkedList<Integer> result = new LinkedList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                result.addFirst(curr.val); // Reverse the process of preorder
                stack.offerLast(curr);
                curr = curr.right; // Reverse the process of preorder
            } else {
                curr = stack.pollLast().left; // Reverse the process of preorder
            }
        }
        return result;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static TreeNode search_recursive(TreeNode node, int val) {
        if (node == null || node.val == val) {
            return node;
        } else if (node.val < val) {
            return search_recursive(node.left, val);
        } else {
            return search_recursive(node.right, val);
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    // Smallest > node
    public static TreeNode successor(TreeNode node) {
        // If the right child of node node is nonempty, then the successor of node is the leftmost node of node’s right child
        if (node.right != null) {
            return findMin(node.right);
        }
        // If the right child of node node is empty, then the successor of node is the lowest ancestor of node, whose left child is also an ancestor of node
        TreeNode parent = node.parent;
        // Find the parent whose left child is the ancesstor of the node
        // If the right child is the node, then keep searching upward, because right child is greater than parent
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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
