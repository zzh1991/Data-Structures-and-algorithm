package basic;
/**
 * Created by zzh1991 on 2016/2/18.
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = new int[] {1,2,3,3,4,5,10};
        int target = 3;
//        System.out.println(myBinarySearch(nums, target));   //binery search
//        System.out.println(searchInsert(nums, target));     // binary search insert
        int[] result = searchRange(nums, target);
        System.out.println(result[0] + " " + result[1]);
    }

    public static int myBinarySearch (int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = -1;
        int end = nums.length;
        int mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target ) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (end == nums.length || nums[end] != target) {
            return -1;
        }
        else {
            return end;
        }
    }

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int start = -1;
        int end = nums.length;
        int mid = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            else if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        return start + 1;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] ret = new int[] {-1, -1};
        if (nums == null || nums.length == 0) {
            return ret;
        }
        int start = -1;
        int end = nums.length;
        int mid  = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start + 1] < nums.length && nums[start + 1] == target) {
            ret[0] = start + 1;
        } else {
            ret[0] = -1;
            ret[1] = -1;
            return ret;
        }

        end = nums.length;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        ret[1] = end - 1;
        return ret;
    }
}
