package merge_sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{38, 5, 8, 3, 45, 140, 77, 127, 65, 102, 91, 73, 66, 100, 80, 69, 0, 23, 4, 654, 3, 2};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int lPos = 0;
        int rPos = 0;
        int index = 0;
        while (lPos < left.length && rPos < right.length) {
            if (left[lPos] <= (right[rPos])) {
                array[index++] = left[lPos++];
            } else {
                array[index++] = right[rPos++];
            }
        }
        while (lPos < left.length) {
            array[index++] = left[lPos++];
        }
        while (rPos < right.length) {
            array[index++] = right[rPos++];
        }
    }
}
