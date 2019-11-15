import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhihao zhang
 * @date 11/15/19
 */

public class MergeKLists {
    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1, 4, 5},
                {1, 3, 4},
                {2, 6},
        };
        ListNode listNode = mergeKLists(ListNode.convert2DArrayToListNodeArray(array));
        ListNode.printListNode(listNode);
    }

    private static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(
                lists.length,
                Comparator.comparing(listNode -> listNode.val));

        ListNode dummy = new ListNode(0);
        for (ListNode listNode: lists) {
            if (listNode != null) {
                priorityQueue.add(listNode);
            }
        }

        ListNode cur = dummy;
        ListNode popNode;
        while(!priorityQueue.isEmpty()) {
            cur.next = priorityQueue.peek();
            popNode = priorityQueue.poll();
            if (popNode.next != null) {
                priorityQueue.add(popNode.next);
            }
            cur = popNode;
        }

        return dummy.next;
    }
}
