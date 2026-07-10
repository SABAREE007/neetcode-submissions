class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if(n==0) return 0;

        double[][] pairs = new double[n][2];  // 2 is for 2 elements which is position and time to reach the target
        for(int i =0; i < n; i++){
            pairs[i][0] = position[i];
            pairs[i][1] = (double) (target - position[i]) / speed[i];  
            //this formula is to find the time to reach the target , distance / speed = time
        }
        // now very important step: we convert the array and sort into descending order based on the position and how close they r to the target distance
        Arrays.sort(pairs, (a,b) -> Double.compare(b[0],a[0]));

        Deque<Double> stack = new ArrayDeque<>();
        for(int i =0;i < n;i++){
            Double currentTime = pairs[i][1];
            // rule 1 : if current time of the car is lesser than stack peek then we do not push into stack
            if(!stack.isEmpty() && currentTime <= stack.peek()){
                continue;
            }

            // rule 2 : if time is greater then it will form a new fleet
            stack.push(currentTime);
        }
        return stack.size();  // this will tell us how many fleet we have 
    }
}
