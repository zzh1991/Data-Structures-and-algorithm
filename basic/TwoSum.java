import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzh1991 on 2016/2/17.
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[] {2,7,11,15};
        int target = 17;
        int[] ret = myTwoSum(nums, target);
        System.out.format("%d %d\n", ret[0], ret[1]);
    }

    public static int[] myTwoSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return new int[] {-1, -1};
        Map<Integer, Integer> mydict = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (mydict.containsKey(target - nums[i])) {
                return new int[] {mydict.get(target-nums[i]) , i};
            }
            else{
//                mydict[i] = i;    error
                mydict.put(nums[i], i);
            }
        }
        return new int[] {-1, -1};
    }
}


