/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        // very vey imporat question
        // we have to return the deepest sharing(parent node of both p's and q's)
        // so there 3 rules applied here
        // rule 1: also a base case of the recurision call : -> so if a current node is null or the node is either p or q , then simply return the node
        if(root == nullptr || root == p || root == q){
            return root;
        }

        // now we continue our Post-order bottom up traversal 
        // first we go to the left side most then rightside
        TreeNode *leftNode = lowestCommonAncestor(root->left , p , q);
        TreeNode *rightNode = lowestCommonAncestor(root->right , p , q);

        // the most imporant parts
        // rule 2: if any one amoung them the childeren has the value either p and q which is -> if one node is null and other isnt , then return the not null's node
        // this also applicable when both the childeren are null so we can direcly return null
        if(leftNode == nullptr){
            return rightNode;
        }
        // then if not if left is not null but right has a value then u return it
        if(rightNode == nullptr){
            return leftNode;
        }

        // rule 3:  if both the childer have thier value(whcih will be eventualy p and q) then this respective node will be the LCA
        return root;
    }
};