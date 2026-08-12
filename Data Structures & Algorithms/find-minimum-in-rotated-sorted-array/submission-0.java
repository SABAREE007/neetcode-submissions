class Solution {
    public int findMin(int[] nums) {

        // another version of it is in leetcode which is same 
       int n = nums.length;
       int ans = Integer.MAX_VALUE;
       int low = 0, high = n-1;

       while(low <= high){
        int mid = (low + high)/2;
        if(nums[low] <= nums[high]){
            ans = Math.min(ans,nums[low]);
            break;
        }else if(nums[low] <= nums[mid] ){
            ans = Math.min(ans,nums[low]);
            low = mid + 1;
        }else {
            ans = Math.min(ans,nums[mid]);
            high = mid - 1;
        }
       }
       return ans;
    }
}