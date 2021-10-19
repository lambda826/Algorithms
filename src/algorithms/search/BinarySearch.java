package algorithms.search;

import java.util.Arrays;

public class BinarySearch {

    public int binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid;
        do {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        } while (lo <= hi);
        return mid;
    }


    public int find_target(int[] nums, int target) {
        if (nums[nums.length - 1] == target) {
            return nums[nums.length - 1];
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return -1;
    }

    public int find_target2(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return nums[mid];
            } else if (nums[mid] > target) {
                hi = mid - 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            }
        }
        return -1;
    }

    public int find_The_Smallest_Element_Greater_Than_Target(int nums[], int target) {
        if (nums[nums.length - 1] <= target) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        // System.out.println("lo : " + lo + " ; hi : " + hi);
        return nums[lo];
    }

    public int find_The_Smallest_Element_Greater_Than_Target2(int nums[], int target) {
        // Optional
        //        if (nums[nums.length - 1] <= target ) {
        //            return -1;
        //        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo > nums.length - 1 ? -1 : nums[lo];
    }

    public int find_The_Smallest_Element_No_Smaller_Than_Target(int nums[], int target) {
        if (nums[nums.length - 1] < target) {
            return -1;
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return nums[lo];
    }

    public int find_The_Smallest_Element_No_Smaller_Than_Target2(int nums[], int target) {
        // Optional
        //        if (nums[nums.length - 1 ]< target ) {
        //            return nums[nums.length - 1];
        //        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo > nums.length - 1 ? -1 : nums[lo];
    }

    public int find_The_Greatest_Element_Smaller_Than_Target(int nums[], int target) {
        if (nums[nums.length - 1] < target) {
            return nums[nums.length - 1];
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == 0 ? -1 : nums[lo - 1];
    }

    public int find_The_Greatest_Element_Smaller_Than_Target2(int nums[], int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo == 0 ? -1 : nums[lo - 1];
        //        return hi == -1 ? -1 : nums[hi];
    }

    public int find_The_Greatest_Element_No_Greater_Than_Target(int nums[], int target) {
        if (nums[nums.length - 1] <= target) {
            return nums[nums.length - 1];
        }
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo == 0 ? -1 : nums[lo - 1];
        //      return hi == -1 ? -1 : nums[hi];
    }

    public int find_The_Greatest_Element_No_Greater_Than_Target2(int nums[], int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo == 0 ? -1 : nums[lo - 1];
        //      return hi == -1 ? -1 : nums[hi];
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = { 0, 3, 5, 8, };
        int[] targets = { -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, };

        for (int target : targets) {
            System.out.println(bs.find_target(nums, target) == bs.find_target2(nums, target));
            System.out.println(bs.find_The_Smallest_Element_Greater_Than_Target(nums, target) == bs.find_The_Smallest_Element_Greater_Than_Target2(nums, target));
            System.out.println(bs.find_The_Smallest_Element_No_Smaller_Than_Target(nums, target) == bs.find_The_Smallest_Element_No_Smaller_Than_Target2(nums, target));
            System.out.println(bs.find_The_Greatest_Element_Smaller_Than_Target(nums, target) == bs.find_The_Greatest_Element_Smaller_Than_Target2(nums, target));
            System.out.println(bs.find_The_Greatest_Element_No_Greater_Than_Target(nums, target) == bs.find_The_Greatest_Element_No_Greater_Than_Target2(nums, target));
        }
        for (int target : targets) {
            System.out.println(Arrays.binarySearch(nums, target));
        }

    }
}
