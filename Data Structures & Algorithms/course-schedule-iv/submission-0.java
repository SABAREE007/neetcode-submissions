class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        // this question is a hard question and irritating question
        // build adj list
        int n = numCourses;
        ArrayList<Integer>[] adjList = new ArrayList[n];
        for(int i=0; i < n;i++){
            adjList[i] = new ArrayList<>();
        }

        for(int pair[] : prerequisites){
            int u = pair[0]; int v = pair[1];
            adjList[u].add(v);
        }

        // very evry imporant core part starts
        // we built reachability matrix which is iused for both direct and indirect connection checking
        boolean[][] isPre = new boolean[n][n];

        // to fill this matrix -> we run a dfs for every single node , for evry single node we get its direct and indirect prerequisite
        for(int i=0; i<n;i++){
            boolean[] visited = new boolean[n];
            dfs_helper(i , i , visited , adjList, isPre);
            // i , i because i is our starnode for evry node and and since this will be a new thign oru current will be also i
        }

        // step 3: the main part : of this question
        // after filling the reachbulity for the nodes its dircet and indiect then according to the reachbility matrix we compare wheather the given queries are matching to the prerequisites
        List<Boolean> result = new ArrayList<>();
        for(int[] query : queries){
            int u = query[0];  int v = query[1];
            result.add(isPre[u][v]);  // we check our reachibity matrix
        }
        return result;
    }

    // out very evry main imporant part to find our direct and indirect prerequiste for each nodes
    public void dfs_helper(int startNode ,int curr ,boolean[] visited ,
    ArrayList<Integer>[] adj,boolean[][] isPre){
        // first mark the current node
        visited[curr] = true;

        // now check for all neigbours 
        for(int neighbour : adj[curr]){
            // if not viisted then and only we will fill for the direct thing
            if(!visited[neighbour]){
                // since not visited , for aall curr , its all neighbours will be a prequeites for the current node so
                isPre[startNode][neighbour] = true;

            // now veyr veyr veyr imporant part : to get indirect 
            // as we know if a ->b and if b-> c , then a -> c , a is also a indrect prerequitse for c
            // so we call our helper again to find all indrect things with same start node
            dfs_helper(startNode , neighbour , visited , adj, isPre);
            }
        }
    }
}