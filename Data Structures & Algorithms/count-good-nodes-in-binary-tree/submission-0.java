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
    public int goodNodes(TreeNode root) { 

        // We use DFS to traverse the tree
        // root = current node
        // root = since we r at the root , we know this as max because we havnt visited any nodes down
        // true = root is considered good
        return dfs_helper(root, root, true); 
    } 

    public int dfs_helper(TreeNode currentNode, 
                          TreeNode maxNodeOnPath, // Fixed: Tracks the largest node  
                          boolean isParentGood) { 

        // No node
        if (currentNode == null) { 
            return 0;  
        } 

        // Check whether current node is good
        boolean isCurrentGood; 

        // simple logic only , a node is considered good , when it is greather than the max node so far found along the path
        if(currentNode.val >= maxNodeOnPath.val){
            isCurrentGood = true;
        }
        else{
            isCurrentGood = false;
        }

        // Count current node if it is good
        int count = 0;

        if (isCurrentGood) { 
            count = 1; 
        }

        // now we have to find our new next mad node if we have found in here so that , we can pass it doen to find any other good nodes
        TreeNode nextMaxNode = (currentNode.val >= maxNodeOnPath.val) ? currentNode : maxNodeOnPath;  

        // Check left and right subtrees
        count += dfs_helper(
            currentNode.left, 
            nextMaxNode, 
            isCurrentGood
        );

        count += dfs_helper(
            currentNode.right, 
            nextMaxNode, 
            isCurrentGood
        );

        return count; 
    } 
}