class Solution {
    public int minCostConnectPoints(int[][] points) {
        //this question is the replica of connection hoiuse with min cost
        // we can use any algo between 2 of them prims and kruskals
        // but prims also is better in here
        int n = points.length;
        boolean[] visited = new boolean[n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1],b[1]));
        // this array will have {node , cost_soFar}
        pq.offer(new int[]{0,0});

        int mstCost =0;
        int pointsConnected =0;

        // our main algo starts here
        while(!pq.isEmpty() && pointsConnected < n){
            int[] curr = pq.poll();
            int node = curr[0];
            int cost = curr[1];

            // vvimp step in this algo
            if(visited[node]) continue;

            visited[node] = true;
            mstCost += cost;

            // now find 
            int x1 = points[node][0]; 
            int y1 = points[node][1];

            // now comes the main part
            // since they havent gievn any edge list , we assume that each node connects to evry node
            for(int v =0; v < n; v++){
                if(!visited[v]){
                    // if not visisted then only
                    int x2 = points[v][0];
                    int y2 = points[v][1];

                    int dist = Math.abs(x1 -x2) + Math.abs(y1 - y2);
                    // now put inside the queue
                    pq.offer(new int[]{v , dist});
                }
            } 
        }
        return mstCost;
    }
}