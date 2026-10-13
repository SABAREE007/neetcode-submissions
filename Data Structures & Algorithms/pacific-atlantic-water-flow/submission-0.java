class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        // this question is one of the very imp qiuestions
        // detail notes in notes

        List<List<Integer>> result = new ArrayList<>();

        int n = heights.length;
        int m = heights[0].length;

        // step 1: imp
        boolean[][] pacificVisited = new boolean[n][m];
        boolean[][] atlanticVisited = new boolean[n][m];

        // step 2 and 3: vvimp
        // left and right rows
        for(int i=0; i < n;i++){
            // pacific left
            dfs_flood(heights , i , 0, Integer.MIN_VALUE, pacificVisited);
            // atlantic right
            dfs_flood(heights , i , m - 1, Integer.MIN_VALUE, atlanticVisited);
        }

        // top and bottom
        for(int j=0; j < m;j++){
            // pacific left
            dfs_flood(heights , 0 , j, Integer.MIN_VALUE, pacificVisited);
            // atlantic right
            dfs_flood(heights , n - 1 , j, Integer.MIN_VALUE, atlanticVisited);
        }

        // step 4: confirmation of cells that water flows to both pacific and atlantic
        for(int i=0; i <n;i++){
            for(int j=0; j < m;j++){
                if(pacificVisited[i][j] && atlanticVisited[i][j]){
                    result.add(Arrays.asList(i , j));
                }
            }
        }
        return result;
    }

    // our vvimp dfs
    public void dfs_flood(int[][] grid ,int r ,int c,int previousval, boolean[][] visited){
        int n = grid.length;
        int m = grid[0].length;

        // imp base cases :
        // out of bounds
        // aldready visited
        // new value not greather then previous val
        if(r < 0 || r >= n || c < 0 || c >= m ||
         visited[r][c] || grid[r][c] < previousval){
            return;
        }

        // mark
        visited[r][c] = true;

        // run dfs to adjacent
        // here the current grid value will acts as previous vals for adjacents
        dfs_flood(grid , r - 1 , c, grid[r][c], visited); // bottom
        dfs_flood(grid , r + 1 , c, grid[r][c], visited); // top
        dfs_flood(grid , r , c - 1, grid[r][c], visited); // left
        dfs_flood(grid , r , c + 1, grid[r][c], visited); // right
    }
}