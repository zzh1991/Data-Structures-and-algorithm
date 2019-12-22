import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author zhihao zhang
 * @date 11/24/19
 */

public class TreeTraversal {
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        TreeNode tmpNode;
        while(!stack.isEmpty()) {
            tmpNode = stack.pop();
            result.add(tmpNode.val);
            if (Objects.nonNull(tmpNode.right)) {
                stack.push(tmpNode.right);
            }
            if (Objects.nonNull(tmpNode.left)) {
                stack.push(tmpNode.left);
            }
        }
        return result;
    }

    public static List<Integer> inOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        while (Objects.nonNull(root) || !stack.isEmpty()) {
            if (Objects.nonNull(root)) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (Objects.isNull(root)) {
            return result;
        }
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pre = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.peek();
            boolean isNoChild = Objects.isNull(root.left) && Objects.isNull(root.right);
            boolean isVisited = pre.equals(root.right) || (pre.equals(root.left) && Objects.isNull(root.right));

            if (isNoChild || isVisited) {
                root = stack.pop();
                result.add(root.val);
                pre = root;
            } else {
                if (Objects.nonNull(root.right)) {
                    stack.push(root.right);
                }
                if (Objects.nonNull(root.left)) {
                    stack.push(root.left);
                }
            }
        }
        return result;
    }
}
