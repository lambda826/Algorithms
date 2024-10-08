/**
 * @author Yunxiang He
 * @date Jan 14, 2018 2:04:17 AM
 */

package questions._05_binarySearch;

/*

You are a product manager and currently leading a team to develop a new product. 
Unfortunately, the latest version of your product fails the quality check. 
Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. 
Implement a function to find the first bad version. 
You should minimize the number of calls to the API.

 */

public class _0278_First_Bad_Version {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int firstBadVersion_BinarySearch(int n) {
        int lo = 1;
        int hi = n;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /*
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     */
    private boolean isBadVersion(int version) {
        return true;
    }
}
