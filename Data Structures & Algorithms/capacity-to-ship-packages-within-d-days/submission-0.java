class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxWeight = 0;
        int totalWeight =0;
        for(int i =0;i<weights.length;i++){
            maxWeight = Math.max(maxWeight,weights[i]);
            totalWeight += weights[i];
        }

        
         int lo = maxWeight;   // Minimum possible valid capacity
        int hi = totalWeight; // Maximum possible required capacity
        // here low starts from maxweight because for other wights even after 5 days we cant completly all all the packages so

        // Step 2: Binary Search to find the minimum working capacity
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2; // mid represents our hypothetical ship capacity

            // Check how many days it takes to ship everything at capacity 'mid'
            if (getDaysNeeded(weights, mid) <= days) {
                hi = mid;     // This capacity works! Squeeze left to look for a smaller capacity
            } else {
                lo = mid + 1; // Too small! We need a stronger boat capacity, search right
            }
        }
        return lo; // lo will naturally point to the minimum optimal capacity
    }

    // Step 3: O(N) Inline simulation helper function
    private int getDaysNeeded(int[] weights, int capacity) {
        int daysNeeded = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            // If adding this package exceeds current boat capacity, ship it out!
            if (currentLoad + weight > capacity) {   // after adding weight , then we check wheather it can hold upto capacuoty if not , shift that package to next day
                daysNeeded++;    // Move to the next day's boat
                currentLoad = 0; // Reset boat load counter for the new day
            }
            currentLoad += weight; // Load package onto the boat
        }
        return daysNeeded;
    }
}