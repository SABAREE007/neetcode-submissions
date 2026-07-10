class Solution {
    public String simplifyPath(String path) {
        String[] components = path.split("/");  // split sperartes the string whenevr it seels '/' and considers as a sepearte elemment
        Deque<String> stack = new ArrayDeque<>();

        for(String dir : components){
            // case 1: if u saw a ".". do absolutley nothing
            if(dir.isEmpty() || dir.equals(".")){
                continue;  // this will come out of iteration ad goes to next iteartion
            }

            // case 2 : if u saw ".." thyen u must come back to parent directory so pop opeartion
            if(dir.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            // case 3: if not case 1 or case 2 just simply push into stack
            else{
                stack.push(dir);
            }
        }

        StringBuilder sb = new StringBuilder();
        // convert the stack elemts into a string and it should follow the rules of how answer should be
        while(!stack.isEmpty()){
            sb.append("/").append(stack.pollLast());
        }
        if(sb.length() == 0){
            return "/";
        }
        return sb.toString();
    }
}