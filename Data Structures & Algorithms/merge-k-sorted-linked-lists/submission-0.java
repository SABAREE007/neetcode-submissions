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
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0 ){
            return null;
        }

        return divideAndConquer(lists , 0 , lists.length-1);
    }

    // helper fuction : we r doing merge sort for this queestion (recursivly) 
    public ListNode divideAndConquer(ListNode[] lists , int start , int end){
        // this is the base edge case for this recurisve fucntion : -> hits when there is only single element to stop recursion
        if(start == end){
            return lists[start];
        }

        // now find the mid element of the array( we folow the merge sort algorithm)
        int mid = (start + end) / 2;

        // now call it by recursily 
        // this phase is now in dividing phase:
        ListNode leftSideLL = divideAndConquer(lists , start , mid);
        ListNode rightSideLL = divideAndConquer(lists , mid+1 , end);
        // now this phase is the conquering phase( megerge of the linked lists)
        return merge_2_LinkedList(leftSideLL , rightSideLL);
    }
    // now an important helper fucntion: merge of 2 linked list
    public ListNode merge_2_LinkedList(ListNode l1, ListNode l2){
        // to merge 2 linked list , we use the concept of dummy node
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                tail.next = l1;
                l1 = l1.next;
            }
            else{
                tail.next = l2;
                l2 = l2.next;
            }
            tail=tail.next;
        }

        // after doing it if still some ll is left out then
        if(l1 != null){
            tail.next = l1;
        }
        else{
            tail.next = l2;
        }

        return dummy.next;
    }
}