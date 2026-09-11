/** 
 * Definition for a binary tree node. 
 * public class TreeNode { 
 *     int val; 
 *     TreeNode left; 
 *     TreeNode right; 
 *     TreeNode() {} 
 *     TreeNode(int val) { this.val = val; } 
 *     TreeNode(int val, TreeNode left, TreeNode right) { 
 *         this.val = val; 
 *         this.left = left; 
 *         this.right = right; 
 *     } 
 * } 
 */
class Solution { 
    // Start with the lowest possible value so that even a tree 
    // made entirely of negative numbers can update this properly.
    int maxSum = Integer.MIN_VALUE; 

    public int maxPathSum(TreeNode root) { 
        // This problem behaves like finding the diameter/height of a tree:
        // We look at each node and find the best paths below it.
        maxGain(root); 
        return maxSum; 
    } 

    // This helper does two things at once:
    // 1. It finds the absolute best "straight" path it can give to its parent.
    // 2. It checks if using this node as the "turnaround point" creates a new global maximum.
    private int maxGain(TreeNode root){ 
        // Base case: If we run out of tree, it contributes nothing to the sum.
        if(root == null){ 
            return 0; 
        } 

        // CRITICAL STEP: Ask the left and right children for their best single-path sums.
        // If a child returns a negative total, it will only hurt our sum. 
        // We use Math.max(0, ...) to completely chop off any branches that drag us down.
        int leftPath = Math.max(0, maxGain(root.left)); 
        int rightPath = Math.max(0, maxGain(root.right)); 

        // What if this current node is the highest point (the arch) of the absolute best path?
        // We simulate "hooking" the left branch, the current node, and the right branch together.
        // If this combined horseshoe path beats our record, update the global maximum.
        maxSum = Math.max(maxSum, root.val + leftPath + rightPath); 

        // What do we return to the parent node waiting above us?
        // A path cannot split in two directions and go up! It must be a continuous line.
        // So, we choose only the single best side (left or right), add our own value, 
        // and send that single straight line up to the parent.
        return root.val + Math.max(leftPath, rightPath); 
    } 
}
