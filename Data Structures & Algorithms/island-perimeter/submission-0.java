class Solution {
    public int islandPerimeter(int[][] grid) {
        // this is a dfs 
        
        int n = grid.length;
        int m = grid[0].length;

        for(int i=0; i < n;i++){
            for(int j=0; j < m; j++){
                if(grid[i][j] == 1){
                    // if its land perform the dfs
                    return dfs_grid(i , j , n , m , grid );
                }
            }
        }

        return 0;
    }

    // our dfs
    public int dfs_grid(int r , int c ,int n, int m, int[][] grid ){
        // important base cases:
        if(r < 0 || r>= n || c < 0 || c >= m || grid[r][c] == 0){
            return 1;
        }
        // another base case , if we hit the island which is aldready visited then we ignore
        if(grid[r][c] == 2){
            // in here 2 will be acted as visited
            return 0;
        }

        // mark the current island as visited
        grid[r][c] = 2;

        // now explore all the possble cells
        int peri = 0;
        peri += dfs_grid(r - 1 , c , n , m,  grid ); // bottom;
        peri += dfs_grid(r + 1 , c , n , m , grid ); // top;
        peri += dfs_grid(r , c - 1 , n , m , grid ); // left;
        peri += dfs_grid(r , c + 1 , n , m , grid ); // right;

        // return this perimeter value to above
        return peri;
    }
}