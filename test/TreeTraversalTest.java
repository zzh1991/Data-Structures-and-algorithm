import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class TreeTraversalTest {

    private static TreeNode root;

    private static final List<Integer> preOrderList = Lists
            .newArrayList(1, 2, 4, 5, 3, 6, 7);

    private static final List<Integer> inOrderList = Lists
            .newArrayList(4, 2, 5, 1, 6, 3, 7);

    private static final List<Integer> postOrderList = Lists
            .newArrayList(4, 5, 2, 6, 7, 3, 1);

    static {
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
    }

    @Test
    void preOrderTraversal() {
        assertThat(TreeTraversal.preOrderTraversal(root))
                .isEqualTo(preOrderList);
    }

    @Test
    void inOrderTraversal() {
        assertThat(TreeTraversal.inOrderTraversal(root))
                .isEqualTo(inOrderList);
    }

    @Test
    void postOrderTraversal() {
        assertThat(TreeTraversal.postOrderTraversal(root))
                .isEqualTo(postOrderList);
    }
}