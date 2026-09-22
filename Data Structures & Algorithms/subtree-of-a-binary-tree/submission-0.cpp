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
    bool isSubtree(TreeNode* root, TreeNode* subRoot) {
        // this question using the logic of same tree question leetcode 100
        // first find the matchinf root , thn compare both the tree using the sametree function
        if(root == nullptr){
            return root;
        }

        //very very imporant check by using a hlper function -> used for comparing if both roots matches 
        if(isSameTree(root, subRoot)){
            return true;
        }

        // if both of them does not match then go to subtree and find
        // important we r using (OR) opetration so that either true or false will return
        return isSubtree(root->left , subRoot) || isSubtree(root->right , subRoot);
    }

    // our important hlper fucntion -> logic of same tree
    bool isSameTree(TreeNode *p , TreeNode *q){
        // first check
        if(p == nullptr && q == nullptr){
            return true;
        }
        // if either one is null and other is not , then it is not the same tree (comparison failed for this tree)
        if(p == nullptr || q == nullptr){
            return false;
        }

        // now recursion starts very important as u c we also need to compare values and check thier subtree
        return (p->val == q->val) && isSameTree(p->left, q->left) && isSameTree(p->right , q->right);
    }
};