/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-18
 */

package questions.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Third_Smallest_Common_Element {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int common(int[] A, int[] B, int[] C) {
        if (B.length >= A.length && B.length >= C.length) {
            return common(B, A, C);
        } else if (C.length >= A.length && C.length >= B.length) {
            return common(C, A, B);
        }
        if (A.length < 3 || B.length < 3 || C.length < 3) {
            return -1;
        }
        Arrays.sort(A);
        List<Integer> AB = new ArrayList<>();
        for (int b : B) {
            int idx = Arrays.binarySearch(A, b);
            if (idx > 0) {
                AB.add(b);
            }
        }
        Collections.sort(AB);
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer a1, Integer a2) -> a2 - a1);
        for (int c : C) {
            int idx = Collections.binarySearch(AB, c);
            if (idx > 0) {
                pq.add(c);
                if (pq.size() > 3) {
                    pq.remove();
                }
            }
        }
        return pq.size() == 3 ? pq.remove() : -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // HashMap

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BucketSort

    public static void main(String[] args) {
        System.out.println(new Third_Smallest_Common_Element().common(new int[] { 1, 2, 4, 5, 6, 7, 8, 9, 10, 22, }, new int[] { 6, 7, 9, 3, 4, 5, }, new int[] { 1, 5, 6, 7, 9, 10 }));
    }

}
