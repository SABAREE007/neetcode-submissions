class Solution {
    // this is a easy question -> solve combination sum question to get idea
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> curr = new ArrayList<>();
        // we call our recursive helper
        backtrack_helper(1 , curr , n , k);
        return res;
    }

    public void backtrack_helper(int start ,List<Integer> curr ,int n ,int k){
        // imporant base case
        if(curr.size() == k){
            res.add(new ArrayList<>(curr));
            return;
        }

        for(int i = start; i <=n - (k - curr.size()) + 1; i++){
            curr.add(i);
            backtrack_helper(i + 1 , curr , n , k);
            curr.remove(curr.size() - 1);
        }
    }
}