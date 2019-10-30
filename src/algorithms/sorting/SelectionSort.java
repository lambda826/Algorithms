package algorithms.sorting;

import common.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Random;

/*
Find the minimum value in the list
Swap it with the value in the current position
Repeat this process for all the elements until the entire array is sorted
in-place
*/
public class SelectionSort {

    public static int[] sort(int[] A) {
        int min;
        for (int i = 0; i < A.length - 1; i++) {
            min = i;
            for (int j = i + 1; j < A.length; j++) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            ArrayUtils.swap(A, min, i);
        }
        return A;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] A = new int[10];
        for (int i = 0; i < A.length; i++) {
            A[i] = random.nextInt(20);
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(sort(A)));
    }

}
