class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // this is a very very imporant question -> median of two sorted arrays
        // here we can solve in optimal way using Bs on answers type 

        // brute was like merginng two arays and finding median but -> tLE will come so
        // we do this via bs

        int n1 = nums1.length;
        int n2 = nums2.length;

        // in this question we have to take the range -> basically the length of the lowest array elements
        // here the range represets , how many number of elnments we can tkae from the arra1 after spkiting into half so divinf will say how many i should take from array1 and array 2 followng its result 

         // VERY IMPORTANT OPTIMIZATION: Always ensure array1 is the shorter array.
        // This ensures the binary search works on the smaller range (0 to n1),
        // keeping the runtime at the optimal O(log(min(N1, N2))) to pass OA filters.
        if(n1 > n2){
            return findMedianSortedArrays(nums2,nums1);
        }

        int low = 0;
        int high = n1;
        // very very imporatnt step :  That + 1 in the formula (n1 + n2 + 1) / 2 is a deliberate design choice that handles odd total lengths without requiring extra if-else branches.By adding 1, we ensure that if the total number of elements is odd, the extra middle element always lands on the left partition 

         // This is the size of the combined left basket.
        // For example, if total elements = 10, totalLeftElements will be 5.
        int total_no_of_elements = (n1 + n2 + 1) / 2;

        while(low <= high){
            // mid represents how many elements we pick from the left side of array1
            int mid1 = low + (high - low) / 2;

            // The remaining elements needed to fill our left basket must come from array2
            int mid2 = total_no_of_elements - mid1;

              // ISOLATING THE 4 CRITICAL BOUNDARY ELEMENTS:
            // We fetch l1 and l2 (highest on left side) and r1 and r2 (lowest on right side).
            // We use edge-case guards (Integer.MIN_VALUE / MAX_VALUE) if a cut falls outside array bounds.

            // STEP 1: Set default edge values upfront
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            // 1. LEFT HALF BOUNDARY CHECKPOINTS (Highest elements on the left side)
            if (mid1 - 1 >= 0) {
                l1 = nums1[mid1 - 1];
            }
            if (mid2 - 1 >= 0) {
                l2 = nums2[mid2 - 1];
            }


            // 2. RIGHT HALF BOUNDARY CHECKPOINTS (Lowest elements on the right side)
            if (mid1 < n1) {
                r1 = nums1[mid1];
            }
            if (mid2 < n2) {
                r2 = nums2[mid2];
            }

             // THE CROSS-COMPARISON VALIDATION CHECK:
            // Verify if the combined left half is completely smaller than the right half.

            // main steps : we now cross checking and compaing thier boindaires in a crpss way
            if(l1 <= r2  && l2 <= r1){
                // find out wheether the length is odd or even and acording to that calculate the median
                // MEDIAN FORMULA CALCULATION PASS:
                // If the total combined elements count is odd, return the maximum of the left side.
                if((n1+n2) % 2 != 0 ){
                    return Math.max(l1,l2);
                }
                else{
                    return (Math.max(l1,l2) + Math.min(r1,r2)) / 2.0;
                }
            }
            else if(l1 > r2){   // here l1 is grater than r2 whihc is booger and erong because our left sidemust be less thn right half value so
            // Too many elements taken from array1 left side; contract our search space left
                high = mid1 - 1;
            }
            else{   // this case hppens because l2 is greather than r1
                 // Too few elements taken from array1 left side; expand our search space right
                low = mid1 + 1;
            }
        }
        return 0.0;
    }
}