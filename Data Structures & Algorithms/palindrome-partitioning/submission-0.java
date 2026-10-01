class Solution {
    // in this question , we have to partition the string , which will be a substring , so each of thiese ssubstrng should be a palindrome so
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        List<String> curr = new ArrayList<>();
        // we call our helper funtion to solve this
        backtrack_helper(0 , s , curr);
        return res;    
    }
    // we call our helper
    public void backtrack_helper(int index ,String s ,List<String> curr){
        // imp base condtion:
        if(index == s.length()){
            res.add(new ArrayList<>(curr));
            return;
        }

        // ou rmain imortant part of this recursiv funxion : horizontal checking
        for(int i = index; i < s.length(); i++){
            // first for the substrng , we take we check weather it is a palindorme or not
            if(isPalindrome(s , index , i)){  // index = start , i = end;
                // if this substring is a palindrome then
                curr.add(s.substring(index , i + 1));
                // veyr imporat part : we move horizontaly i + 1 to go find from the remaing string;
                backtrack_helper(i + 1 , s , curr);
                // our last step : backtracking step -> remove last and find other posibilities
                curr.remove(curr.size() - 1);
            }
        }
    }

    // main imp part
    public boolean isPalindrome(String s ,int left ,int right){
        while(left < right){
            if(s.charAt(left) != s.charAt(right)){
                return false;
            }
            // if not 
            left++;
            right--;
        }
        return true;
    }
}