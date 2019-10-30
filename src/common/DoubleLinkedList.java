/**
 *  @author Yunxiang He
 *  @date 11/22/2018
 */

package common;

public class DoubleLinkedList<K, V> {

    public K key;
    public V val;
    public DoubleLinkedList<K, V> pre;
    public DoubleLinkedList<K, V> next;
    public int freq;

    public DoubleLinkedList() {
    }

    public DoubleLinkedList(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public DoubleLinkedList(K key, V val, int freq) {
        this(key, val);
        this.freq = freq;
    }

    public DoubleLinkedList(K key, V val, DoubleLinkedList<K, V> pre, DoubleLinkedList<K, V> next) {
        this(key, val);
        this.pre = pre;
        this.next = next;
    }

    public DoubleLinkedList(K key, V val, int freq, DoubleLinkedList<K, V> pre, DoubleLinkedList<K, V> next) {
        this(key, val, freq);
        this.pre = pre;
        this.next = next;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("(").append(key.toString()).append(",").append(val.toString()).append(",").append(freq).append(") | ").toString();
    }

    public String toStringLink() {
        DoubleLinkedList<K, V> temp = next;
        StringBuilder sb = new StringBuilder(toString());
        while (temp != this && temp != null) {
            sb.append(temp.toString());
            temp = temp.next;
        }
        return sb.toString();
    }

}
