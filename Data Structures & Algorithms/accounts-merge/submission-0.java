class DisjointSet {
    int[] parent, size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findUltimateParent(int node) {
        if (node == parent[node]) return node;
        return parent[node] = findUltimateParent(parent[node]);
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUltimateParent(u);
        int ulp_v = findUltimateParent(v);
        if (ulp_u == ulp_v) return;

        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // explanation in notes please check it
        // we r doing this based on dsu so disjoint set class is written above

        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        // step 1:
        Map<String , Integer> mapMailNodes = new HashMap<>();

        for(int i=0; i < n;i++){
            for(int j =1; j < accounts.get(i).size(); j++){
                // we start j from 1 because from 1 index only we will have all emails
                String email = accounts.get(i).get(j);
                //imp part
                if(!mapMailNodes.containsKey(email)){
                    mapMailNodes.put(email , i);
                }
                else{
                    // veyr veyr evry imporant step -> merging
                    ds.unionBySize(i , mapMailNodes.get(email));
                }
            }
        }

        // step 2: merginf accounts
        List<List<String>> mergedMails = new ArrayList<>();
        for(int i =0; i < n;i++){
            mergedMails.add(new ArrayList<>());  // initialization step:
        }

        for(Map.Entry<String , Integer> entry : mapMailNodes.entrySet()){
            String mail = entry.getKey();
            // vveyr evyr imporant step: get ultimate parent account index
            int node = ds.findUltimateParent(entry.getValue());
            // vvimp step
            mergedMails.get(node).add(mail);
        }

        // step 3: anser formation
        List<List<String>> ans = new ArrayList<>();
        for(int i =0; i <n;i++){
            // edge case:
            if(mergedMails.get(i).isEmpty()){
                continue;
            }

            // sort the emails
            Collections.sort(mergedMails.get(i));
            List<String> temp = new ArrayList<>();  // temp list to form the inner merged list
            temp.add(accounts.get(i).get(0)); // first add the name
            temp.addAll(mergedMails.get(i));  // now add all sorted emails

            // put it into the answer
            ans.add(temp);
        }
        return ans;
    }
}