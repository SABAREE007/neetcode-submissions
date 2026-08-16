class Solution {
    public int splitArray(int[] nums, int k) {
        // This is exactly the Book Allocation problem! -> do chck it out before doing this
        // We want to minimize the largest sum of any split subarray.
        int n = nums.length;
        
        // If we need more splits than elements, it is impossible.
        if (n < k) {
            return -1;
        }
        
        int maxElement = 0;
        long totalSum = 0; // Using long to avoid overflow on massive sums
        
        for (int i = 0; i < n; i++) {
            maxElement = Math.max(maxElement, nums[i]);
            totalSum += nums[i];
        }
        
        // low = largest single element (a single partition must hold it).
        // high = sum of all elements (if k = 1 split).
        long low = maxElement;
        long high = totalSum;   
        
        long ans = -1;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            
            // Count how many partitions are needed if the max sum limit is 'mid'
            int partitionsCount = countPartitions(nums, mid);
            
            // Goal: Minimize the largest subarray sum
            if (partitionsCount <= k) {
                ans = mid;
                high = mid - 1; // Try to look for a smaller maximum sum on the left
            } 
            else {
                low = mid + 1; // Not enough partitions; increase capacity to the right
            }
        }
        return (int) ans;
    }
    
    // Your important helper function, renamed for clarity
    private int countPartitions(int[] nums, long maxClusterSum) {
        int n = nums.length;
        int partitions = 1; // Start counting with the first partition
        long currentClusterSum = 0; 
        
        for (int i = 0; i < n; i++) {
            if (currentClusterSum + nums[i] <= maxClusterSum) {
                currentClusterSum += nums[i];
            } 
            else {
                // Limit exceeded! Create a brand new split partition
                partitions++;
                currentClusterSum = nums[i]; 
            }
        }
        return partitions;
    }
}
