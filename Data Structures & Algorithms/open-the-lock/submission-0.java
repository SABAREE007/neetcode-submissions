class Solution {
    public int openLock(String[] deadends, String target) {
        // this is a similar question like word ladder 
        // but we can rotate the wheel clockwise and anticlockwise so 
        // and the thing is we should check the cuurent combination shuld not exist in the deadends , if our combinations reaches then thats it 
        Set<String> deadend = new HashSet<>(Arrays.asList(deadends));
        // veyr vry important edge case
        if(deadend.contains("0000") || deadend.contains(target)){
            // the start and the end should not be in the deadend list , if its there then we can process it
            return -1; 
        }
        // another imp edge case
        if(target.equals("0000")){
            return 0;   // if our target itself is the initial start then there is no need to rotate
        }

        // since this is a implicit graph question and all edges have equal weights then
        // we use bfs 
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        // an imporant very impornt thng , in previous questoion(word ladder , muation ) we deleedt by using a trick : trick: We deleted words from the dictionary set the moment we visited them (words.remove(new_word)). By deleting the word from the allowed list, it was impossible to step backward onto it again. The dictionary set doubled as our visited tracker
        // but in here we cant do like that because we need to constatntly check our lock password to c wheather this has reached deadend or not becuase we should not reach it -> so we should not touch that and we will use a visited set to keep track of what and combinations have worked so far so that we dont do that repeations circle thing
        Set<String> visited = new HashSet<>();
        visited.add("0000");

        // since our main aim is to caluclate minimum moves to reach the target so
        int moves = 0;
        // our bfs starts
        while(!q.isEmpty()){
            int size = q.size();
            for(int i =0; i < size; i++){
                String curr = q.poll();
                // our very imp check
                if(curr.equals(target)){
                    return moves;
                }

                // if not then
                char[] wheels = curr.toCharArray();
                for(int j =0; j < 4;j++){  // since 4 wheels so
                    // first store the original value
                    char originalSlot = wheels[j];
                    // we have 10 slots ( 0 <-> 9)
                    for(char c = '0'; c <= '9';c++){
                        // a veyr evry very imporant rule : (check notes for explanation)
                        // CRUCIAL STEP: Only allow numbers that are exactly 1 click away!
                        // This handles 1 -> 2, 8 -> 9, and the 0 <-> 9 wrap-arounds
                        if(Math.abs(c - originalSlot) == 1 ||
                            c == '0' && originalSlot == '9' ||
                            c == '9' && originalSlot == '0'){
                                wheels[j] = c;
                                String nextState = new String(wheels);

                                // veyr imporntant check the new state must not be used again(should not be in visited) and it shoudl not be a deadend so
                                if(!visited.contains(nextState) && !deadend.contains(nextState)){
                                    q.offer(nextState);
                                    visited.add(nextState);
                                }
                            }
                    }
                    // if finishing for this wheel move to next wheel and restore the original 
                    wheels[j] = originalSlot;
                }
            }
            // veyr evy mporant thing , after 1 compledted move we increment
            moves++;
        }
        // if we cant ablw to find target then
        return -1;
    }
}