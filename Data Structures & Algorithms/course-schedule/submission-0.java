class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // this is a topo sort question and i am using dfs algo appraoch
        // convert the edge list into adjacent list with an in-degree array
        int n = numCourses;
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for(int i=0; i < n; i++){
            adjList[i] = new ArrayList<>();
        }

        // built adj list and in degree count for each of the nodes
        for(int[] pair : prerequisites){
            int a = pair[0];
            int b = pair[1];

            // as per the given rule above b -> a
            adjList[b].add(a);
        }

        // for this we r going to use a 3 state algorithm
        int[] visited = new int[n];  // 0 - unvisited , 1 - visiting , 2 - visited

        // now explore all posibilities
        for(int i=0; i < n;i++){
            if(visited[i] == 0){
                // if node is unvisted then
                // check if it has cycle
                if(hasCycle(i , adjList , visited)){
                    // if it has cycle then
                    return false;
                }
            }
        }
        return true;
    }

    // veyr impormat part
    public boolean hasCycle(int curr ,ArrayList<Integer>[] adjList ,int[] visited){
        // very very imporat checks
        // if the cuurent node is visiting 
        if(visited[curr] == 1){
            return true;  // cycle exists
        }
        if(visited[curr] == 2){
            return false;  // fully processed so skip
        }

        // mark the current node as visiting
        visited[curr] = 1;

        // explore all its neighbours
        for(int neighbour : adjList[curr]){
            if(hasCycle(neighbour , adjList , visited)){
                return true;   // cycle exists
            }
        }

        // after exploring all posibitlies and no ccyle is exists then this current node is fully processed so
        visited[curr] = 2;
        return false;
    }
}
