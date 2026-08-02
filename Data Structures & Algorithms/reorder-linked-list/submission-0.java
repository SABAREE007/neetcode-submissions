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
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return;
        }

        // find the middle node of the ll -> to reverse the second half
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode rightHead = slow.next;
        slow.next = null;   // cut the halfs so to get 2 separate LLs

        ListNode p1 = head;
        ListNode p2 = reverseList(rightHead);

        while(p2 != null){
            ListNode next1 = p1.next;
            ListNode next2 = p2.next;   // get both of the halfs next node for the next interation purpose
            // now connect according the logic
            // first left half node followed by reversed second half first node , then continue
            p1.next = p2;
            p2.next = next1;   // very important step connect the next node of the first half node

            // move to next nodes
            p1 = next1;
            p2 = next2;
        }
    }
    private ListNode reverseList(ListNode head){
        ListNode curr = head;
        ListNode prev = null;
        ListNode nextNode = null;
        while(curr != null){
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}