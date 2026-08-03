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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null || left == right){
            return head;
        }

        // first we create a dummy node and at6ach its next with the head of the given ll
        ListNode dummy = new ListNode(0 , head);
        // very very imporatnt step -> we maintain a prev node so after reversing , we can attch this prev.next to the new head after the reverded ll
        ListNode prev = dummy;

        // step 1: we move the prev node untill before the left node (left -1)
        for(int i=0 ; i<left - 1;i++){
            prev = prev.next;
        }
        // since we have prev whihc is pointing t the before node of left node so the left node will be 
        ListNode curr = prev.next;

        // step 3: very very imporatn step;
        // we have to reverse the ll from the left to the respective right side given
        // since it is a singly ll we reverse the linkes one by one 

        for(int i=0; i< right-left;i++){
            // first we preverse the nextnode
            ListNode nextNode = curr.next;
            // we do this iteration by uation so now curr's next should point to the next node of the nextnode
            curr.next = nextNode.next;
            // since the nextNode is now broken and stays alone , we attack it to the next node of previous(whihc is the current node) to maintain the order
            nextNode.next = prev.next;// prev.next will be the cuurent node)
            // now to maintin the order
            prev.next = nextNode;
        }
        return dummy.next;
    }
}
