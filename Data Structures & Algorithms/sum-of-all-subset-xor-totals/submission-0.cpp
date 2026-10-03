class Solution {
public:
    int subsetXORSum(vector<int>& nums) {
        // This is a backtracking question based on subsets.
        // We call a helper function to recursively generate all possible subsets.
        return backtrack_helper(0, 0, nums);
    }

    int backtrack_helper(int index, int current_xor, vector<int> &nums) {

        // Important base case:
        // When we reach the end of the array, we have finished deciding
        // whether to take or not take every element.
        // Therefore, one complete subset has been formed.
        // current_xor contains the XOR value of this particular subset,
        // so we return it to the parent call.
        if (index == nums.size()) {
            return current_xor;
        }

        // Since this is a subset/backtracking problem, for every element
        // we have two choices:
        // 1. Take the current element.
        // 2. Don't take the current element.
        //
        // Here we take nums[index], so we include it in the current subset.
        // Therefore, we update current_xor using XOR with nums[index].
        int take = backtrack_helper(
            index + 1,
            current_xor ^ nums[index],
            nums
        );

        // Option 2: Don't take the current element.
        // So we move to the next element without changing current_xor.
        int not_take = backtrack_helper(
            index + 1,
            current_xor,
            nums
        );

        // Each recursive call represents a group of possible subsets.
        // 'take' contains the sum of XOR values from all subsets
        // formed by taking nums[index].
        // 'not_take' contains the sum of XOR values from all subsets
        // formed by not taking nums[index].
        //
        // Together, these two branches contain all possible subsets,
        // so we add their results and return the total to the parent call.
        return take + not_take;
    }
};