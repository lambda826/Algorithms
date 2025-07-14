package questions.leetcode.tree.dfs;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*

You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
int next() Returns the next integer in the nested list.
boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
If res matches the expected flattened list, then your code will be judged as correct.


Example 1:
    Input: nestedList = [[1,1],2,[1,1]]
    Output: [1,1,2,1,1]
    Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].

Example 2:
    Input: nestedList = [1,[4,[6]]]
    Output: [1,4,6]
    Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].


Constraints:
    1 <= nestedList.length <= 500
    The values of the integers in the nested list is in the range [-10^6, 10^6].

*/
public class _0341_Flatten_Nested_List_Iterator {

    public interface NestedInteger {
        boolean isInteger();

        Integer getInteger();

        List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private final List<Integer> list;
        private int index;

        public NestedIterator(List<NestedInteger> nestedList) {
            list = new ArrayList<>();
            dfs(nestedList);
        }

        private void dfs(List<NestedInteger> nestedList) {
            if (nestedList != null) {
                for (NestedInteger node : nestedList) {
                    if (node.isInteger()) {
                        list.add(node.getInteger());
                    } else {
                        dfs(node.getList());
                    }
                }
            }
        }

        @Override
        public Integer next() {
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index != list.size();
        }

    }
}
