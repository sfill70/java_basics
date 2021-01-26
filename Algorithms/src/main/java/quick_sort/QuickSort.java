package quick_sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    public static final Random RND = new Random();

    public static void main(String[] args) {
        int[] arr = new int[]{38, 5, 8, 3, 45, 140, 77, 127, 65, 102, 91, 73, 66, 100, 80, 69, 0, 23, 4, 654, 3, 2};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to) {
        if (from < to) {
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to) {
//        int index = from + RND.nextInt(to - from + 1);
        int index = from + ((to - from) / 2);
        int pivot = array[index];
        swap(array, index, to);
        for (int i = index = from; i < to; ++i) {
            if (array[i] <= pivot) {
                swap(array, index++, i);
            }
        }
        swap(array, index, to);
        return (index);
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
