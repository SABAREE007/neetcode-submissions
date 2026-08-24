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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // we should print the numbers level by level 
        // here output is like seperate lists , each one is a level
        List<List<Integer>> ans = new ArrayList<>(); // our final list
        // edge case :
        if(root == null){
            return ans;
        }
        // we put on the nodes level by level onto the queue
        Queue<TreeNode> q = new ArrayDeque<>();
        // now we add out first root node -> which is in the level 1
        q.offer(root);

        // now is the important part
        while(!q.isEmpty()){
            // first calculate how many number of nodes are in this level
            int levelSize = q.size();
            //now get the element from the queue and add into a list
            List<Integer> currlevel = new ArrayList<>();

            // now we take elements out of this level , andd add the nodes of next level(which means the childerens of that node represtents a new level)
            for(int i = 0; i < levelSize; i++){
                TreeNode currNode = q.poll();
                // add the value to the current level list
                currlevel.add(currNode.val);
                //now add the children of the current node if it has to the new level 
                if(currNode.left != null){
                    q.offer(currNode.left);
                }
                
                if(currNode.right != null){
                    q.offer(currNode.right);
                }
            }
            // now add the list of elemnts of the current level to the final ans
            ans.add(currlevel);
        }
        return ans;
    }
}