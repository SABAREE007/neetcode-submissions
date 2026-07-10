class Solution {
    public String decodeString(String s) {
        // Optimization 1: Use ArrayDeque instead of Stack for faster, non-synchronized operations
        Deque<Character> stack = new ArrayDeque<>();

        // Optimization 2: Use a standard index loop to avoid creating a temporary char array via toCharArray()
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c != ']') {
                stack.push(c);
            } else {
                // 1. Build the substring to be repeated
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty() && stack.peek() != '[') {
                    sb.insert(0, stack.pop());
                }
                stack.pop(); // Remove the '['

                // 2. Get the number K preceding the bracket
                StringBuilder k = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k.insert(0, stack.pop());
                }
                int count = Integer.parseInt(k.toString());

                // 3. Repeat and push back to stack
                String sub = sb.toString();
                int subLen = sub.length();
                for (int m = 0; m < count; m++) {
                    // Optimization 3: Push characters using charAt() instead of creating an array per repetition
                    for (int j = 0; j < subLen; j++) {
                        stack.push(sub.charAt(j));
                    }
                }
            }
        }

        // 4. Convert stack to string
        StringBuilder result = new StringBuilder();
        // Optimization 4: Since ArrayDeque's iterator reads from top-to-bottom (opposite of old Stack), 
        // we use pollLast() to cleanly drain elements from bottom-to-top in O(N) time.
        while (!stack.isEmpty()) {
            result.append(stack.pollLast());
        }
        
        return result.toString();
    }
}
