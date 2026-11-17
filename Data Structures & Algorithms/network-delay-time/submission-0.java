class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // this question uses djikstras algo since we need to start from the node k and find all the min time to reach all nodes 
        // we can also use floydd warshall bit djkstra is better in here more fast
        List<List<int[]>> adjList = new ArrayList<>();
        for(int i=0; i <= n;i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] t : times){
            int src = t[0]; int tar = t[1]; int timeTaken = t[2];
            adjList.get(src).add(new int[]{ tar , timeTaken});
        }

        // now our mian djikstra stsrts  
        int[] minTime = new int[ n + 1];
        Arrays.fill(minTime , Integer.MAX_VALUE);
        // since we have to start at node k so 
        minTime[k] = 0;

        // our main prioriry queue 
        // it stores array which has {curr node , timeTaken_sofar}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1] ,
        b[1]));
        pq.offer(new int[]{k , 0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int curr_node = curr[0];
            int curr_time = curr[1];

            // veyr evry imp edge case:
            if(curr_time > minTime[curr_node]) continue;

            // now our imp relaxation
            for(int[] neighbour : adjList.get(curr_node)){
                int neighbour_node = neighbour[0];
                int neighbour_time = neighbour[1];
                int newTime = curr_time + neighbour_time;  // time taken from starting node to reach this neighbour node

                // oru main relaxation step
                if(newTime < minTime[neighbour_node]){
                    minTime[neighbour_node] = newTime;
                    pq.offer(new int[]{neighbour_node , newTime});
                }
            }
        }
        // after finding all the min time to reach all n nodes starting from k 
        // our main goal is to find the minmost time to rech all nodes so
        int minMostTime = 0;
        for(int i=1; i<=n;i++){
            if(minTime[i] == Integer.MAX_VALUE){
                return -1;
            }

            // this is the main imporant step:
            // we do this because ->
            // Signals travel to all nodes in parallel (at the same time), not one by one.
            // So the whole network finishes only when the LAST (slowest) node receives the signal.
            // That is why we take the maximum of all shortest times!

            minMostTime = Math.max(minMostTime , minTime[i]);
        }

        return minMostTime;  
    }
}