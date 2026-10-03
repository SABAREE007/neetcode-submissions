class Solution {
    // this is an medium question , we have to find wheather the word exits in the list of words and if its there we need to from a sentence
    List<String> res = new ArrayList<>();
    public List<String> wordBreak(String s, List<String> wordDict) {
        // first we transform that list into set to make better look ups
        Set<String> dict = new HashSet<>(wordDict);

        StringBuilder curr = new StringBuilder();

        // we call our recurisve hlper to solve this
        backtrack_helper(0 , s , dict , curr);
        return res;
    }

    // ourt very imporatnt helper function
    public void backtrack_helper(int index ,String s ,Set<String> dict ,StringBuilder curr_str){
        // our imp base case :
        if(index == s.length()){
            res.add(curr_str.toString().trim());  // Trim removes the extra trailing space
            return;
        }

        // our very imporant part starts here
        // we r going theoihg horizontaly through the given string
        for(int i= index; i < s.length(); i++){
            // first get the word
            String word = s.substring(index , i + 1);
            // veyr imp part -> check if this word exits n te dictiinonary
            if(dict.contains(word)){
                // first u store the actual length of the current string what it is now
                int original_length = curr_str.length(); // whcih we will be used in bactraiking part

                // now include this word in the curren sentence
                curr_str.append(word).append(" ");

                // no recurivly check for other words and add and for all possible sentence with this current string
                backtrack_helper(i + 1 , s , dict , curr_str);  // we put (i + 1) is to go from the word in the string to jump to a new position to start fresh

                // after this , wither we would have formed a new possible string or in that current recurisvr call we could have not build a string so either way we have to backtrack and check for more possibitlies
                // so we bring the old state of the string in this current call
                // we do this by seting the length of the old string to this so it world erase the newly added ones to get back the old one
                curr_str.setLength(original_length);
                // we can do this by copying it into new strinbuilder and doing t in very revurvive clal butit takes space , so we use this
            }
            // we go letter by letter in each iteration and form words in each recurisv calls
        }
    }
}