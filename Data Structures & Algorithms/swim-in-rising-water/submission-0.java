class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        // in this problme we r have src and we have to reach a destination
        // main problem explanarion in the note section please check it

        // we keep a visited array 
        boolean[][] visited = new boolean[n][n];

        // we r using djikstras algo
        // this pq will store {row , col , maxElevation_soFar}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2] ,b[2]));
        pq.offer(new int[]{0 , 0 , grid[0][0]});
        visited[0][0] = true;

        // to explore our 4 directions -> right , down , left , up
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // lets start our main thing
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int timeNeeded = curr[2]; // This represents 't' (the tallest block on this path) so far

            // this iste veyr evry imporant part 
            // Since pq always expands lowest paths first, the very first time we pop
            // the bottom-right destination, we are guaranteed to have the minimum time 't'.
            if(r == n-1 && c == n-1){
                return timeNeeded;
            }

            // now we explore all its neighburs
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];

                // this is the ver evry imporant part
                if(nr >= 0 && nr < n && nc >=0 && nc < n && !visited[nr][nc]){
                    // first mark visited
                    visited[nr][nc] = true;

                    // this comes th ver impornt part
                    // To swim onto (nr, nc), the water level 't' must cover BOTH:
                    // 1) All blocks we already crossed (timeNeeded)
                    // 2) This new neighbor block (grid[nr][nc])
                    int MaxTimeNeeded = Math.max(timeNeeded, grid[nr][nc]);

                    // now we insert this new cell into the pq
                    pq.offer(new int[]{nr , nc , MaxTimeNeeded});
                }
            }
        }
        return -1; // if no minimum is abl to be founded
    }
}