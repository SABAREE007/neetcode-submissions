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
    public List<Integer> rightSideView(TreeNode root) {
        // imporatant question
        List<Integer> ans = new ArrayList<>();
        // calling the helpfer recursive fucntion
        reversePreOrder(root , 0 , ans);
        // here in the funciton call , the second element presents the current level of the tree -> it is ued in the imporatn checkig function
        return ans;
    }
    public void reversePreOrder(TreeNode root , int level , List<Integer> ans){
        // very important  base function - since we r following prorder so
        if(root == null){
            return; // go to the parent
        }

        // very important checking funciton ->
        // // CRUCIAL RULE: If this is the first node we encounter at this level depth,
        // it is guaranteed to be the rightmost visible node of this horizontal layer.
        if(level == ans.size()){
            ans.add(root.val);
        }

        // since we r mainly finding the right side'th value of the level so first
        // we r checking the right side ones , then the left side 
        reversePreOrder(root.right , level + 1 , ans);
        // then left side
        reversePreOrder(root.left , level + 1 , ans);
    }
}