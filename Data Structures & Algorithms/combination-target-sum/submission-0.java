class Solution {

    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // this is a very very very importwnt qestion
        // there 2 2 backtracking solution for here , i am using the most optimal solution here , other solution is presnt in notes section
        res = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();  // this holds the current combination at the moment or state;

        // so this is an optimal solution where prunning takes place -> an imporant ting we must to which is to sort the array before passing it
        Arrays.sort(candidates);

        // we now call our recurive helper to finifhs this
        backtracking_helper(0, curr , 0 , candidates , target);

        return res;
    }

    // our very imporant helper fucntion
    public void backtracking_helper(int index,List<Integer> curr , int total , int[] nums ,int target){
        // since this is a recurisve funciton -> outr imporant base case
        if(total == target){
            res.add( new ArrayList<>(curr));
            return;  // return back to parrent if exits(any);
        }

        // now comes the very very imporant part of thsirecurive funcion
        for(int j = index ; j < nums.length; j++){
            // since this for loop handles the iteration to part to check all types of combos
            if(total + nums[j] > target){
                // if new total > target thhen not valid so we retunr to our parent
                return;
            }

            // if that satisfues , then we can have 2 choices -> 1 is to use the same element again in the combinations to find target
            curr.add(nums[j]);
            // very important part -> where we apply choice one
            backtracking_helper(j , curr , total + nums[j] , nums , target);
            // either this recurive function can find a succesfulll combination or it fails for that combo so either outcoes come we have an imporant part
            // backtracking step:
            curr.remove(curr.size() - 1);
            // so for this iteration this is over , after this we go to next itration trying out various diffent combo that thier sum matches the target 
        }
    }

}