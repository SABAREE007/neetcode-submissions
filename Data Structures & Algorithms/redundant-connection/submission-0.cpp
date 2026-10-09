class Solution {
public:
    vector<int> parent;  
    vector<int> findRedundantConnection(vector<vector<int>>& edges) {
        // this is a great question to introduction in the DSU / union find / disjoint set data structures
       // explanation in notes
       int n = edges.size();

       parent.resize(n + 1); // since 1 based indexing
        
       // first we initialize 
       for(int i = 1; i <= n;i++){
            parent[i] = i;
       }

        for(vector<int> edge : edges){
            int u = edge[0];  int v = edge[1];

            // as per the rules of DSU
            if(find(u) == find(v)){
                return edge;
            }

            // if thats no use then we union them 
            union_DSU(u , v);
        }
    return {};
    }

    // these r the imporant function of DSU
private:
    int find(int node){
        if(parent[node] != node){
            // path optiimzation is applied
            parent[node] = find(parent[node]);
        }

        return parent[node];
    }

    void union_DSU(int nodeA , int nodeB){
        int rootA = find(nodeA);
        int rootB = find(nodeB);

        if(rootA != rootB){
            parent[rootA] = rootB;
        }
    }
};