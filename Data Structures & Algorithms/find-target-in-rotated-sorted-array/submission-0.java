class Solution {
    public int search(int[] nums, int target) {
        // very very important question 
        // here our array is left rotated , since left toated
        // we have 2 halfs and -> either both halfs can be sorted , or one can other not , or no one
        // so the algorithm follows
        int low = 0;
        int high = nums.length-1;

        

        while( low <= high){
            // first find the middle element
            int mid =low + (high - low) / 2;
            if(nums[mid] == target){
                return mid;
            }
            // now the thing is our serach element can be either on of the half since it is a distinct elements only
            // first we have to check which half is sorted and check in it wheather element is there or not , if not there then it woube in the next half
            // to check left sorted half
            if(nums[low] <= nums[mid]){
                // here left thalf is sorted
                // now we search if element exits in here by 
                if(nums[low] <= target && target < nums[mid]){
                    // so it eixts and we before normal bs and search in this half
                    // very important here low also can be our taget element so dont forget to add < or eqal to =
                    high = mid -1;
                }
                else{
                    // since in that half even if it was sorted but there was not the element present so we have to check in other half
                    low = mid +1;
                } 
            }
            else{
                // this condtion works if that half is not sorted first then 
                // this half will be sorted surely
                // now check if our element can exists in this half
                if(nums[mid] < target && target <= nums[high]){
                    // here it exits so we perform normal bs to check 
                    // very important here high also can be our taget element so dont forget to add < or eqal to =
                    low = mid + 1;
                }
                else{
                    // since in that half even if it was sorted but there was not the element present so we have to check in other half
                    high = mid -1;

                }
            }
        }
        return -1;
    }
}