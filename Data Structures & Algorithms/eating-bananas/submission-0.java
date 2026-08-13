class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // in this qeustion we have to mainly find how the minimum amount of bananas the monkey ate (per hour) fully all piles untill the guard arrives in h hours so
        // this question is BS on ansers (where we work on a range)
        // in order to calucale or find the range we have to find the pile with MAX amount of bananas there in that pile

        // Minimum eating speed must be 1 to prevent division-by-zero crashes.
        int low = 1; 
        int high = maxPileOfBananas(piles);

        int BananasPerHour = Integer.MAX_VALUE;
        // now we r performing normal BS
        while(low <= high){
            // we first find the middle value of that range
            int mid = low + (high - low) / 2;  
           // mid represents our target eating speed K
            long totalHours_for_k_bananas = 0;  // to get the total hours taken by eating k amount of bananas per hours
            // in this whole while loop proces we r finding the minimum k bananas monket ate per hour untill gued arrves 
            // so in fiest iteration for mid (amount of bananas) and calcukate its total hours 
            totalHours_for_k_bananas = totalHours(piles,mid);

             // Check if this speed allows Koko to finish before the guard returns
            if(totalHours_for_k_bananas <= h){
                BananasPerHour = Math.min(BananasPerHour,mid);
                // here we have found out a type of mimmum amount of bananas whih could complete , so the valyes after this range will also satisyes -> but we need the minimum value of bananas so
                 // Look for a smaller, more optimal minimum speed on the left
                high = mid - 1;
            }
            else{
                 // Too slow! We must increase our eating speed range to the right
                low = mid + 1;
            }
        } 
        return BananasPerHour;
    }
    // helper fucntions :
    // firs t to find the max amunt of banaans in a pile
    private int maxPileOfBananas(int[] piles){
        int n = piles.length;
        int max=0;
        for(int i =0;i<n;i++){
            max = Math.max(max , piles[i]);
        }
        return max;
    }
    // very very imporatant core helper fucntion
    private long totalHours(int[] piles, int k){
        // in here for k amount of banas per hour -> we need to calute total hours it tale to complete all the piles of banans
        int n = piles.length;
        long totalhours = 0; // Using long to shield against integer overflow
        for(int i =0; i <n;i++){
             // Safe integer ceiling division trick instead of broken Math.ceil()
            totalhours += (piles[i] + k - 1) / k;
        }
        return totalhours;
    }
} 