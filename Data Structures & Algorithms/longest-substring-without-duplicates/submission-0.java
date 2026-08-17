class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        
        // ASCII has 128 standard characters (letters, numbers, symbols).
        // This array replaces the HashSet completely!
        boolean[] seen = new boolean[128]; 
        
        int left = 0;
        int maxlength = 0;
        
        for (int right = 0; right < n; right++) {
            char currentChar = s.charAt(right);
            
            // While the current character is already true (seen),
            // unmark the character at 'left' and shift the pointer
            while (seen[currentChar]) {
                char leftChar = s.charAt(left);
                seen[leftChar] = false; // "Remove" it from our window tracker
                left++;
            }
            
            // Mark the new character as seen ("Add" to our window)
            seen[currentChar] = true;
            
            int currentSubstringLength = right - left + 1;
            maxlength = Math.max(maxlength, currentSubstringLength);
        }
        
        return maxlength;
    }
}
