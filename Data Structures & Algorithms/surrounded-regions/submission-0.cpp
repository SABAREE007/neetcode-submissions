class Solution {
public:
    void solve(vector<vector<char>>& board) {
        // this is a similar question to no of enclaves question but witha twist
        int n = board.size();
        int m = board[0].size();

        // for edge 'o' cells and its adjacent ones , all should be marked with placeholder
        for(int j =0; j < m;j++){
            // top boundary
            if(board[0][j] == 'O'){
                // launch dfs
                dfs_mark_it(board , 0 , j , n , m);
            }

            // bottom boudary
            if(board[n-1][j] == 'O'){
                dfs_mark_it(board , n - 1 , j , n , m);
            }
        }

        // now for side boundaries
        for(int i =0; i < n;i++){
            // left boundary
            if(board[i][0] == 'O'){
                // launch dfs
                dfs_mark_it(board , i , 0 , n , m);
            }

            // right boudary
            if(board[i][m - 1] == 'O'){
                dfs_mark_it(board , i , m - 1 , n , m);
            }
        }

        // our final step:
        for(int i=0; i <n;i++){
            for(int j=0; j < m;j++){
                if(board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                else if(board[i][j] == '#'){
                    board[i][j] = 'O';
                }
            }
        }
    }

    // our dfs
    void dfs_mark_it(vector<vector<char>> &board ,int r ,int c ,int n ,int m){
        //  this dfs is usd for reachability
        // base case
        if(r < 0 || r >= n || c < 0 || c >= m || board[r][c] != 'O'){
            return;
        }

        // mark the value and put a placeholder
        board[r][c] = '#';

        // launch dfs to put for adjacent 'o' also
        dfs_mark_it(board , r + 1 , c , n , m); // bottom 
        dfs_mark_it(board , r - 1 , c , n , m); // top 
        dfs_mark_it(board , r , c - 1 , n , m); // left 
        dfs_mark_it(board , r , c + 1, n , m); // right 
    }
};