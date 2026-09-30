class Solution {
    
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // this is a very hard question , but its similar to its previous sum
        // here we shouuld not have a dsolution set of duplicates and candiates kus be used only once, but if it duplicates in input array then we can use till how many it has 
        res = new ArrayList<>();

        // for this qiestion a veru important step we must do is to sort the array before we perform futter things
        Arrays.sort(candidates);

        List<Integer> curr = new ArrayList<>();
        // we now call our recurive helper ot do this
        backtrack_recursive_helper(0 , curr , 0 , candidates , target);

        // we retunr our final
        return res;
    }

    // very impornt helper
    public void backtrack_recursive_helper(int index ,List<Integer> curr , int total ,
    int[] nums ,int target){

        // since this is recurive fnction  we need a base case
        // this is a very impromt t base case
        if(total == target){
            res.add(new ArrayList<>(curr));
            return;  // we retunr bacl to the pointer
        }

        // this is the veyr wvry very imp part
        for( int i = index; i < nums.length; i++){
            // a very very main importn checking condition in order to avoid duplicates on regturn to same level
            // Skip duplicate choices at the SAME recursion level.
            // i > idx means this is not the first element at this level,
            // and candidates[i] == candidates[i - 1] means it has the same value
            // as the previous choice, so taking it would create duplicate combinations.
            // We still allow duplicates at deeper levels because they are different elements.
            if (i > index && nums[i] == nums[i - 1]) {
                continue;
            }

            // this is a very also impportant part ,
            // if we add this current value to thte totla and this new total is larger than target then incliduing this and the next set of elements are useless beacuse all r higher value elements which will lead to same step so we break it
            if(nums[i] + total > target){
                break;
            }

            // this is where imporsnt backtracjing starts
            curr.add(nums[i]);
            // our recurvie step -> go to the next level
            backtrack_recursive_helper(i + 1 , curr , nums[i] + total , nums , target);

            // this above part can either be used to find the combintions or it fails to find the cobinations for either of them we must apply backtracking step to remove the element and work on other choices
            curr.remove(curr.size() - 1);
        }
    }
}