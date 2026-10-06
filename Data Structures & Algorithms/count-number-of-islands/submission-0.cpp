class Solution {
public:
    int numIslands(vector<vector<char>>& grid) {
        // this is my first sum in graphs -> this is a graph question -> identicators -> 'connecting' , 'adjacent' 

       // but we use genrakl recursion fucntion which will acts as dfs to solve this
       int n = grid.size();
       int m = grid[0].size();
       int noOfIslands = 0;  // total no. of islands

       for(int i =0; i < n; i++){
            for(int j = 0; j < m;j++){
                // locate and find the island in our grid
                if(grid[i][j] == '1'){
                    // since we found an island
                    noOfIslands++;

                    // now we recursilvy neighbour land parts of the same island and sink it to find other islands
                    dfsGrid(grid , i , j , n , m);
                }
            }
       }
       return noOfIslands;
    }

    // very imp funcrion:
    void dfsGrid(vector<vector<char>> &grid ,int row ,int col ,int n ,int m){
        // veru very imp base case : if the current cell is reached out of grid boundaries or if i am standing at water , then go back
        if(row < 0 || row >= n || col < 0 || col >= m || grid[row][col] == '0' ){
            return;
        }

        // mark this island  or part of this island as visited by sinking it in water
        grid[row][col] = '0';

        // now recursivly check its neighbour parts of the same land if its there 
        dfsGrid(grid , row - 1 , col , n , m);  // top
        dfsGrid(grid , row + 1 , col , n , m);  // bottom
        dfsGrid(grid , row , col - 1 , n , m);  // left
        dfsGrid(grid , row , col + 1 , n , m);  // right
    }
};