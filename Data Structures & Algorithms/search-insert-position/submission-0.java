class Solution {
    public int searchInsert(int[] nums, int target) {

        int low = 0;
        int high = nums.length-1;
        int ans = nums.length; // this is our condtion if we found the target then its index value is the ans , but if not then ans will be the our array length;

        while( low <= high){
            int mid = low + (high - low) / 2;
            // first we check and spkit its ha;lf and check the value on its only half and we repeat it untill we found smallest index
            if(nums[mid] >= target){
                ans = mid;  // store the index value
                high = mid -1;
            }
            else{
                low = mid + 1;
            }
        }

        return ans;


    }
}