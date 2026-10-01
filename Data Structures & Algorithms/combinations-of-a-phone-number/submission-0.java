class Solution {
    List<String> res = new ArrayList<>();
    // we store all the mapping for each nubers given in the image in an array
    String[] mappings = {
        "", // 0
        "", // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs", // 7
        "tuv", // 8
        "wxyz" // 9
    };
    public List<String> letterCombinations(String digits) {
        // edge case :
        if(digits == null || digits.length() == 0){
            return res;
        }

        StringBuilder curr = new StringBuilder(); // since we r builting the string so
        // we call our helper function
        backtrack_helper(0 , digits , curr);
        // finally retunr
        return res;
    }

    // our imorant helper:
    public void backtrack_helper(int index ,String digits ,StringBuilder curr){
        // our imp base case:
        if(index == digits.length()){
            // we include this string in our result
            res.add(curr.toString());
            return;
        }

        // so we need to first conveet that string number to an integer
        int digit = digits.charAt(index) - '0';
        // after converting , for the respective number , we have to extract its mapped letters for that numbers
        String letters = mappings[digit];

        // now our imporant part of the functins starts
        for(int i = 0; i < letters.length(); i++){
            // now we incude this letter into our current string
            curr.append(letters.charAt(i));

            // now we apply recurive function -> to go to the next digit and find out the possible combinations
            backtrack_helper(index + 1 , digits , curr);

            // now backtracking step: -> remove the last letter and automaticlay it goes for the next letters of that digit
            curr.deleteCharAt(curr.length() - 1);
        }
    }
}