package tree;
import java.util.*;

/**
 * Created by zzh1991 on 2016/2/20.
 */
public class MyBinaryTree {
    public static void main (String[] args) {
        int[] nums = new int[] {3,1,2,5,4,6};
        TreeNode root = null;//new TreeNode(nums[0]);
        for (int i = 0; i < nums.length; i++) {
           root = insert(nums[i],root);
        }

//        root = sortedArrayToBST(nums, 0, nums.length - 1);
//        root = insertNode(root, new TreeNode(1));       //insert Node  iter
//        TreeNode retNode = lowestCommonAncestor(root, 4, 6);
//        System.out.println(retNode.val);

        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        System.out.println(sb);
        StringTokenizer st = new StringTokenizer(sb.toString(), ",");
        root = deserialize(st);

        List<Integer> ret = preorderTraversal(root);

//        System.out.println(isValidBST(root,Long.MIN_VALUE, Long.MAX_VALUE));
//        List<Integer> ret = inorderTraversal(root);
//        List<Integer> ret = postorderTraversal(root);
        for (Integer num : ret) {
            System.out.println(num.toString());
        }

//        List<List<Integer>> list = levelOrder(root);
//        for (List l : list) {
//            System.out.println(l.toString());
//        }

//        System.out.println(isBalanced(root));

//        ArrayList<Integer> list = new ArrayList<>();
//        searchRange(root, 2,4, list);
//        for (Integer num: list) {
//            System.out.println(num.toString());
//        }

    }

    public static TreeNode insert (int val, TreeNode node) {
        if (node == null) {
            return new TreeNode(val);
        }

        if (node.val > val) {
//            if (node.left == null) node.left = new TreeNode(val);
            node.left = insert(val, node.left);
        }
        else if (node.val < val) {
//            if (node.right == null) node.right = new TreeNode(val);
            node.right = insert(val, node.right);
        }
        return node;
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
    }

    public static List<Integer> inorderTraversal( TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            }
            else {
                root = stack.pop();
//                if (root.val >= k1 && root.val <= k2)         search Range in a binary tree
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public static List<Integer> postorderTraversal( TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode curr = stack.peek();
            boolean noChild = (curr.left == null) && (curr.right == null);
            boolean childVisted = false;
            if (prev != null && (curr.left == prev || curr.right == prev)) {
                childVisted = true;
            }
            if( noChild || childVisted) {
                result.add(curr.val);
                stack.pop();
                prev = curr;
            } else {
                if (curr.right != null) stack.push(curr.right);
                if (curr.left != null) stack.push(curr.left);
            }

        }
        return result;
    }

    public static List<List<Integer>> levelOrder( TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        int depth = 0;
        q.offer(root);
        while (!q.isEmpty()) {
            int qSize = q.size();
            depth++;
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i< qSize; i++) {
                TreeNode node = q.poll();
                list.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            result.add(new ArrayList<Integer>(list));
        }
        Collections.reverse(result);
        return result;
    }

    public static boolean isBalanced (TreeNode root) {
        return maxDepth(root) != -1;
    }

    public static int maxDepth (TreeNode root) {
        if (root == null) return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        if (leftDepth == -1 || rightDepth == -1 || Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static TreeNode insertNode (TreeNode root, TreeNode node) {
        if (root == null) return node;
        if (node == null) return root;

        TreeNode rootcopy = root;
        while (root != null) {
            if (root.val >= node.val && root.left == null) {
                root.left = node;
                break;
            }
            else if (root.val < node.val && root.right == null) {
                root.right = node;
                break;
            }
            else if (root.val >= node.val) root = root.left;
            else  root = root.right;
        }
        return rootcopy;
    }

    public static boolean isValidBST (TreeNode root, long lower, long upper) {
        if (root == null) return true;

        if (root.val < lower || root.val >= upper ) return false;
        boolean isLeftValidBST = isValidBST(root.left,lower, root.val);
        boolean isRightValidBST = isValidBST(root.right, root.val, upper);

        return isLeftValidBST && isRightValidBST;
    }

    public static void searchRange (TreeNode root, int k1, int k2, ArrayList<Integer> list) {
//        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return;
        if (root.left != null && root.val >= k1) {
            searchRange(root.left, k1, k2, list);
        }
        if (root.val >= k1 && root.val <= k2) {
            list.add(root.val);
        }
        if (root.right != null && root.val < k2) {
            searchRange(root.right, k1, k2, list);
        }
    }

    public static TreeNode sortedArrayToBST (int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) return null;

        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);

        return root;
    }

    public static TreeNode lowestCommonAncestor (TreeNode root, int A, int B) {
        if (root == null) return null;

        TreeNode lNode = lowestCommonAncestor(root.left, A, B);
        TreeNode rNode = lowestCommonAncestor(root.right, A, B);
        if (lNode != null && rNode != null) return root;
        if (root.val == A || root.val == B) return root;
        return (lNode != null)? lNode : rNode;
    }

    public static void serialize (TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("#,");
        else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }

    public static TreeNode deserialize (StringTokenizer st) {
        if (!st.hasMoreTokens()) return null;

        String val = st.nextToken();
        if (val.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialize(st);
        root.right = deserialize(st);
        return root;
    }
}
