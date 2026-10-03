class Solution {
public:
    int totalsolutions=0;
    int totalNQueens(int n) {
        vector<bool> cols(n);
        vector<bool> diag1_TopL_to_BottomR(2*n);
        vector<bool> diag2_TopR_to_BottomL(2*n);

        // call our helper
        backtrack_helper(0 , n , cols ,diag1_TopL_to_BottomR , diag2_TopR_to_BottomL );

        return totalsolutions;
    }

private:
    // very imp helper
    void backtrack_helper(int row , int n , vector<bool> &cols , vector<bool> &diag1 ,vector<bool> &diag2){
        // very imp base case
        if(row == n){
            totalsolutions++;
            return;
        }

        for(int col = 0; col < n; col++){
            // very vry imporant check of n queen rules
            // if these r true , thenwe cant place the queens
            if(cols[col] || diag1[row - col + n] || diag2[row + col]){
                continue;
            }

            // if we can place then mark them
            cols[col] = true;
            diag1[row -col + n] = true; 
            diag2[row + col] = true;

            // now we call ou rhleper to place other queens
            backtrack_helper(row + 1 , n , cols , diag1 , diag2);

            // backtrack step : if we cant place the qeen here look for other possibile places
            cols[col] = false;
            diag1[row -col + n] = false; 
            diag2[row + col] = false;
        }
    }
};