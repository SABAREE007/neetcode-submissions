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
    public List<Integer> postorderTraversal(TreeNode root) {
        // its same as inorder and preorder but rules change here -> first left subtrree , then go to the righ side , then print the node
        
       List<Integer> ans = new ArrayList<>();
        // now call the hlper fucntion
        postOrder(root,ans);
        // after getting it
        return ans;
    }
    // helper function
    public void postOrder(TreeNode root , List<Integer> ans){
        //base important case of this recursive fucntion -> if the current node is null go back to its previous recursive called fucntion
        if(root == null){
            return;
        }
        // in postorder traversal -> first left subtree we go and print it
        postOrder(root.left,ans);
        // now go to its right side
        postOrder(root.right,ans);
        // then atlast thats Node value (ancestor)
        ans.add(root.val);
    }
}