/**
 *  @author Yunxiang He
 *  @date 02/17/2019
 */

package coding.company.amazon.debug;

public class SortArray {

    public static void main(String[] args) {
        reverseArray(new int[] { 20, 30, 10, 40, 50 });
    }

    // reverse array: arr[len-1] 改成 arr[len-i-1], 循环结束前去掉 len+=1;
    public static int[] reverseArray(int arr[]) {
        int i, temp, orginalLen = arr.length;
        int len = orginalLen;
        for (i = 0; i < orginalLen / 2; i++) {
            temp = arr[len - 1 - i]; //////////////
            arr[len - i - 1] = arr[i]; ////////////////////
            arr[i] = temp;
            // len += 1;
        }
        return arr;
    }
}
