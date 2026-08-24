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
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> ans = new ArrayList<>();

        // we cal a helper fucntion where it does this thing
        preOrder(root,ans);

        return ans;


    }

    // helper funciton (IMP)
    public void preOrder(TreeNode root, List<Integer> ans){

        // if the current node is a function(recursive function ) is null then go back to its previous fucntion where this originated
        if(root == null){
            return;
        }

        // according to pre order traversal -> first print the node -> then left subtree -> then right subtree
        ans.add(root.val);
        // now go to the left subtree and so on (if that node has children -> then to its left subtree ans so on...)
        preOrder(root.left,ans);
        // after finishing the left paart now the right part starts
        preOrder(root.right,ans);

    }
}