/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // this question is a very easy question , we have to encode the tree into string , and decode the string back to orignal tree;
    // here we can use basic level order traversal

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if(root == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        // so in here we are going to do basic level order traversal to form as a string
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode curr = q.poll();

            // very impornant checking function -> why because we r also going to add null values to the queue so it is importnat that we should encode the respective chrater for null and we should stop the process after that and continue with other nodes so->
            if(curr == null){
                sb.append("n,");
                continue;  // we stop or skip this next part of the loop and proceed with next iteration
            }

            // in this step , we r going to put inside the string append with ',' for encoding purpose
            sb.append(curr.val).append(",");

            // now in this step , we will simpley add left and right parts to the queue
            q.offer(curr.left);
            q.offer(curr.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // so now we have encoded our tree , now we have to decoded to get back the orignal tree so first we will check initaly edge cases
        if(data == null || data.length() == 0){
            return null;
        }

        // now that we have encoded our values and we have sepearte our nodes by ',' so now we split teh string
        String[] values = data.split(",");
        // sice the starting node and first element is our node so we get it 
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int i = 1; // start at index 1 , since root 0 is done
        while(!q.isEmpty() && i < values.length  ){
            TreeNode curr = q.poll();

            // very impotrant steps 
            // sice we stores the strig with BFS directly we can do it and we also shoudl skip the encoded null part
            if(!values[i].equals("n")){
                // first exyract the node
                TreeNode leftChild = new TreeNode(Integer.parseInt(values[i]));
                curr.left = leftChild;
                q.offer(leftChild);
            }
            // after doing left increment the pointer i so it goes to next value
            i++;  

            if(!values[i].equals("n")){
                // first exyract the node
                TreeNode rightChild = new TreeNode(Integer.parseInt(values[i]));
                curr.right = rightChild;
                q.offer(rightChild);
            }
            // after doing left increment the pointer i so it goes to next value
            i++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));