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
    public List<Integer> inorderTraversal(TreeNode root) {
        // same as pre-order traversal in here -> first go to left subtree , then Node , then go the right subtree
        List<Integer> ans = new ArrayList<>();
        // now call the hlper fucntion
        inOrder(root,ans);
        // after getting it
        return ans;
    }
    // helper function
    public void inOrder(TreeNode root , List<Integer> ans){
        //base important case of this recursive fucntion -> if the current node is null go back to its previous recursive called fucntion
        if(root == null){
            return;
        }
        // in inorder traversal -> first left subtree we go and print it
        inOrder(root.left,ans);
        // then thats Node value (ancestor)
        ans.add(root.val);
        // now go to its right side
        inOrder(root.right,ans);
    }
}