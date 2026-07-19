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
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }

        // this will be a 3 pointer, we assign them to track ( 2 pointer approach on linked list)
        ListNode prev = null;
        ListNode current = head;
        ListNode nextNode = null;

        while( current != null){
            // first we copy the next element of the current element to the nextNode variable because in next steps we r ging to change the idrection of pointers so 
            nextNode = current.next;
            // now swap the pointer direction
            current.next = prev;  // why prev becauuse we r reversing right 

            // now very very important steps
            prev = current; // move the prev one step forward;(why because prev is set on starting null so for the next Iteration's current node , its previous will be stored here itself)
            current = nextNode;  // moving to next node; 
        }
        return prev; // when the current node touches null , the prev Node will become the starting point(head) for the reversed linked list
        
    }
}
