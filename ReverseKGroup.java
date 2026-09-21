
public class ReverseKGroup {

    public class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        //伪头
        ListNode dummuy = new ListNode(0);
        dummuy.next = head;

        ListNode end = dummuy.next;
        ListNode pre = dummuy;
        ListNode start = dummuy.next;
        while (end != null) {
            //一组k
            for (int i = 1; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;//不够一组k就退出
            }
            //一组k反转
            ListNode endNext = end.next;//保存end.next
            end.next = null;//断开
            pre.next = reverse(start);//反转之后start在尾
            start.next = endNext;
            pre = start;//更新pre
            start = start.next;//更新start
            end = start;//更新end

        }
        return dummuy.next;

    }

    public ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        ReverseKGroup solution = new ReverseKGroup();

        ListNode first = solution.buildList(1, 2, 3, 4, 5);
        solution.checkList("k = 2", solution.reverseKGroup(first, 2), 2, 1, 4, 3, 5);

        ListNode second = solution.buildList(1, 2, 3, 4, 5);
        solution.checkList("k = 3", solution.reverseKGroup(second, 3), 3, 2, 1, 4, 5);

        ListNode single = solution.buildList(1);
        solution.checkList("single node", solution.reverseKGroup(single, 1), 1);

        solution.checkList("empty list", solution.reverseKGroup(null, 2));

        System.out.println("All tests passed!");
    }

    private ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }
        return dummy.next;
    }

    private void checkList(String testName, ListNode head, int... expected) {
        ListNode current = head;
        for (int value : expected) {
            if (current == null || current.val != value) {
                throw new AssertionError(testName + " failed");
            }
            current = current.next;
        }
        if (current != null) {
            throw new AssertionError(testName + " failed: result is longer than expected");
        }
        System.out.println("Passed: " + testName);
    }
}
