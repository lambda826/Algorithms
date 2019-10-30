/**
 *  @author Yunxiang He
 *  @date Feb 8, 2018 4:47:36 PM
 */

package algorithms.tree;

import java.util.Comparator;

public class BST2<T> {

    private BinaryNode<T> root;
    private Comparator<? super T> cmp;

    private static class BinaryNode<T> {

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;

        BinaryNode(T theElement) {
            this(theElement, null, null);
        }

        BinaryNode(T theElement, BinaryNode<T> l, BinaryNode<T> r) {
            element = theElement;
            left = l;
            right = r;
        }

    }

    public BST2() {
        root = null;
    }

    public BST2(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }

    private int myCompare(T lhs, T rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T x) {
        return contains(x, root);
    }

    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true; // Match
        }
    }

    public T findMin() {
        if (isEmpty()) {
            //            throw new UnderflowException();
        }
        return findMin(root).element;
    }

    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }

    public T findMax() {
        if (isEmpty()) {
            //            throw new UnderflowException();
        }
        return findMax(root).element;
    }

    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }

    public void insert(T x) {
        root = insert(x, root);
    }

    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        int compareResult = myCompare(x, t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            ; // Duplicate; do nothing
        }
        return t;
    }

    public void remove(T x) {
        root = remove(x, root);
    }

    private BinaryNode<T> remove(T x, BinaryNode<T> t) {

        if (t == null) {
            return t;
        }

        int compareResult = cmp.compare(x, t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return t;

    }

    private void printTree(BinaryNode<T> t) {
        /* Figure 4.56 */
    }

    public void printTree() {
        /* Figure 4.56 */
    }

}