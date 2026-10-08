class Solution {
    public boolean validTree(int n, int[][] edges) {
        // this question's explanation is gieven in notes check it
        // very veyr impo edge case 
        if(edges.length != n - 1) return false;

        ArrayList<Integer>[] adjList = new ArrayList[n];
        for(int i=0; i < n;i++){
            adjList[i] = new ArrayList<>();
        }

        for(int[] edge : edges){
            int u = edge[0];  int v = edge[1];
            // since undirected
            adjList[u].add(v);
            adjList[v].add(u);
        }

        boolean[] visited = new boolean[n];

        // step 2: rule 1 check
        // parent of startuing node is -1
        if(hasCycleDfs(0 , -1 , adjList , visited)){
            return false;
        }

        // step 3: rule 2 check for isolated lands
        for(int i=0; i < n;i++){
            if(!visited[i]){
                return false;
            }
        }

        // if all rules passed
        return true;
    }

    // veyr imp funciton
    public boolean hasCycleDfs(int curr , int parent ,ArrayList<Integer>[] adj , boolean[] visited){
        // mark current node
        visited[curr] = true;
        // veyr imp part
        for(int neighbour : adj[curr]){
            // if not visited -> go there 
            if(!visited[neighbour]){
                if(hasCycleDfs(neighbour , curr , adj , visited)){
                    return true;  //if cycle exists
                }
            }

            // if its visited and if this neighbour node our parent then cycle
            else if(neighbour != parent){
                return true;
            }
        }

        // retunr false if no cycle exists
        return false;
    }
}
