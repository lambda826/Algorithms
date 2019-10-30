/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

import java.util.Arrays;

/*

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: 
"A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.

Note: If there are several possible values for h, the maximum one is taken as the h-index.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.

 */

public class _0274_H_Index {

    public int hIndex_Count(int[] citations) {
        int[] counts = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] < counts.length) {
                counts[citations[i]] += 1;
            } else {
                counts[counts.length - 1] += 1;
            }

        }
        int h = 0;
        int sum = 0;
        for (int i = counts.length - 1; i >= 0; i--) {
            sum += counts[i];
            if (sum >= i) {
                h = i;
                break;
            }
        }
        return h;
    }

    public int hIndex_Sort(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length - i) {
                h = citations.length - i;
                break;
            }
        }
        return h;
    }

    public static void main(String[] args) {
        _0274_H_Index test = new _0274_H_Index();
        //        int[] citations = { 1, 1 };
        //        int[] citations = { 1 };
        int[] citations = { 3, 0, 6, 1, 5, 2, 3, 3, 2, 2, 4 };
        System.out.println(test.hIndex_Sort(citations));
        System.out.println(test.hIndex_Count(citations));
    }
}
