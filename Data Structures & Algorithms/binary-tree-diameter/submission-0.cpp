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
    int diameterOfBinaryTree(TreeNode* root) {
        // this is a very good and tricky question -> solve it by bottom up approach(optimal one)
        // since it is c++ we can use pass by reference so we can use normal int
        int diameter = 0;
        // our recurive bottom up helper fucntion;
        calculateHeight(root,diameter);  // this funciton will return height but we store diamter by class so we need that only
        // return the diamater
        return diameter;
    }
    // recursice helper funciton
    int calculateHeight(TreeNode* root , int &diameter){
        // our importabt base case :
        if(root == nullptr){
            return 0;
        }

        // 2. Ask children for their maximum heights (Node-Counting method)

        // now calcuate the leftheight (goes onto for every node)
        int leftHeight = calculateHeight(root->left,diameter);
        // now calculate the rightheight (goes onto for every node)
        int rightHeight = calculateHeight(root->right,diameter);


         // 3. Update the global max diameter at the CURRENT node
        // The number of edges passing through this node is exactly leftHeight + rightHeight
        // now very important step -> to get max diameter
        diameter = max(leftHeight + rightHeight , diameter);

        // step 4: return the height value to the parent 
        return 1 + max(leftHeight,rightHeight); // + 1 indicates we r using node based
        

    }
};