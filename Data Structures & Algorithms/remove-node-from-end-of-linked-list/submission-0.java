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
    public ListNode removeNthFromEnd(ListNode head, int n) {
    
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // for this question sytart slow and fast pointer on dummy node, since slow stops exactly one node before nTH node so
        ListNode slow = dummy;
        ListNode fast = dummy;

        // step 1: move the fast pointer n times
        for(int i=0;i<n;i++){
            fast= fast.next;
        }

        // step 2: after reaching n times , now move both slow and fast pointer simultaneosly untill fast.next becomes null(fast poits to last node)
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        // step 3: fast reaches the last node, this means slow pointer has reacger the nefore node of the Nth node so (this is the important part)
        // we link the before pointer to the node we hav to delete's next pointer
        slow.next = slow.next.next;

        //step 4: this is the most importnt part 
        return dummy.next; // since dummy .next points the head
    }
}