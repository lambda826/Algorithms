/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package questions.temp;

import java.util.Iterator;

/*

Given an Iterator class interface with methods: next() and hasNext(), 
design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().


Example:
    Assume that the iterator is initialized to the beginning of the list: [1,2,3].
    Call next() gets you 1, the first element in the list.
    Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
    You call next() the final time and it returns 3, the last element. 
    Calling hasNext() after that should return false.

*/

public class _0284_Peeking_Iterator implements Iterator<Integer> {

    private final Iterator<Integer> iterator;
    private Integer peek;

    public _0284_Peeking_Iterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
        if (iterator.hasNext()) {
            peek = iterator.next();
        }
    }

    public Integer peek() {
        return peek;
    }

    @Override
    public Integer next() {
        int temp = peek;
        peek = iterator.hasNext() ? iterator.next() : null;
        return temp;
    }

    @Override
    public boolean hasNext() {
        return peek != null;
    }
}
