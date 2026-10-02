class Solution {
    // we can use the same exact logic as qeustion for matchsticks to square quedtion bbut that gives us 3ms solution
    // here we cna better otpimze it to 1ms 
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // first we find the total sum
        int total_sum = 0;
        for(int num : nums){
            total_sum += num;
        }

        if(total_sum % k != 0){
            return false;
        }

        int target_sum_per_bucket = total_sum / k;

        boolean[] visited = new boolean[nums.length];  // a visited check list , so we dont use an aldready use number for another bucket

        // we sort our array in desc order
        Arrays.sort(nums);
        reverse(nums);

        // veyr very imporant check , so after this , our fist value will be largest value, so if our largest value > target sum which needed for a bucket , then this value would be useless , so we cannot divide into k equal sum subset because this value cant be added anywhere
        if(nums[0] > target_sum_per_bucket){
            return false;
        }

        // now we all our recursvive helper to solve this
        return backtrack_helper(0 , k , 0 , target_sum_per_bucket, nums , visited); 
    }

    // this part is our veru importan part
    public boolean backtrack_helper(int index ,int k ,int current_sum , int target_sum_per_bucket,int[] nums ,boolean[] visited){
        // a very very imporant base case , and a optimization step -> so we r going to use bucket first strategy here so we need to fill k buckets , so the thing is if untill (k-1) is filled succefully with equall sum on the others , there is no need to validate for k = 0 because we have proved for all others so we can skip this step
        if(k == 1){
            return true;
        }

        // this is an very veyr imporant part -> when for a bucket , if we reached our target value , then we can stop for this call , and look for next bucket
        // very very impoant thing is -> if we r going to a new bucket , we r restarting things , freshly so we will start again from firstt and sum will be reseted to 0
        // importatly we will not use aldready used value
        if(current_sum == target_sum_per_bucket){
            return backtrack_helper(0 , k - 1 , 0 , target_sum_per_bucket, nums , visited);
        }

        // now a very imporant part starts : our core part
        for(int i = index; i < nums.length; i++){
            // first if our value is aldrwady been visited before our used , we should not use that again so , we skip it 
            if(visited[i]){
                continue;
            }
            // Skip this number for the current bucket if it would exceed the target.
            // The number is still unused and can be tried in another bucket.
            // so if u look above when for a current bucket if we rwavh te target , we start a new bucket with empty values and used values have been visited and we shounlt not use , so at that time , we can use this number
            if(current_sum + nums[i] > target_sum_per_bucket){
                continue;
            }

            // so now we can use our current value because adding it to the curent sum will result in lesser value thn target so -> since we r using this vlaue we hsould mark it
            visited[i] = true;

            // Now we recursively try to add more numbers to the
            // SAME current bucket.
            //
            // i + 1 is important because nums[i] is the number
            // we just selected, so the next search starts after it.
            //
            // The recursive calls will eventually handle the remaining
            // buckets after this bucket is completed. 
            if(backtrack_helper(i + 1 , k , current_sum + nums[i] , target_sum_per_bucket, nums , visited)){
                return true;
            }

            // Backtracking:
            // The choice of nums[i] did not lead to a valid solution,
            // so we undo our choice and make this number available again.
            visited[i] = false;

            // Optimization:
            //
            // Case 1:
            // If current_sum was 0, we were trying to start a completely
            // new bucket with nums[i]. If that starting choice fails,
            // there is no need to try another equivalent starting branch.
            //
            // Case 2:
            // If nums[i] exactly completed the current bucket and the
            // remaining buckets still failed, there is no need to keep
            // trying more numbers for this bucket.
            if(current_sum == 0 || current_sum + nums[i] == target_sum_per_bucket){
                break;
            }
        }
        return false;
    }

    public void reverse(int[] arr){
        int l = 0;
        int r = arr.length  - 1;
        while( l < r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            l++;
            r--;
        }
    }
}