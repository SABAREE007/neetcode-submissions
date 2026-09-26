class Solution {
    
    // this quesrion a new type recrusion queston which i felt had this is pufle a backtracking question with recursion 
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        // now we r goig to call our recusrive backtracking helper fucntion to solve this
        solve_recursive_backtrack_helper(0, nums, new ArrayList<>());
        return ans;    
    }

    // our important very imp , recusion helper
    public void solve_recursive_backtrack_helper(int index , int[] nums, List<Integer> current){
        // a very veru imp base case of recusion:
        if(index == nums.length){
            ans.add(new ArrayList<>(current));  // very very imprtsnt step and we need to crete a new list then only opass because this fucntio is doing by caall by refrence so
            return;
        }
        // since this is a bactracking question for every element we have 2 choices
        // step 1 : take this element
        current.add(nums[index]);
        solve_recursive_backtrack_helper(index + 1 , nums , current);

        // veruy veey imporat step
        // step 2 : backtracking step : remove the item we just addded
        current.remove(current.size() - 1);

        // step 3 : choice B : skip this element
        solve_recursive_backtrack_helper(index + 1 , nums , current);
    }
}