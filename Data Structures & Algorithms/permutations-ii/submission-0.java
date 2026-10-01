class Solution {
    // here we use freequency counting logic -> since we have duplicates here so
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        // here we start
        Map<Integer , Integer> freqMap = new HashMap<>();
        // stoe frequency for each number
        for(int num : nums){
            freqMap.put(num , freqMap.getOrDefault(num , 0) + 1);
        }
        List<Integer> curr = new ArrayList<>();
        // we call our helper
        backtrack_helper( nums.length , freqMap , curr);
        return res;
    }

    public void backtrack_helper( int targetSize ,Map<Integer,Integer> freqMap ,List<Integer> curr){
        // our base case:
        if(curr.size() == targetSize){
            res.add( new ArrayList<>(curr));
            return;
        }

        // now we iterate thorfh the map
        for(int num : freqMap.keySet()){
            // get current count
            int count = freqMap.get(num);
            if(count > 0){
                curr.add(num);
                // update the count of that number by reducing it -> for next ietartion purposes
                freqMap.put(num , count - 1);
                // call ur recursve fucntion
                backtrack_helper( targetSize , freqMap , curr);
                // after finsih this -> we perfrom backtracking step
                curr.remove(curr.size() - 1);
                // we restore the original freqncunecy count of this current call
                freqMap.put(num , count);
            }
        }
    }
}