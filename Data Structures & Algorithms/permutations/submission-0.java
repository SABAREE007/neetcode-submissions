class Solution {
    // this is my method of backtrackign solution
    List<List<Integer>> res = new ArrayList<>(); 
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        // we keep a visited track 
        boolean[] visited = new boolean[nums.length];
        // we call our helper
        backtrack_helper(visited , nums , curr);

        return res;
    }

    public void backtrack_helper(boolean[] visited ,int[] nums ,List<Integer> curr){
        // base case:
        if(curr.size() == nums.length){
            res.add(new ArrayList<>(curr));
            return;
        }


        // this for loop branch is the most important as it is used
        for(int i =0; i < nums.length; i++){
            // vry imporant ->first check if it is vited or not , if in this iteartion's a element in the array if aldready visited , then we dont add , we skip and go find other possibilties
            if(visited[i]){
                continue;
            }

            // if not then , we r use this element and mark this as visited and go find other possibilities
            visited[i] = true;
            curr.add(nums[i]);

            // now go find other psobiltie
            backtrack_helper(visited , nums , curr);

            // after this branch is finished -> then we mark this curr element as not visited and we remove from the list to find other possibilities
            visited[i] = false;
            curr.remove(curr.size() - 1);
        }
    }
    
}