class Solution {
    public int minimumEffortPath(int[][] heights) {
        // this qesiton usues djistra algo bevcause as u can c for 2 nodes we have distance which are condsidred as weights so

        // simce this is a matrix grid based question we dont havr to store edge and weights

        int n = heights.length;
        int m = heights[0].length;

        // givin in the question we need to find  a route's effort = max abosulte difference   
        // smce we can have many route this froms a question like find the min's max abosute difference which forms a pattern of BS on answers and we will using bfs because we need to reach from sorce to destination

        // but this approach is slower to what djisktras algorithm gives us since priority queue can eailty helps us to find the minumum max value

        // we neeed effort so 
        int[][] effort = new int[n][m];
        // initaly fill evry cell with max 
        for(int[] row : effort){
            Arrays.fill(row , Integer.MAX_VALUE);
        }
        // sice our starting point is (0,0) -> so its effort will be 0
        effort[0][0] = 0;

        // since this is djistra, priority queue is very important
        // we need min heap so -> Min-heap storing {row, col, currentMaxEffort}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a , b) -> Integer.compare(a[2] , b[2]));
        // first add our source:
        pq.offer(new int[]{ 0 , 0 , 0});

        // since it is amtrix and we can travel in 4 directions so
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // our main thing starts here
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int currEffort = curr[2];
            // thi curr effort always stores the  the highest difference encountered so far on the path from (0,0) to your current cell (r, c).
            // if u ask how is it done then it is used to djikstras and mainly priority queue's ability to do this 

             // if we already found a better way to (r, c), skip  entry
            if (currEffort > effort[r][c]) continue;
            
            // veyr evyr main step
            // first time popping destination guarantees minimum effort
            if (r == n - 1 && c == m - 1) {
                return currEffort;
            }

            // visiting
            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];

                // main case :
                if(nr >= 0 && nr < n && nc >= 0 && nc < m){
                    // first we find the absolute differnce
                    int weight = Math.abs(heights[r][c] - heights[nr][nc]);
                    // max weight -> veyr imp step since given in the quesion
                    int newEffort = Math.max(weight , currEffort);

                    // now evry evry imporant step -> relaxation
                    if(newEffort < effort[nr][nc]){
                        effort[nr][nc] = newEffort;
                        pq.offer(new int[]{nr , nc , newEffort});
                    }
                }
            }
        }
        return -1;
    }
}