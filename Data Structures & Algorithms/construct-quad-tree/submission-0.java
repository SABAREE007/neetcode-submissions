/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    
    public Node() {
        this.val = false;
        this.isLeaf = false;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = null;
        this.topRight = null;
        this.bottomLeft = null;
        this.bottomRight = null;
    }
    
    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}
*/

class Solution {
    public Node construct(int[][] grid) {
       // this is a important tree question but asked in matrix format
       // we have to construct tree based on grids given -> simple logic only
       // when a quadrant or entire spefic martirx has same vslaue , all value is same then that matrix will be a leaf node
       // if that matrix or quadrant have mixed value , then that matrix will be a non lef node and it will have child nodes so next art we have to process those child nodes
       if(grid.length == 0){
            return null;
       }
       // if thats not null then we call our imporant recursive fucntion to constuct the tree
       return helper_constructor(grid , 0 , 0 , grid.length);
       // here we pass the grid matrix , current row anc colum standing point , and the size of the matrix
    }

    // our important helper functiom
    public Node helper_constructor(int[][] grid , int row , int col , int size){
        // now in order to check the value iside the grid , we can have atmost only 2 values , either its 1 or 0 so, if we took a example cell from the grid , that number should be presented in all the grids (which indictes thats a leaf) , if not -> then its a non leaf node
        int value = grid[row][col];
        boolean isLeaf = true;  // this is a helper varibale to tell wheather our matrix isa eaf or not
        // now we ietarte thoguh each cell of the given matrix so identify it
        for(int i =row; i < row + size; i++){
            for(int j = col; j < col + size; j++){
                // row + size and col + size defines the actual boundary of that particluar matrix
                // now we checl for every value
                if(grid[i][j] != value){
                    // vakues r diffent in this matrix so this is not a leaf node we need futrther checking
                    isLeaf = false;
                    break; // moment u find changes break the loop
                }
            }
        }

        // now for the creation of the tree
        // if that node is a leaf then
        if(isLeaf){
            // if this is a leaf node then we call its specific constructor for leaf node creation
            return new Node(value == 1, true);
            // we put a equlaity opeartor chdcking value is 1 0r 0 , it can be any of them dependim on the cell , so true means val is 1 , false means val is 0
        }

        // step 2 : ver y imporamt part
        // since this current node is node a leaf node then this node will have children , so we need to preocess those childen( 4 quadrants)
        // to solit them into 4 quadrants we hava forumala whic is down as follows


        int half = size / 2;
        // half denotes the boundary of those sp;iited quadrants
        Node topLeft = helper_constructor(grid , row , col , half);
        Node topRight = helper_constructor(grid , row , col + half , half);  
        Node bottomLeft = helper_constructor(grid , row + half, col , half);  
        Node bottomRight = helper_constructor(grid , row + half, col + half , half);

        // now we as found found those childeren so , now we have to return the constructed final node with these chidtren attached to the, -> since this isa recurisve fucion , this will also be used if tree is a messy one
        // we use a spefic constructor for this
        return new Node(true , false , topLeft , topRight , bottomLeft , bottomRight);
        // first boolean value can represrn any of them that parent can be any of them 1 or 0 , it acts as a place holder  
    }
}