/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class ReverseList {
    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
    public static void main(String[] args) {
        ReverseList solution = new ReverseList();

        ListNode head = solution.buildList(1, 2, 3, 4, 5);
        ListNode reversed = solution.reverseList(head);
        solution.checkList("反转 1->2->3->4->5", reversed, 5, 4, 3, 2, 1);

        ListNode single = solution.buildList(7);
        ListNode reversedSingle = solution.reverseList(single);
        solution.checkList("反转单节点", reversedSingle, 7);

        ListNode empty = solution.reverseList(null);
        solution.checkList("反转空链表", empty);

        System.out.println("所有测试通过！");
    }

    public ListNode reverseList(ListNode head) {
        if(head==null) return null;

        ListNode pre=null;
        ListNode cur=head;
        while(cur!=null){
            ListNode tmp=cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        return pre;
    }

    private ListNode buildList(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int value : values) {
            cur.next = new ListNode(value);
            cur = cur.next;
        }
        return dummy.next;
    }

    private void checkList(String description, ListNode head, int... expected) {
        ListNode cur = head;
        for (int value : expected) {
            if (cur == null || cur.val != value) {
                throw new AssertionError(description + "：测试失败");
            }
            cur = cur.next;
        }
        if (cur != null) {
            throw new AssertionError(description + "：链表长度超过预期");
        }
        System.out.println("通过：" + description);
    }
}
