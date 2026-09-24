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
    public int rob(TreeNode root) {
        // this is a very important hard tier oa question although it looks simple
        // its main logic are in notes please check
        // we will store mianly 2 values and find a max from one of them so , we start it and a helper function will privode it
        int[] masterchoices = fillBackPack(root);
        // this array will consist of only 2 values so we will return the max from one of them
        return Math.max(masterchoices[0] ,masterchoices[1]);
    }

    // now our very very imporant helper fucntion -> this is a recurive helper
    public int[] fillBackPack(TreeNode root){
        // since this is a recursion -> base case willl be 
        if(root == null){
            return new int[]{0,0};
        }

        // since we r following bottom to top appraoch -> we will use post order aprach so drill it to down
        int[] leftBackPack = fillBackPack(root.left);
        int[] rightBackPack = fillBackPack(root.right);

        // now step 2: after reading the downmost node we take 2 values from these arrays
        // these values rpresnts children of a node so
        int leftRobbed = leftBackPack[0];
        int leftSkipped = leftBackPack[1];

        int rightRobbed = rightBackPack[0];
        int rightSkipped = rightBackPack[1];

        // step 3 : very impormt imp part of this function
        // the main rule of this problem is that at each node we can have to choices
        // one is to robb this node and we should compulsoryly skip our child and go to its child
        // another is that if we r skiiping this node , we can have 2 possibilities ->either we can robb our childer node or we can skip our children node depending upon its value

        // choice a : 
        int robThisHouse = root.val + leftSkipped + rightSkipped;
        // choice B :
        int skipThisHouse = Math.max(leftSkipped , leftRobbed) + Math.max(rightSkipped , rightRobbed);

        // now we have calculated our choics , pass this choices to our parent nodes so they can use this
        return new int[]{robThisHouse , skipThisHouse};
          
    }
}