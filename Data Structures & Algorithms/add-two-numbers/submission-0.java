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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        // track the carry 
        int carry =0;

        // step 2: loop runs untill there both lists are null and carry is 0
        while(l1 != null || l2 != null || carry != 0){
            int sum =0;  // at each column of digit at starting sum is 0

            //extract the digit from the lists and add
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }

            // if the cafrry from before sum is there ,then add it also
            sum += carry;

            // very very impoaratn step: calculte the new carry if there for then it shoulbe used for adding next consequitve paralel numbers
            carry = sum/10;  // in java this will return the first digit if number is more than 1 digit or else it returns 0 because no carry is there and it is a single digit

            temp.next = new ListNode(sum % 10); // if number is more than single digit then there is carry so -> we have to only update the unts digit vale to the node and take its carry forward to next number addtion
            temp = temp.next;
        }

        return dummy.next;
    }
}