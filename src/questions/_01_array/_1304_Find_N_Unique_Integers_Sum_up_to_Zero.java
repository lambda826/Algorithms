package questions._01_array;

public class _1304_Find_N_Unique_Integers_Sum_up_to_Zero {


    public int[] sumZero(int n) {
        int[] res = new int[n];
        int lo = 0;
        int hi = n - 1;
        int num = 1;
        while (lo < hi) {
            res[lo++] = num;
            res[hi--] = -num;
            ++num;
        }
        return res;
    }
}
