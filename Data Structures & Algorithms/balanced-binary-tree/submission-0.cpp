#include <bits/stdc++.h>
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
    bool isBalanced(TreeNode* root) {
         // If our bottom-up helper function returns -1, the tree is unbalanced
        return dfsHeight(root) != -1;
    }

    int dfsHeight(TreeNode* root){

        // sicne its a recurive ficntion very imporat base case
        if(root == nullptr){
            return 0;
        }

        int leftHeight = dfsHeight(root->left);
        // after calculating the height of left side subtree if its equal to -1 return it
        if(leftHeight == -1){
            return -1; // Abort: Left side is already unbalanced
        }
        // now after left side -> now go towards right side
        int rightHeight = dfsHeight(root->right);
        if(rightHeight == -1){
            return -1;  // Abort: right side is already unbalanced
        }

        // 4. Evaluate current parent node using the heights passed up

        // after cacluting both : very imporant step formula -> if differnence between those 2 are > 1 then they r not balanced tree -> immediatl return -1
        if(abs(leftHeight - rightHeight) > 1){
            return -1;   // Abort: Current node breaks the balance condition
        }

        // now calculate its max height ->  // 5. Node-Counting Induction: Return actual height if perfectly balanced
        return max(leftHeight,rightHeight) + 1; // here we r following node based height counting
    }
};