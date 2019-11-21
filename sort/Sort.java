import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author zhihao zhang
 * @date 11/21/19
 */

public class Sort {

    private static final int SIZE_TWO = 2;

    public static void main(String[] args) {
        int[] array = new int[]{5, 9, 4, 6, 5, 3};
//        quickSort(array, 0, array.length - 1);
        mergeSort(array, array.length);
        System.out.println(Arrays.stream(array)
                .boxed()
                .collect(Collectors.toList()));
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1;

        int tmp;
        while (start < end) {
            if (array[start] <= pivot) {
                i++;
                tmp = array[i];
                array[i] = array[start];
                array[start] = tmp;
            }
            start++;
        }

        tmp = array[i + 1];
        array[i + 1] = pivot;
        array[end] = tmp;

        return i + 1;
    }

    private static void mergeSort(int[] array, int length) {
        if (length < SIZE_TWO) {
            return;
        }

        int median = length / 2;
        int[] leftArray = new int[median];
        int[] rightArray = new int[length - median];
        System.arraycopy(array, 0, leftArray, 0, median);
        System.arraycopy(array, median, rightArray, 0, length - median);

        mergeSort(leftArray, median);
        mergeSort(rightArray, length - median);
        merge(array, leftArray, rightArray, median, length - median);
    }

    private static void merge(int[] array,
                               int[] leftArray, int[] rightArray,
                               int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }

        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }
}
