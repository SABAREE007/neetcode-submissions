class Solution {
    public void islandsAndTreasure(int[][] grid) {
        // this quetion is leetcode 286 walls and gate
        // similar to rotten oranges and 01 matrix

        // we follow multi source bfs
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();

        for(int i =0; i <n; i++){
            for(int j =0; j < m; j++){
                if(grid[i][j] == 0){
                    q.add(new int[]{i , j});
                }
            }
        }
        int[] dRow = { -1 , 1 , 0 , 0}; 
        int[] dCol = { 0 , 0 , -1 , 1};

        // our multi souce bfs starts
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            // since its a grid traversal , a cell can travel 4 ways
            for(int i=0; i < 4;i++){
                int newRow = r + dRow[i];
                int newCol = c + dCol[i];

                // our very evry imporant part starts
                if(newRow >= 0 && newRow < n){
                    if(newCol >= 0 && newCol < m && 
                    grid[newRow][newCol] == Integer.MAX_VALUE){
                        // now update our column and pass this cell into the queue
                        grid[newRow][newCol] = grid[r][c] + 1;
                        q.add(new int[]{newRow , newCol});
                    }
                }
            }
        } 
    }
}
