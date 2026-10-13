class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int max = 0;

        for(int i=0; i < n;i++){
            for(int j =0; j < m; j++){
                if(grid[i][j] == 1){
                    int area = dfs_area(i , j , n , m , grid);
                    max = Math.max(max , area);
                }
            }
        }
        return max;
    }

    // our dfs
    public int dfs_area(int r ,int c ,int n ,int m ,int[][] grid){
        // base case:
        if(r < 0 || r >= n || c < 0 || c >= m || grid[r][c] == 0){
            return 0;
        }

        // sink
        grid[r][c] = 0;
        int area = 1;
        area += dfs_area(r - 1 , c , n , m , grid); // bottom
        area += dfs_area(r + 1 , c , n , m , grid); // top
        area += dfs_area(r , c - 1 , n , m , grid); // left
        area += dfs_area(r , c + 1 , n , m , grid); // right

        return area;
    }
}