/**
 * @author zhihao zhang
 * @date 11/15/19
 */

public class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static ListNode convertArrayToListNode(int[] array) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode tmp;
        for (int item : array) {
            tmp = new ListNode(item);
            cur.next = tmp;
            cur = tmp;
        }
        cur.next = null;
        return dummy.next;
    }

    public static ListNode[] convert2DArrayToListNodeArray(int[][] array) {
        if (array.length == 0) {
            return new ListNode[0];
        }
        ListNode[] listNodes = new ListNode[array.length];
        int i = 0;
        for (int[] oneArray : array) {
            listNodes[i++] = convertArrayToListNode(oneArray);
        }
        return listNodes;
    }

    public static void printListNode(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val);
            if (listNode.next != null) {
                System.out.print("->");
            }
            listNode = listNode.next;
        }

    }
}
