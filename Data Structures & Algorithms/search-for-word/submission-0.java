class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // 1. Tally all characters on the board
        int[] boardCount = new int[128];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boardCount[board[r][c]]++;
            }
        }

        // 2. Pruning: Check if the board has enough characters for the word
        char[] wArray = word.toCharArray();
        int[] wordCount = new int[128];
        for (char ch : wArray) {
            wordCount[ch]++;
            if (wordCount[ch] > boardCount[ch]) {
                return false; 
            }
        }

        // 3. Pruning: Reverse word if the last letter is rarer than the first letter
        if (boardCount[wArray[wArray.length - 1]] < boardCount[wArray[0]]) {
            reverse(wArray);
        }

        // Standard 4-directional search loop
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == wArray[0]) {
                    if (backtrack(r, c, 0, board, wArray)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(int r, int c, int index, char[][] board, char[] word) {
        if (index == word.length) {
            return true;
        }

        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word[index]) {
            return false;
        }

        char temp = board[r][c];
        board[r][c] = '#';

        boolean found = backtrack(r + 1, c, index + 1, board, word) ||
                        backtrack(r - 1, c, index + 1, board, word) ||
                        backtrack(r, c + 1, index + 1, board, word) ||
                        backtrack(r, c - 1, index + 1, board, word);

        board[r][c] = temp;
        return found;
    }

    private void reverse(char[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
