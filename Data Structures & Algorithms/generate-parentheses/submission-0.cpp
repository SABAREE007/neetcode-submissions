class Solution {
public:
    vector<string> generateParenthesis(int n) {
        // hey so java code is give in java section
        // this is a very very evry important backtrackign question so 
        vector<string> ans;
        // we call our baktracking _ recusive funciron to help this
        backtrack_recursive_helper(ans , "" , 0 , 0 , n );
        // "" this empty string reprsent the current state of the string in a current receurive call
        return ans;
    }
private:
    void backtrack_recursive_helper(vector<string> &ans , string current, int open, int close , int max){
        // this is our very very imporant heloer fucntion so baseically we have 3 core rules 
        // we r given with n pairs so we can have n* 2 brakets totalty , in order to be a well formed parenthiese we should have 3 open and 3 closing brakets. amd it sould also follow the rules correctly , closing braket should not come beofre opeing braket like that
        // so the main rules are:
        // rule 1: we can only add opening bracet to the string only if then count of open is less than maximum count(open < max)
        // rule 2: we can only add closing braket only when closing braket count is less than opening braket counts

        // very imporant base case : when both open and close counts follows this and hits the max possible counts then we have got the valid well formed parenthesis so we add it to the list
        if(open == max && close == max){
            ans.push_back(current);
            return;
        }

        // step 1 : choice 1(rule 1)
        if(open < max){
            // we can call our recurive helper succh that we can given the updatedstring inside the functipn argument itself
            backtrack_recursive_helper(ans , current + "(" , open + 1 , close , max);
        }

        // step 2: choice 2(rule 2)
        if(close < open){
            backtrack_recursive_helper(ans , current + ")" , open , close + 1 , max);
        }
    }
};