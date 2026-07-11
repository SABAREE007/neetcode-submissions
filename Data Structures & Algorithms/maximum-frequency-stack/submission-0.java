class FreqStack {

    private HashMap<Integer, Integer> freqMap;

    private HashMap<Integer, Deque<Integer>> groupMap;  // this is to store seperate seperate hap which is seperate frequency level and its stack elements
    
    int maxFreq;


    public FreqStack() {
        // declare the variables here in this constructor
        freqMap = new HashMap<>(); 
        groupMap = new HashMap<>();
        maxFreq = 0;  //at first maximum frequency will be 0 
    }
    
    public void push(int val) {
        // herr this is a  push opeartiomn and elements are pushed one by one so no useage of for and while loops
        int currentFreq = freqMap.getOrDefault(val , 0) + 1;
        freqMap.put(val , currentFreq);
        // to chech andd get the max frequency while adding itself
        if(currentFreq > maxFreq){
            maxFreq = currentFreq; 
        }

        // now we have to store this value in its respective frequency in its respective frequency level stack
        // create a new groupmap only if the current frequncy level doesnt exist
        if(!groupMap.containsKey(currentFreq)){
            groupMap.put(currentFreq , new ArrayDeque<>());
        }
        // now insert the val in its respective frequency level stack
        groupMap.get(currentFreq).push(val);
        // this above step gets the stack in the respective frequency key in the map and push onto it

    }
    
    public int pop() {
        // very vry important step
        // get the max frequency value by going into the max fequency level and getting the element from its stack
        int maxFreqElement = groupMap.get(maxFreq).pop();
        
        // now next step is to reduce the frequency of the element whihc we poped out now and decrementing its count by 1
        freqMap.put(maxFreqElement , freqMap.get(maxFreqElement) - 1);

        // now the cleanup step 
        //if the stack of the max frequency level is empty then we decrement the maximum frequncy value by 1 down so its go to next below levels
        if(groupMap.get(maxFreq).isEmpty()){
            maxFreq--;
        }

        //now return the poped value
        return maxFreqElement;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */