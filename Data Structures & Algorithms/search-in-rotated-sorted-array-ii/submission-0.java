class Solution {
    public boolean search(int[] nums, int target) {
        // this qiestion is same as the previous question of this part serach in roated sorted array 
        // but the problem here is array can have duplocate elements so we have to slightly change some things 

        // please check out the line -> 19

         // very very important question 
        int low = 0;
        int high = nums.length-1;

        while( low <= high){
            // first find the middle element
            int mid =low + (high - low) / 2;
            if(nums[mid] == target){
                return true;
            }

            // in here we can have a state where all the low , mid , and high pointers can have same value inside it 
            // eg -> nums = [1, 0, 1, 1, 1] target = 0 ->
            /*
            Now look at our previous check: -> nums[low] <= nums[mid] , That's:
             1 <= 1 ✅ -> So we'd say: "Left half is sorted."
            But look at the left half: [1, 0, 1] -> It is NOT sorted! 😭
            The 0 is hiding inside it.
            So our old logic cannot reliably identify the sorted half anymore. */
            // very importat condtion
            if(nums[low] == nums[mid] && nums[mid] == nums[high]){
                low++;
                high--;
                continue;  // skips this loop and goes for next ietartion
            }

            //  rest of the code is same

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
        return false; // if the search value not found
    }
}