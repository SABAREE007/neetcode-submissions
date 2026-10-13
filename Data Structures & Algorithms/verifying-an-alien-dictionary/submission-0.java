class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        // this question is similar to alien dictionary questinon but we dint need a graph solution for this
        // here its given that aliens's alphabetical order is given in the order string
        // we have to check wheater the words in the array is lexicographical sorted in thier order

        // first we form a translator(rules)
        int[] alienOrder = new int[26]; // standard 26 letters
        // we assign them according to an value of thier order
        for(int i=0; i < alienOrder.length; i++){
            char c = order.charAt(i);
            alienOrder[c - 'a'] = i;
        }

        // as now we have formed the order , we r going to compare these words and find out wheather they r sorted or not
        for(int i=0; i < words.length - 1; i++){
            // if its not sorted then we return false
            if(!isSorted(words[i] , words[i + 1], alienOrder)){
                return false;
            }
        }

        // if all the words is sorted then
        return true; 
    }

    // imporant function:
    public boolean isSorted(String word1 ,String word2,int[] alienOrder){
        int l1 = word1.length();
        int l2 = word2.length();

        int minLen = Math.min(l1 , l2);
        // we now iterate till only the smaller length word becuase then only we can compare them
        for(int i =0; i < minLen; i++){
            // now we compare them letter by letter
            // we only check when there is the first mismatch after then we do not care
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);

            // if we found our mismatch now compare then
            if(c1 != c2){
                // now we get thier lexigographical value based on thier rules
                // get thier value from thier order
                int rank1 = alienOrder[c1 - 'a'];
                int rank2 = alienOrder[c2 - 'a'];

                // now the main part we compare them , if the first value is lesser than second , then its lexicographcial sorted , if not then not sorted correctly
                return rank1 < rank2;
            }
        }

        // very veyr imporant case: , if both thier letters are matched untill the minium length of a string length
        // we now check that if is the shorter length strng have come first or not 
        // if shorter length string comes first then its sorted correctly , if longer length string comes first then its not
        return l1 <= l2;
    }
}