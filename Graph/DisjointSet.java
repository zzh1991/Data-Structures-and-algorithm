package basic;

import java.util.Arrays;

/**
 * Created by dell on 2016/6/30.
 */
public class DisjointSet {
    public static void main(String[] args) {
        int n = 8;
        int[] nums = new int[n];
        Arrays.fill(nums, -1);

        // do union
        union(nums, 4, 5);
        union(nums, 6, 7);
        union(nums, 4, 6);
        union(nums, 3, 4);
        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println(find(nums, 3) == find(nums, 1));
    }

    // union by size
    private static void union(int[] nums, int root1, int root2) {
        if (nums[root1] <= nums[root2]) {
            nums[root1] += nums[root2];
            nums[root2] = root1;
        }
        else {
            nums[root2] += nums[root1];
            nums[root1] = root2;
        }
    }

    // path compression
    private static int find(int[] nums, int index) {
        if (nums[index] < 0) return index;
        else {
            return nums[index] = find(nums, nums[index]);
        }
    }
}
