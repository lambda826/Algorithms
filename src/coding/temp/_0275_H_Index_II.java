/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

/*

Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm?

*/

public class _0275_H_Index_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int hIndex_BinarySearch(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int lo = 0;
        int hi = citations.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (citations[mid] == citations.length - mid) {
                return citations.length - mid;
            } else if (citations[mid] > citations.length - mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }
        return citations.length - lo;
    }

    //    citations[mid]
    public static void main(String[] args) {
        _0275_H_Index_II test = new _0275_H_Index_II();
        int[] citattions = { 100, 100 };
        System.out.println(test.hIndex_BinarySearch(citattions));

    }
}
