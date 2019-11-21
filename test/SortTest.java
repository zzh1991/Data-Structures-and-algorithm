import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortTest {
    private static  final int[] originalArray = new int[]{5, 9, 4, 6, 5, 3};
    private static final int[] sortedArray = new int[]{3, 4, 5, 5, 6, 9};



    @Test
    void quickSort() {
        int[] array = new int[originalArray.length];
        System.arraycopy(originalArray, 0, array, 0, originalArray.length);
        Sort.quickSort(array, 0, array.length - 1);
        assertThat(array).isEqualTo(sortedArray);
    }

    @Test
    void mergeSort() {
        int[] array = new int[originalArray.length];
        System.arraycopy(originalArray, 0, array, 0, originalArray.length);
        Sort.mergeSort(array, array.length);
        assertThat(array).isEqualTo(sortedArray);
    }
}