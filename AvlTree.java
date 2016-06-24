package mytree;

/**
 * Created by dell on 2016/6/24.
 */
public class AvlTree {
    public static void main(String[] args) {
        int[] nums = new int[] {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8};
        AvlNode root = null;
        for (int i = 0; i < nums.length; i++) {
            root = insert(nums[i], root);
        }
        preOrderPrint(root);
    }

    private static AvlNode insert(int val, AvlNode root) {
        if (root == null) {
            return new AvlNode(val);
        }

        if (val < root.val) {
            root.left = insert(val, root.left);
            if (height(root.left) - height(root.right) == 2) {
                if (val < root.left.val) {
                    root = rotateWithLeftChild(root);
                }
                else {
                    root = doubleWithLeftChild(root);
                }
            }
        }
        else if (val > root.val) {
            root.right = insert(val, root.right);
            if (height(root.right) - height(root.left) == 2) {
                if (val > root.right.val) {
                    root = rotateWithRighChild(root);
                }
                else {
                    root = doubleWithRightChild(root);
                }
            }
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        return root;
    }

    private static int height(AvlNode avlNode) {
        return avlNode == null? -1: avlNode.height;
    }

    private static AvlNode rotateWithLeftChild(AvlNode avlNode) {
        AvlNode root = avlNode.left;
        avlNode.left = root.right;
        root.right = avlNode;
        avlNode.height = Math.max(height(avlNode.left), height(avlNode.right)) + 1;
        root.height = Math.max(height(root.left), avlNode.height) + 1;
        return root;
    }

    private static AvlNode rotateWithRighChild(AvlNode avlNode) {
        AvlNode root = avlNode.right;
        avlNode.right = root.left;
        root.left = avlNode;
        avlNode.height = Math.max(height(avlNode.left), height(avlNode.right)) + 1;
        root.height = Math.max(height(root.right), avlNode.height) + 1;
        return root;
    }

    private static AvlNode doubleWithLeftChild(AvlNode avlNode) {
        avlNode.left = rotateWithRighChild(avlNode.left);
        return rotateWithLeftChild(avlNode);
    }

    private static AvlNode doubleWithRightChild(AvlNode avlNode) {
        avlNode.right = rotateWithLeftChild(avlNode.right);
        return rotateWithRighChild(avlNode);
    }

    private static void inOrderPrint(AvlNode avlNode) {
        if (avlNode != null) {
            inOrderPrint(avlNode.left);
            System.out.println(avlNode.val);
            inOrderPrint(avlNode.right);
        }
    }

    private static void preOrderPrint(AvlNode avlNode) {
        if (avlNode != null) {
            System.out.println(avlNode.val);
            preOrderPrint(avlNode.left);
            preOrderPrint(avlNode.right);

        }
    }
}
