/**
 *  @author Yunxiang He
 *  @date 10/09/2018
 */

package coding.leetcode.temp;

/*

Design your implementation of the linked list. 
You can choose to use the singly linked list or the doubly linked list. 
A node in a singly linked list should have two attributes: val and next. 
val is the value of the current node, and next is a pointer/reference to the next node. 
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. 
Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:
    get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
    addAtHead(val) : Add a node of value val before the first element of the linked list. 
        After the insertion, the new node will be the first node of the linked list.
    addAtTail(val) : Append a node of value val to the last element of the linked list.
    addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. 
        If index equals to the length of linked list, the node will be appended to the end of linked list. 
        If index is greater than the length, the node will not be inserted.
    deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.


Example:
    _0707_Design_Linked_List linkedList = new _0707_Design_Linked_List();
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

public class _0707_Design_Linked_List {

    public static void main(String[] args) {
        _0707_Design_Linked_List linkedList = new _0707_Design_Linked_List();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1, 2); // linked list becomes 1->2->3
        linkedList.get(1); // returns 2
        linkedList.deleteAtIndex(1); // now the linked list is 1->3
        linkedList.get(1); // returns 3
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private int size;

    public _0707_Design_Linked_List() {
        head = new Node(-1);
        size = -1;
    }

    public int get(int index) {
        Node temp = getNode(index);
        return temp == null ? -1 : temp.val;
    }

    public Node getNode(int index) {
        if (index > size) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = head.next;
        head.next = node;
        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(val);
        Node temp = head;
        for (int i = 0; i <= size; i++) {
            temp = temp.next;
        }
        temp.next = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index == size + 1) {
            addAtTail(val);
        } else if (index < size + 1) {
            Node temp = getNode(index - 1);
            Node node = new Node(val);
            node.next = temp.next;
            temp.next = node;
            size++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < size) {
            Node temp = getNode(index - 1);
            temp.next = temp.next.next;
            size--;
        } else if (index == size) {
            Node temp = getNode(index - 1);
            temp.next = null;
            size--;
        }
    }

    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        Node temp = head;
        while (temp != null) {
            sBuilder.append(temp.val).append("    ");
            temp = temp.next;
        }
        return sBuilder.toString();
    }

}
