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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // this is a hard question , also a very very very important one
        // we have to solve this using a resurive way using manly indexes 
        // the thing is , we r using prorder array to choose our current node and inirder array to get ites left and right subtree parts
        // we r mainy doing with indexing so = check note for explanation
        // FIRST step : we get dtore the Inorder array in a map with indexes so we can easily get left and right parts
        Map<Integer , Integer> inOrderMap = new HashMap<>();
        for(int i = 0 ; i < inorder.length; i++){
            inOrderMap.put(inorder[i], i);
        }

        int n = preorder.length;
        int m = inorder.length;

        // now we r calling our recurisve helper function to make the recursove calls
        return splitAndBuild(preorder , 0 , n-1 , inorder , 0 , m-1 , inOrderMap);
    }

    // very very very important recurive hlper funciton
    public TreeNode splitAndBuild(int[] preorder , int preorderStart , int preorderEnd , 
    int[] inorder , int inorderStart , int inorderEnd , Map<Integer , Integer> inOrderMap ){

        // this is the most imporant base case of the recurive funtion and also used to attach null to the left and right parts of the leaf nodes
        // if the pointer croses the boundaries then
        if(preorderStart > preorderEnd || inorderStart > inorderEnd){
            return null;
        }

        // step 2: first of all create a root node of the currrent segment which can be get from preorder array
        TreeNode root = new TreeNode(preorder[preorderStart]);
        // important step 3: get the index postion from the inorder map so we can separet the left and right portio from this
        int inOrderIndex = inOrderMap.get(root.val);

        // now that we have got the index , so we need to find , how many elements to move left or right inorder ti get the correct boundaries for that there is a formula
        int numsLeft = inOrderIndex - inorderStart;

        // step 3 : very very very importat trciky recurive calls
        // LEFT SUBTREE:
        // In inorder, everything before the current root belongs to the left subtree.
        // So its inorder range is: inorderStart -> inOrderIndex - 1.
        //
        // The left subtree contains numsLeft nodes.
        // Since preorder is: ROOT -> LEFT -> RIGHT,
        // the root is at preorderStart, so the left subtree starts at
        // preorderStart + 1 and ends after numsLeft elements,
        // therefore its preorder range is: preorderStart + 1 -> preorderStart + numsLeft.
        root.left = splitAndBuild(preorder , preorderStart + 1 ,
         preorderStart + numsLeft, inorder , inorderStart , inOrderIndex - 1 , inOrderMap );

         // now the right subtree part : recurive way 
         // RIGHT SUBTREE:
        // In inorder, everything after the current root belongs to the right subtree.
        // So its inorder range is: inOrderIndex + 1 -> inorderEnd.
        //
        // In preorder, the root and all numsLeft nodes of the left subtree
        // have already been used.
        // Therefore the right subtree starts at preorderStart + numsLeft + 1
        // and continues until preorderEnd.
         root.right = splitAndBuild(preorder , preorderStart + numsLeft + 1 , preorderEnd , inorder , inOrderIndex + 1 , inorderEnd , inOrderMap);

        // finaly return the root
        return root;

    }
}