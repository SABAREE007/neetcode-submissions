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
    bool isSameTree(TreeNode* p, TreeNode* q) {
        // for a two tree to be identical ->
        // Are both NULL?
        // Are only one NULL?
        // Do their values differ?

        if(p == nullptr && q == nullptr){
            return true;
        }

        // if one is null and other is not null -> then not identical
        if(p == nullptr || q == nullptr){
            return false;
        }


        // if both values arent same , then false;
        if(p->val != q->val){
            return false;
        }

        // now check for thier left and right subtree and so on for evry node
        return isSameTree(p->left,q->left) && isSameTree(p->right , q->right);
    }
};