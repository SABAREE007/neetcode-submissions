class Solution {
    public boolean makesquare(int[] matchsticks) {
        // this is a very very important question and its a hard question as weel due to we have understand of question
        // since we have less constraint -> first we r ogin to find the perimeter of the square -> since we have to include all sticks which will form a square , so we find its perimeter by
        int total_sum = 0;
        for(int num : matchsticks){
            total_sum += num;
        }

        // veyr veru imporat check : if we cannot split the 4 sides of thw square equally then with this set of matchsticks we cant form a square
        if(total_sum % 4 != 0){
            return false;
        }
        int side_of_square = total_sum / 4;
        // we now r going to sort the stcicks into descending order so it would be a better for this
        Arrays.sort(matchsticks);
        reverse(matchsticks); // we reverse the ascending order to get descending order

        // ver veyr crucial part: since a swuare has 4 sides , we make sudre we create an array of length 4 and we try to fit for every 4 position  these values and find it out
        int[] sides = new int[4];

        // now we call our helper to solve this
        return backtrack_helper(0 , side_of_square , matchsticks , sides);
    }

    // our very imporatant helper
    public boolean backtrack_helper(int index ,int target_value_of_a_side ,int[] sticks ,
    int[] sides){
        // very veey imporant base case: we need to use evry single stick to from this square then ony it becomes treu
        if(index == sticks.length){
            return true;
        }

        // now we take the value of the current stick -> since we r going thorugh recursively for each recursive state , we use a stick
        int current_stick = sticks[index];
        // core part of this code
        for(int i =0; i < 4; i++){
            // since we have 4 sides of a square so now we check for every side one by one and c
            // rule : if curent stcik value exceeds the target value of the side , then we skip it
            if(sides[i] + current_stick <= target_value_of_a_side){
                // if it comes under this or when it atctauly matches our target then
                sides[i] += current_stick;

                // this is the very veyr veyr impornt part: now we recuricly call and try for next set of matchsticks and if when all the next matchsticks are used to form all side of the square then we know a square is formed so
                if(backtrack_helper(index + 1 , target_value_of_a_side , sticks , sides)){
                    return true;
                }

                // if we coudnt try to find for next set of sticks then we perfrom this backtracking step only when the above is not exceuted because we count not try to fit other sticks
                sides[i] -= current_stick;
            }

            // veyr important optimixzation rule here : after doinf everything and if u cant try to find a value for this side , then the next set of value will also be useless , becayuse our sticks r in descednign order so , we immnedialty break from the loop
            if(sides[i] == 0){
                break;
            }
        }
        // if we coudnt not form a square then
        return false;
    }

    public void reverse(int[] arr){
        int left = 0;
        int right = arr.length - 1;
        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}