class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // this question can be solved be normal queue because djisktra wont work here because we have to track the count of steps also so 
        // we r going to use nromal quwue method

        // another method is there which is belmon ford algo for this question

        //first we built an adjaceny list
        List<List<int[]>> adjList = new ArrayList<>();
        for(int i =0; i < n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] f : flights){
            adjList.get(f[0]).add(new int[]{ f[1] , f[2]});
        }

        //now that we have initialized ,we need to track the mincost so
        int[] minCost = new int[n];
        Arrays.fill(minCost , Integer.MAX_VALUE);
        minCost[src] = 0;

        // main part starts here
        // in this queue we r going to store an array which is {steps , city , costsofar}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{ 0 , src , 0 });

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int total_steps_so_far = curr[0];
            int city = curr[1];
            int cost_so_far = curr[2];

            // imp case;
            if(total_steps_so_far > k) continue;

            for(int[] neighbour : adjList.get(city)){
                //  here one confusion happened -> adjlist city will give us a list of arrays which will have destination and thier cost
                // we use one by one of the array
                int nextCity = neighbour[0];
                int price = neighbour[1];

                // veyr evry veyr imporan step
                if(cost_so_far + price < minCost[nextCity]){
                    // if new updated price is lesser than older one ee update it
                    minCost[nextCity] = cost_so_far + price;
                    // imp step;
                    q.offer(new int[]{ total_steps_so_far + 1 , nextCity , cost_so_far + price});
                }
            }
        }

        if(minCost[dst] != Integer.MAX_VALUE){
            return minCost[dst];
        }
        else{
            return -1;
        }
    }
}