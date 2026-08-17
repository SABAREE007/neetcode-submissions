

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> window = new HashSet<>();
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            
            // 1. REMOVE FIRST: If the window already holds 'k' elements, 
            // remove the oldest element at 'left' BEFORE checking or adding the new one.
            if (window.size() > k) {
                window.remove(nums[left]);
                left++;
            }

            // 2. CHECK SECOND: Now check if the new element is a duplicate
            if (window.contains(nums[right])) {
                return true;
            }

            // 3. ADD THIRD: Add the new element into the window
            window.add(nums[right]);
        }

        return false;
    }
}
