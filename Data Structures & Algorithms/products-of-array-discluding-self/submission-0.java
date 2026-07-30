class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        //optimal one
        //note: store the first index of ans as 1 because in prefix product array we should calculate its product before elements but in here as the condtion says we should not consider the element itself
        ans[0] =1;
        // use the output array as prefix array for first pass
        for(int i =1;i<n;i++){  // start the index of ans from 1st index as 0 is aldready occupied
            ans[i] = ans[i-1] * nums[i-1];
        }
        // now for second pass whihc is the suffix product so in suffix product the lat elemtn value will be 1 which is the same rule of prefix so
        int suffixProduct =1;
        for(int i = n-1; i>=0;i--){  // we come from the back of array
            ans[i] = ans[i] * suffixProduct;
            suffixProduct = suffixProduct * nums[i];
        }

        return ans;
    }
}