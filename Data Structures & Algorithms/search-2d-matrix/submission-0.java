class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // this is BS on 2d matrix problem
        // we have to flatten our 2d matrix into 1d matrix ( but without converting it)
        // so we use some rules for the conversion 

        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0;
        int high = (n * m) - 1;

        while( low <= high){
            int mid = low + (high - low) / 2;

            // now we have our 1d index , but we have numbers in  2d matrix , so we have to find its 2d matrinx index which can be found by
            int row = mid / m;  // this tell us how many rows , u have skiiped and u r now in a current row , it tells us which row u r in 
            int col = mid % m;  // after finding which row , we have to find which column so this gives us how many steps tp move to get to that column

            if(matrix[row][col] == target){
                return true;
            }
            else if(matrix[row][col] > target){
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        return false; // this returs false if we coudnt find out our element target
    }
}