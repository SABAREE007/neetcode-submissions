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

public class Solution {
    public boolean hasCycle(ListNode head) {
        // this question follows a two pointer pattern(Tortoise algoritm-> fast and slow poniter concept)
        ListNode slow = head;
        ListNode fast = head;

        // very very important step
        // this while loop tells us that: if its condition fails then the linked list is a linear list
        while(/*for even length*/ fast != null && /*for odd length*/ fast.next != null){
            slow = slow.next;  // move 1 step ahead;
            fast = fast.next.next;  // move 2 steps ahead;

            // at some point of time if a linked list is circular then at some point the slow and fast pointer will point to the same node which indicates that there is a loop in this linked list;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
