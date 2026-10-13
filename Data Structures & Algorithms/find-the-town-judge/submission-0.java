class Solution {
    public int findJudge(int n, int[][] trust) {
        // this is a simple grpah question 
        // they define trust : if a trust b then :  a-> b , b is the judge and if many people is there , all except b should trust b
        // we have to find the the person(who is the judge) , who trusts nobody and everybody trusts him
        // which means they dont have any out degrees for them

        // we dont need to create adj list , we only need t check in degree and out degree

        
        int[] out_degree = new int[n + 1];
        int[] in_degree = new int[n + 1];

        // create the list from the edge list
        for(int[] edge : trust){
            int u = edge[0]; int v = edge[1];
            
            out_degree[u]++;
            in_degree[v]++;
        }

        // the person with 0 out-degree which means he dont trust no one , and with in-degree n - 1 -> where all the people (except for the town judge) trust him
        for(int i=1; i <= n; i++){
            if(out_degree[i] == 0 && in_degree[i] == n - 1){
                return i;
            }
        }

        return -1;
    }
}