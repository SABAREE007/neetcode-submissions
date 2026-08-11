class Solution {
    public int search(int[] nums, int target) {
        // edge case :
        if(nums.length == 0 ){
            return -1;
        }
        int low = 0;
        int high = nums.length-1;


        // loop runs untill left(or low) crosses high or meets high
        while(low <= high){
            // calculate the middle position
            int mid = low + (high - low) / 2;
            
            if(nums[mid] == target){
                return mid;
            }
            else if(nums[mid] < target){
                low = mid + 1;
            }
            else{  // if mid is larger than target then the search item is located on first half
                high = mid -1;
            }
        }
        return -1;
    }
}