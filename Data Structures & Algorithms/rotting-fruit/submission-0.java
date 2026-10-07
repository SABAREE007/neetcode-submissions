class Solution {
    public int orangesRotting(int[][] grid) {
        // this is an BFS based question not dfs , explaned in notes
        // we dont use a normal queue for this , we introduce a new term which is 
        // multi souce queue -> since we have to add every rotten oranges first itself in the queue , then start the process
        int n = grid.length;
        int m = grid[0].length;
        int fresh_fruit_counts = 0;

        // our multi souce queue -> since inout is in matrix , we need coordinates so
        Queue<int[]> q = new LinkedList<>();
        for(int i =0; i < n;i++){
            for(int j=0; j < m;j++){
                if(grid[i][j] == 2){
                    q.add(new int[]{i , j});
                }
                else if(grid[i][j] == 1){
                    fresh_fruit_counts++;
                }
            }
        }

        // if there no fresh fruits then
        if(fresh_fruit_counts == 0) return 0;
        int minutes = 0; // final minutes to rotten all oranges

        // very ervy veyr important helper afrrays to get coordinates of a cell's adjacent cells(top , bottom , left , right)
        int[] drow = {-1 , 1 , 0 , 0};
        int[] dcol = {0 , 0 , -1 , 1};

        // we start our bfs 
        while(!q.isEmpty() && fresh_fruit_counts > 0){
            int size = q.size();
            minutes++;  // for first enitre layer of rooten oranges will start to rot simulatenouly together so

            // for this layer , we rot everything
            for(int i =0; i < size; i++){
                int[] curr = q.poll();  // first elenemnt of the layer
                int r = curr[0];
                int c = curr[1];

                // now very evry important part starts
                // for the current rotten orange , we will rot every fresh fruit adjacent to us
                for(int j =0; j < 4;j++){
                    int curr_row = r + drow[j];
                    int curr_col = c + dcol[j];

                    // now we start rotting
                    // check out of bounds ad whether adjacent is fresh fruit or not
                    if(curr_row >= 0 && curr_row < n){
                        if(curr_col >=0 && curr_col < m && grid[curr_row][curr_col] == 1){
                            // now rot this orange and reduce the fresh fruit count
                            grid[curr_row][curr_col] = 2;
                            fresh_fruit_counts--;
                            // now add it into queue for the processing for next layer of rotting oranges
                            q.add(new int[]{curr_row , curr_col});
                        }
                    }
                }
            }
        }
        // now comes imp part if we have any remianig fresh fruit left then we dint fully rot so
        // and if no more fresh fruit avaible thenreturn how many mins took it to rot the basket fully
        if(fresh_fruit_counts == 0){
            return minutes;
        }
        else{
            return -1;
        }
    }
}