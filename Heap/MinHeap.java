package basic;

/**
 * Created by dell on 2016/6/27.
 */
public class MinHeap {

    public static int length;

    public static void main(String[] args) {
//        int[] nums = new int[] {13,21,16,24,31,19,68,65,26,32,14};
        int[] nums = new int[] {13,21,16,24,31,19,68,65,26,32,0};
        length = nums.length - 1;
        buildHeap(nums);
        insert(nums, 14);
//        System.out.println("min num is " + deleteMin(nums));
        for (int i = 0; i < length; i++) {
            System.out.println(nums[i]);
        }

    }

    private static void buildHeap(int[] nums) {
        for (int i = length / 2; i >= 0; i--) {
            percolateDown(nums, i);
        }
    }

    private static void percolateDown(int[] nums, int index) {
        int tmp = nums[index];
        int child;
        for (; index * 2 + 1 < length; index = child) {
            child = index * 2 + 1;
            if (child != length - 1 && nums[child + 1] < nums[child] ) {
                child++;
            }

            if (nums[child] < tmp) {
                nums[index] = nums[child];
            }
            else {
                break;
            }
        }
        nums[index] = tmp;
    }

    private static void insert(int[] nums, int val) {
        int index = length++;
        for (; index >= 0 && val < nums[(index - 1) / 2]; index = (index - 1) / 2) {
            nums[index] = nums[(index - 1) / 2];
        }
        nums[index] = val;
    }

    private static int deleteMin(int[] nums) {
        length--;
        int min = nums[0];
        nums[0] = nums[length];
        percolateDown(nums, 0);
        return min;
    }
}
