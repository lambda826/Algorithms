package algorithms.sorting;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    public static int[] sort(int[] A) {
        int j;
        for (int i = 1; i < A.length; i++) {
            j = i;
            while (j > 0 && A[j - 1] > A[j]) {
                A[j] = A[j - 1];
                j--;
            }
            A[j] = A[i];
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
