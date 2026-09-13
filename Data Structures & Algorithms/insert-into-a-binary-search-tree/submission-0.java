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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // since this is inserti a node int bst , there are several ways we can but in oa it is easy to fit the node in the leafs
        if(root == null){
            return new TreeNode(val);
        }

        TreeNode curr = root;
        while(true){
            if(val < curr.val){
                // imporant condtion
                if(curr.left == null){
                    curr.left = new TreeNode(val);
                    break;   // this is bevry imporant to add as we should must exit the loop or else this will go on because we dint add any condition on the loop so
                }
                // if left is not null then go there
                curr = curr.left;
            }
            else{
                // imporant condtion
                if(curr.right == null){
                    curr.right = new TreeNode(val);
                    break;   // this is bevry imporant to add as we should must exit the loop or else this will go on because we dint add any condition on the loop so
                }
                // if right is not null then go there
                curr = curr.right;
            }
        }
        return root;
    }
}