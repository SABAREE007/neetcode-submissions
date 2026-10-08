class Solution {
    public int countComponents(int n, int[][] edges) {
        // this question is a easy question , we have to find how many connetced graphs which means how many seprate grpahs are there 
        // we have not given with a adjaceny list but a edge list , so weconver this edge list into an adjacney list , which makes our problem easy
        ArrayList<Integer>[] adjList = new ArrayList[n];
        // firts we initialize our adj list
        for(int i=0; i < n;i++){
            adjList[i] = new ArrayList<>();
        }

        // now we convert that edge list , into adj list since we have intialized the nodes aldready
        for(int []edge : edges){
            int u = edge[0];
            int v = edge[1];

            // since this is an undirected graph , 2 way mapping
            adjList[u].add(v);
            adjList[v].add(u);
        }

        // now we begin our actual logic
        boolean[] visited = new boolean[n];
        int graphcounts = 0;
        for(int i=0; i < n;i++){
            // if an only if the node we havent visited yet
            if(!visited[i]){
                // we increase the count
                graphcounts++;

                // we begin our dfs to find its full reacbility
                dfs(i , visited , adjList);
            }
        }

        // at last retutn the count
        return graphcounts;
    }

    // our very imp dfs
    public void dfs(int curr ,boolean[] visited ,ArrayList<Integer>[] adjList){
        // we mark this current node as visited
        visited[curr] = true;

        // now we explore all its connected nodes
        for(int neighbour : adjList[curr]){
            // we lauch our dfs to track its nodes only if this current node is not visted yet
            if(!visited[neighbour]){
                dfs(neighbour , visited , adjList);
            }
        }
    }
}
