class Solution {
    // very evry omportant question -> since we have to retyurn all posibiltis
    List<List<String>> res = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        // first we intialize and mark the cell with '.'
        // since we havr to place n queens , we will have a (n x n) matrix
        char[][] board = new char[n][n];
        int rows = n; int cols = n;
        for(int i = 0; i < rows; i++){
            for(int j =0; j < rows; j++){
                board[i][j] = '.';
            }
        }

        // after we mark -> we call our helper
        backtrack_helper(0 , board , n); // here first value will indicate whcih row are win in now 
        return res;
    }

    // our imporant part starts here
    public void backtrack_helper(int row ,char[][] board ,int n){
        // our imporant base case -> if we have putted n queen , then we add the cureent n queen matrix formed
        if(row == n){
            res.add(constructBoard(board));
            return;
        }

        // now we r checking the vlaue horizontally -> which means for the cureent rows we will check a possible colum to place the queen
        for(int c =0; c < n; c++){
            //The Filter: Cross-verify if it's safe from column and diagonal attacks
            if(isSafe(row , c , board , n)){
                // if safe then we place the queen and try to place the queen for the next row 
                board[row][c] = 'Q';
                // nnow we recusive call to place queen for the below rows
                backtrack_helper(row + 1 , board , n);
                // next our bactracking step : if we coudnt place for this , or we have succefully found a resulting matrix of n queen and we r exploring to find other psosibities
                board[row][c] = '.';
            }
        }
    }

    // veyr vey iportant checking function -> to identify weather this current cell is safe from past attacks from queens
    public boolean isSafe(int row ,int col ,char[][] board ,int n){
        // so for our current cell , an attack can come only from above rows( which is the past rows) -> either from above colum cell , or cels from diagonals of both side top left anf top right

        // 1) for column -> we come from first row till the curent row
        for(int i =0; i <row; i++){
            if(board[i][col] == 'Q'){
                return false;
            }
        }

        // 2) now we check diagonals
        // top left sides -> for loop can add double conditions 
        for(int i = row - 1, j = col - 1 ; i >= 0 && j >=0 ; i--,j-- ){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        // top right sides -> for loop can add double conditions 
        for(int i = row - 1, j = col + 1 ; i >= 0 && j < n ; i--,j++ ){
            if(board[i][j] == 'Q'){
                return false;
            }
        }

        // if no attacks came thne this cell is valid
        return true;
    }

    // this is the imporant fucntion to built a full matrix of a valid n queen matrix
    public List<String> constructBoard(char[][] board){
        // c in our computer memeory matrizx are stoed as rows where each row represts a list and list value represents the columns so this is how it is
        List<String> current = new ArrayList<>();
        // we now iterate row wise
        for(int i = 0; i < board.length; i++){
            current.add(new String(board[i]));  // where each board[i] represts a row
        }

        return current;
    }
}