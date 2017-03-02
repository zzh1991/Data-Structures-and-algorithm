/**
 * Created by zzh1991 on 2016/6/24.
 */
public class AvlNode {

    int val;
    AvlNode left;
    AvlNode right;
    int height;

    public AvlNode(int val) {
        this.val = val;
        left = null;
        right = null;
        height = 0;
    }

}
