class Solution {
    public int findDuplicate(int[] nums) {
        // this is a singly linked list question whihc is hidden one 
        // here we dont have to use node like data steicture here
        // we use floyd hare and tortoise alogorithm
        // the array elemnts , consider them as the index postion where it will go
        // Step 1: Initialize slow and fast at our safe starting pad (Index 0)
        int slow = nums[0];
        int fast = nums[nums[0]];

        // Phase 1: Move slow by 1 step and fast by 2 steps until they collide
        while (slow != fast) {
            slow = nums[slow];          // slow = slow.next
            fast = nums[nums[fast]];    // fast = fast.next.next
        }

        // Phase 2: Find the entrance of the loop (the duplicate number)
        // Reset slow back to the starting pad (Index 0)
        slow = 0;
        
        // Move both slow and fast at the exact same speed (1 step at a time)
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        // When they meet again, they are standing exactly on the duplicate number!
        return slow;


    }
}