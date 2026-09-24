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
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        // this uestion we can dpo simple post order dfs trvaral -> bottom to up apprach to find leaves of the target and delete it we can do it by
        // the thing is we cant use top to down apperach for this as it will violate a condtion so

        // since this is a recurive fucntion -> its base case
        if(root == null){
            return root;
        }

        // now we go deep most to fid the leaves nodes from the bottom
        root.left = removeLeafNodes(root.left , target);
        root.right = removeLeafNodes(root.right , target);

        // now we have come to the deppest node we check wheather this is a target node and wheather this node is leaf node or not
        if(root.left == null && root.right == null && root.val == target){
            // we simply return null why beacuse we have put variabls like root.left , right so return null will attch thier childer with null , completily removing that target leaf node by detaching 
            return null;
        }

        // return the final node
        return root;
    }
}