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

    // here we used the optimzed version -> morris trvaersal:
    // normal approiach is recurve solution


    public int kthSmallest(TreeNode root, int k) {
        TreeNode current = root;
        int count = 0;
        int result = -1;
        
        while (current != null) {
            // Case 1: If there is no left child, we process the current node
            if (current.left == null) {
                count++;
                if (count == k) {
                    result = current.val;
                    // Note: We don't break immediately because we need to clean up 
                    // any temporary pointers we created elsewhere in the tree!
                }
                current = current.right; // Move to the right child
            } 
            // Case 2: A left child exists
            else {
                // Find the inorder predecessor (rightmost node in the left subtree)
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                
                // Step A: If the temporary link doesn't exist, create it
                if (predecessor.right == null) {
                    predecessor.right = current; // Establish temporary link back up
                    current = current.left;      // Move down to the left child
                } 
                // Step B: The temporary link already exists, meaning we've returned from the left subtree
                else {
                    predecessor.right = null; // Clean up and break the temporary link
                    
                    count++;
                    if (count == k) {
                        result = current.val;
                    }
                    
                    current = current.right; // Move to the right child
                }
            }
        }
        
        return result;
    }
}

