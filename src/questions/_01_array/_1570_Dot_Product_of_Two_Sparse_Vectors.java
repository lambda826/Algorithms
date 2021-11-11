package questions._01_array;

/*

Given two sparse vectors, compute their dot product.

Implement class SparseVector:
SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.


Follow up:
    What if only one of the vectors is sparse?


Example 1:
    Input:
        nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
    Output:
        8
    Explanation:
        v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
        v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8

Example 2:
    Input:
        nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
    Output:
        0
    Explanation:
        v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
        v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0

Example 3:
    Input:
        nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
    Output: 6


Constraints:
    n == nums1.length == nums2.length
    1 <= n <= 10^5
    0 <= nums1[i], nums2[i] <= 100

*/

import java.util.ArrayList;
import java.util.List;

public class _1570_Dot_Product_of_Two_Sparse_Vectors {

    class SparseVector {

        List<int[]> list;

        SparseVector(int[] nums) {
            list = new ArrayList<>();
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) {
                    list.add(new int[] { i, nums[i] });
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            List<int[]> list1 = this.list;
            List<int[]> list2 = vec.list;
            if (list1.size() > list2.size()) {
                return vec.dotProduct(this);
            }
            if (list1.size() == 0 || list2.size() == 0) {
                return 0;
            }
            int i1 = 0;
            int i2 = 0;
            int sum = 0;
            while (i1 < list1.size() && i2 < list2.size()) {
                if (list1.get(i1)[0] == list2.get(i2)[0]) {
                    sum += list1.get(i1)[1] * list2.get(i2)[1];
                    ++i1;
                    ++i2;
                } else if (list1.get(i1)[0] > list2.get(i2)[0]) {
                    ++i2;
                } else {
                    ++i1;
                }
            }
            return sum;
        }
    }
}
