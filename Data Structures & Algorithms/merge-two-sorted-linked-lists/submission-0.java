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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode Dummy = new ListNode(0);
        ListNode tail = Dummy;

        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }
            else{   // if list2 element is smaller
                tail.next = list2;
                list2 = list2.next;
            }
            // very very important step; we shpuld move after each iteation to next tail node to attack the next node or else it will go infinite loop
            tail = tail.next;
        }

        // step 3 : attack remaining linked lists
        if(list1 != null){
            tail.next= list1;
        }
        else{
            tail.next = list2;
        }

        // very very important step; always returen dummy's next element because it will be the starting head of the linked list 
        return Dummy.next;
    }
}