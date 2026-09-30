class Solution {

    // this is a very important question
    // this questipn foloows the same logicn of combination sum 2 question -> here we have duplicates in the input array , we need to find all possible substes

    // here we use size based approach not the take/not take appriach

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> curr = new ArrayList<>();
        // first thing we should sort our array
        Arrays.sort(nums);

        // now we call our helprr
        backtrack_helper(0 , nums , curr);

        return res;
    }

    public void backtrack_helper(int index ,int[] nums ,List<Integer> curr){
        // since this is a recurive funciton :
        // very vr y evry imporant line -> EVERY single combination we land on is a valid subset.
        // We capture it immediately at the top of the function!
        // This naturally captures the empty list [] on the absolute first call.
        res.add(new ArrayList<>(curr));

        //now very imporant art of this fucnito
        for(int i = index ; i < nums.length ; i++){
            // since we have duplicte values inthe array -> in any current recusive call we must avoid taking branh for duplicates , as it will be inserted duplicates into final result set
            if(i > index && nums[i] == nums[i-1]){
                continue;  // skip this value and move to next iteration anc check
            }

            // if not duplicate then\
            curr.add(nums[i]);
            // we call our recurive helper to work on this current branch
            backtrack_helper(i + 1 , nums , curr);

            // afte rhti sbranch is over , we eleimate this branch and move to nect ietartion to findother possibilties
            curr.remove(curr.size() - 1);
        }
    }
}