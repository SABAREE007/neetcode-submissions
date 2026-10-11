class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] visited = new int[n];  // same 3 state thing
        Stack<Integer> st = new Stack<>();

        // check for all nodes since there might be possibilties of disconnected grapghs
         // Start DFS and also hascyle thing
        for(int i = 0; i < n; i++){
            if(visited[i] == 0){
                // If a cycle is detected during DFS, return an empty array immediately
                if (hasCycle(i, visited,adjList, st)) {
                    return new int[0];
                }
            }
        }

        // after completing , now our stack is filled with our values(bottom -> top)
        int[] result = new int[n];
        int index = 0;
        while(!st.isEmpty()){
            result[index] = st.pop();
            index++;
        }

        // loop checking
        if(index < n){
            return new int[0];
        }

        return result;
    }

    // imporant part
    public boolean hasCycle(int curr ,int[] visited ,ArrayList<Integer>[] adj, Stack<Integer> st){
        // important checks
        if(visited[curr] == 1){
            return true;  // cycle exists
        }

        if(visited[curr] == 2){
            return false;
        }

        // mark current node
        visited[curr] = 1;

        // visit all its neighbours
        for(int neighbour : adj[curr]){
            if(hasCycle(neighbour , visited , adj, st)){
                return true;
            }
        }

        // if a node has no neigbours or if all the neigbours of current node is visited then we put into the stack
        st.push(curr);

        // after procesesing the nodes and if u dont hve cycle then this curr node is fully processed
        visited[curr] = 2;
        return false;

    }
}
