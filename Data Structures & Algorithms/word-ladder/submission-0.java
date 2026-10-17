class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // this is a an very evry imporant impportant queztion
        Set<String> words = new HashSet<>(wordList);
        
        // veryevyr imporant edge case
        if(!words.contains(endWord)){
            return 0;
        }

        // we need to work on each charcter in the given begin word so we convert it
        // since we need to find shortest value BFS algo is best for this
        Queue<String> q = new LinkedList<>();
        // insert the string and remove the begin word from the word list if it was there
        q.offer(beginWord);
        // remove from the set of strings
        words.remove(beginWord);

         // The question counts the NUMBER OF WORDS in the sequence, not just transitions.
        // So starting with "hit" means our initial level count is 1.
        int level = 1;

        while(!q.isEmpty()){
            // furst calcultae the size of the current level to prcess all the words of this current level
            int size = q.size();
            for(int i=0; i < size; i++){
                // of the current level , we proces each each string now
                String curr = q.poll();

                // this is the very evry evry imporant check of this entire question:
                // suppose we have found out the end word -> then we return the current level number whih represents the shortest number of time required to trasnform the begin word to the end word
                if(curr.equals(endWord)){
                    return level;
                }

                // if not then process the current word of this level
                // first we convert the string to each letters so we can find out the possibilitis
                char[] letters_of_curr_word = curr.toCharArray();
                // process each letter
                for(int j =0; j < letters_of_curr_word.length; j++){
                    // first we took one charcter at a time
                    char originalChar = letters_of_curr_word[j];  // store this word so that after processing this word we replace this with the original and work with next set of letters
                    // now we have to find possibiltie combination of words with diffetent letters at this given place and check wheather it is present in the wordlist or not
                    for(char c = 'a'; c <= 'z'; c++){
                        // if the same chacter same then we skip it
                        if(c == originalChar){
                            continue;
                        }

                        // now repkce this word with all possibilti letters and find 
                        letters_of_curr_word[j] = c;
                        // convert to string
                        String new_word = new String(letters_of_curr_word);

                        // now coes the veyr evyr imp part
                        // if this word contains in the word list then we add in into the next level of queue to precess
                        if(words.contains(new_word)){
                            q.offer(new_word);
                            words.remove(new_word);  // remove this new word from the word list so that we wont process it again
                        }
                        // if that word does not contains then we simply ignore it and try other combination for different letters
                    }
                    // after this current etter is finished -> now we replce the original char to its place and try to word in other possitions
                    letters_of_curr_word[j] = originalChar;
                }
            }
            // increase the level if one is completed
            level++; 
        }

        // we coundnt not transform then
        return 0;

    }
}