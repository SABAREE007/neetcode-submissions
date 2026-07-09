class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for(String t : tokens){
            if(t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")){
                int second = stack.pop();
                int first = stack.pop();
                if(t.equals("+")){
                    stack.push(first + second);
                }
                else if(t.equals("-")){
                    stack.push(first - second);
                }
                else if(t.equals("*")){
                    stack.push(first * second);
                }
                else{
                    stack.push(first / second);
                }
            }
            else{
                stack.push(Integer.parseInt(t));
            }
        }
        return stack.peek();
    }
}
