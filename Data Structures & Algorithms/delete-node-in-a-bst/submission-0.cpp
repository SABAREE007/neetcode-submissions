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
    TreeNode* deleteNode(TreeNode* root, int key) {
        // Base Case: If the tree is completely empty, nothing to delete.
        if (root == nullptr) {
            return nullptr;
        }

        // Edge Case: If the target node to delete is the root itself, 
        // we directly invoke the helper function to restructure the tree.
        if (root->val == key) {
            return disconnectAndRestructure(root);
        }

        // Main Navigation: Perform an iterative search to locate the node to be deleted.
        // We use a trailing check to stop at the parent node so we can rewire its pointers.
        TreeNode *current = root;
        while (current != nullptr) {
            if (key < current->val) {
                // If the target key belongs in the left subtree:
                // Check if the immediate left child is the node to delete.
                if (current->left != nullptr && current->left->val == key) {
                    current->left = disconnectAndRestructure(current->left);
                    break;
                } else {
                    current = current->left;
                }
            } else {
                // If the target key belongs in the right subtree:
                // Check if the immediate right child is the node to delete.
                if (current->right != nullptr && current->right->val == key) {
                    current->right = disconnectAndRestructure(current->right);
                    break;
                } else {
                    current = current->right;
                }
            }
        }

        return root;
    }

private:
    // Helper function that disconnects the target node and structurally merges its subtrees.
    TreeNode* disconnectAndRestructure(TreeNode* targetNode) {
        // Case 1 & 2: If either child is missing, safely float up the available child.
        // (If both are null, this naturally returns nullptr, perfectly handling leaf nodes).
        if (targetNode->left == nullptr) {
            return targetNode->right;
        }
        if (targetNode->right == nullptr) {
            return targetNode->left;
        }

        // Case 3: The node has two children.
        // We preserve the right child and find the largest element in the left subtree.
        TreeNode *isolatedRightChild = targetNode->right;
        TreeNode *rightmostInLeftSubtree = findLargestNode(targetNode->left); 

        // Core Rewire: Since all elements in the target's original right subtree 
        // are strictly greater than the left subtree, they safely attach to the right 
        // side of the left subtree's largest (rightmost) element.
        rightmostInLeftSubtree->right = isolatedRightChild;
        
        // Return the root of this newly rewired, consolidated subtree.
        return targetNode->left;
    }

    // Traverses down to the absolute rightmost leaf to find the largest value in a subtree.
    TreeNode* findLargestNode(TreeNode *subTreeRoot) {
        // If there is no right child, the current root is already the largest element.
        if (subTreeRoot->right == nullptr) {
            return subTreeRoot;
        }

        TreeNode *runner = subTreeRoot;
        while (runner->right != nullptr) {
            runner = runner->right;
        }

        return runner;
    }
};
