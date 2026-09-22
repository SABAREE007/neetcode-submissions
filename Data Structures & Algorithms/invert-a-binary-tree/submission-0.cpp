class Solution {
public:
    TreeNode* invertTree(TreeNode* root) {
        // Base Case: If the node is empty (null), there is nothing to invert.
        if (root == nullptr) {
            return nullptr;
        }

        // 1. Drill down to the bottom of the Left and Right subtrees first
        TreeNode* invertedLeft = invertTree(root->left);
        TreeNode* invertedRight = invertTree(root->right);

        // 2. Your exact logic: Swap the left and right children at this parent node
        root->left = invertedRight;
        root->right = invertedLeft;

        // 3. Return the fully inverted node back up the call stack
        return root;
    }
};
