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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Edge Case: If list is empty, has 1 node, or group size is 1, no changes happen
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode temp = head;
        ListNode prevLast = null; // Tracks the tail node of the previously completed reversed group
        ListNode newHead = null;  // Stores the final master head node to return

        while (temp != null) {
            // Step 1: Find the k-th node of the current segment
            ListNode kthNode = getKthNode(temp, k);

            // Guard Clause: If there aren't enough nodes left to form a complete group of size k
            if (kthNode == null) {
                if (prevLast != null) {
                    prevLast.next = temp; // Link previous group tail to the remaining untouched list
                }
                break;
            }

            // Step 2: Capture the start of the next block and isolate the current group
            ListNode nextBlockHead = kthNode.next;
            kthNode.next = null; // 🪓 Cut the track to form a clean standalone k-length list

            // Step 3: Reverse the isolated k-length segment
            ListNode reversedGroupHead = reverseList(temp);

            // Step 4: Stitch the reversed block back into the master chain
            if (temp == head) {
                // This was the very first group, so its new head becomes the main list head
                newHead = reversedGroupHead;
            } else {
                // Link the tail of the previous group to the head of this newly reversed group
                prevLast.next = reversedGroupHead;
            }

            // The original 'temp' node was the head, which means after reversal, it is now the tail!
            prevLast = temp; 
            
            // Advance forward to the start of the next block
            temp = nextBlockHead;
        }

        // If newHead was never set (e.g. total nodes < k), return the original head
        return newHead != null ? newHead : head;
    }

    // 🏃‍♂️ Helper 1: Scans forward k steps to find the k-th node boundary
    private ListNode getKthNode(ListNode temp, int k) {
        k -= 1; // Since we are already standing on step 1
        while (temp != null && k > 0) {
            k--;
            temp = temp.next;
        }
        return temp;
    }

    // 🔄 Helper 2: Standard in-place 3-pointer linked list reversal
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev; // Returns the head of the reversed list segment
    }
}

