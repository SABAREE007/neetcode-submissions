/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    int maxDepth(TreeNode* root) {
        // code is done using recurision
        // first for every node if the node is null , then return 0
        if(root == nullptr){
            return 0;
        }

        // now first calculate the depth for left subtree
        int leftSide = maxDepth(root->left);
        // then for right subtree
        int rightSide = maxDepth(root->right);
        // after that this is the main formula:
        return max(leftSide,rightSide) + 1;
        // The reason we add +1 after finding the maximum depth of the left and right subtrees is to account for the current node itself

    }
};