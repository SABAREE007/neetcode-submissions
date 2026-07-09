class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // this question is a cassic mintonic stack question simply we have to find the next greater element of the cuurent index's value and we have to return no. of days untill the temperature wioill be warmer
        Deque<Integer> stack = new ArrayDeque<>();
        int n = temperatures.length;
        int[] result = new int[n];
        for(int i =0;i<n;i++){
            int current = temperatures[i];
            while(!stack.isEmpty() && current > temperatures[stack.peek()]){
                int poppedIndex = stack.pop();
                result[poppedIndex] = i - poppedIndex;
            }
            stack.push(i);
        }
        return result;
    }
}
