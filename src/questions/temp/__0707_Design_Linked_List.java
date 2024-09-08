/**
 * @author: Yunxiang He
 * @date : 2018-07-30 23:51
 */

package questions.temp;

/*

Design your implementation of the linked list. 
You can choose to use the singly linked list or the doubly linked list. 
A node in a singly linked list should have two attributes: val and next. 
val is the value of the current node, and next is a pointer/reference to the next node. 
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. 
Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : 
Get the value of the index-th node in the linked list. 
If the index is invalid, return -1.

addAtHead(val) : 
Add a node of value val before the first element of the linked list. 
After the insertion, the new node will be the first node of the linked list.

addAtTail(val) : Append a node of value val to the last element of the linked list.

addAtIndex(index, val) : 
Add a node of value val before the index-th node in the linked list. 
If index equals to the length of linked list, the node will be appended to the end of linked list. 
If index is greater than the length, the node will not be inserted.

deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.

Example:
MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3

Note:
All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.

*/

public class __0707_Design_Linked_List {

    private final Node head;
    private Node tail;

    private int size;

    class Node {
        Node next;
        int val;

        public Node() {
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public __0707_Design_Linked_List() {
        this.head = new Node();
        this.size = -1;
    }

    public int get(int index) {
        Node node = getPreNodeOfIndex(index);
        if (node == null || node.next == null) {
            return -1;
        }
        return node.next.val;
    }

    public void addAtHead(int val) {
        head.next = new Node(val, head.next);
        size++;
        if (size == 0) {
            tail = head.next;
        }
    }

    public void addAtTail(int val) {
        if (size == -1) {
            addAtHead(val);
        } else {
            tail.next = new Node(val, null);
            tail = tail.next;
            size++;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
        } else if (index == size + 1) {
            addAtTail(val);
        } else {
            Node node = getPreNodeOfIndex(index);
            if (node != null) {
                node.next = new Node(val, node.next);
                size++;
            }
        }
    }

    public void deleteAtIndex(int index) {
        Node node = getPreNodeOfIndex(index);
        if (node != null && node.next != null) {
            if (index == size) {
                tail = node;
            }
            Node temp = node.next;
            node.next = temp.next;
            size--;
        }
    }

    private Node getPreNodeOfIndex(int index) {
        if (index > size + 1) {
            return null;
        }
        Node node = head;
        int i = 0;
        while (i != index) {
            i++;
            node = node.next;
        }
        return node;
    }

    public static void main(String[] args) {
        __0707_Design_Linked_List test = new __0707_Design_Linked_List();
        test.addAtHead(0);
        test.addAtIndex(1, 9);
        test.addAtIndex(1, 5);
        test.addAtTail(7);
        test.addAtHead(1);
        test.addAtIndex(5, 8);
        test.addAtIndex(5, 2);
        test.addAtIndex(3, 0);
        test.addAtTail(1);
        test.addAtTail(0);
        test.deleteAtIndex(6);

    }
}
