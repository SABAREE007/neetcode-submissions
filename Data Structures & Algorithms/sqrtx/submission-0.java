class Solution {
    public int mySqrt(int x) {
        // this question is a BS on answers question
        // so our range will be from minum value to the hgihghest value of that range
        int low =0;
        int high = x;
        int ans =0;

        while(low <= high){
            long mid = low + (high - low) / 2;
            if(mid * mid <= x){
                // we r doing the same operation of lower bound
                // finding the greatest value smaller to x to return the sqrt
                ans = (int) mid;
                // sinxe the value is lower than x then we discard the left half and search the right half
                low = (int)mid +1;
            }
            else{
                // this happens when -> square is largwet than x exceeding the value so , we discardthe right half
                high = (int)mid -1;
            }
        }
        return ans;

    }
}